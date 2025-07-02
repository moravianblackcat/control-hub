package cz.dan.controlhub.integration.glue.football.request.player;

import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import cz.dan.integrationtests.kafka.TestKafkaConsumer;
import cz.dan.integrationtests.util.ClassPathResourceUtil;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class FootballRequestPlayerStep {

    private static final String FOOTBALL_PLAYER_REQUEST_TOPIC_NAME = "fetcher.request.football.player";

    private final ClassPathResourceUtil classPathResourceUtil;

    private final TestKafkaConsumer testKafkaConsumer;

    @Then("{int}s Football players defined in {} are requested")
    public void footballPlayersAreRequested(int timeoutInSeconds, String messagesJsonPath) throws IOException {
        FootballPlayerRequest expected = classPathResourceUtil.getObjectFromJsonPath(messagesJsonPath,
                FootballPlayerRequest.class);
        FootballPlayerRequest actual = testKafkaConsumer
                .getNextMessageFromTopicWithTimeout(FOOTBALL_PLAYER_REQUEST_TOPIC_NAME, timeoutInSeconds);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }

}
