package cz.dan.controlhub.integration.glue.request.person;

import cz.dan.avro.fetcher.request.PersonRequest;
import cz.dan.integrationtests.kafka.TestKafkaConsumer;
import cz.dan.integrationtests.util.ClassPathResourceUtil;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class PersonRequestSteps {

    private static final String PERSON_REQUEST_TOPIC_NAME = "fetcher.request.person";

    private final ClassPathResourceUtil classPathResourceUtil;

    private final TestKafkaConsumer testKafkaConsumer;

    @Then("{int}s Person defined in {} is requested")
    public void personDefinedInIsRequested(int timeoutInSeconds, String messagesJsonPath) throws IOException {
        PersonRequest expected = classPathResourceUtil.getObjectFromJsonPath(messagesJsonPath, PersonRequest.class);
        PersonRequest actual = testKafkaConsumer.getNextMessageFromTopicWithTimeout(PERSON_REQUEST_TOPIC_NAME,
                timeoutInSeconds);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }
}
