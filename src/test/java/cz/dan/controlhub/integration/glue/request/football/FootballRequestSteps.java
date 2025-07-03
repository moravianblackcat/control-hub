package cz.dan.controlhub.integration.glue.request.football;

import cz.dan.avro.fetcher.request.FootballCoachRequest;
import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import cz.dan.avro.fetcher.request.FootballTeamRequest;
import cz.dan.integrationtests.kafka.TestKafkaConsumer;
import cz.dan.integrationtests.util.ClassPathResourceUtil;
import io.cucumber.java.en.Then;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RequiredArgsConstructor
public class FootballRequestSteps {

    private static final String FOOTBALL_COACH_REQUEST_TOPIC_NAME = "fetcher.request.football.coach";

    private static final String FOOTBALL_PLAYER_REQUEST_TOPIC_NAME = "fetcher.request.football.player";

    private static final String FOOTBALL_TEAM_REQUEST_TOPIC_NAME = "fetcher.request.football.team";

    private final ClassPathResourceUtil classPathResourceUtil;

    private final TestKafkaConsumer testKafkaConsumer;

    @Then("{int}s Football coaches defined in {} are requested")
    public void footballCoachesAreRequested(int timeoutInSeconds, String messagesJsonPath) throws IOException {
        FootballCoachRequest expected = classPathResourceUtil.getObjectFromJsonPath(messagesJsonPath,
                FootballCoachRequest.class);
        FootballCoachRequest actual = testKafkaConsumer
                .getNextMessageFromTopicWithTimeout(FOOTBALL_COACH_REQUEST_TOPIC_NAME, timeoutInSeconds);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }

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

    @Then("{int}s Football teams defined in {} are requested")
    public void footballTeamsAreRequested(int timeoutInSeconds, String messagesJsonPath) throws IOException {
        FootballTeamRequest expected = classPathResourceUtil.getObjectFromJsonPath(messagesJsonPath,
                FootballTeamRequest.class);
        FootballTeamRequest actual = testKafkaConsumer
                .getNextMessageFromTopicWithTimeout(FOOTBALL_TEAM_REQUEST_TOPIC_NAME, timeoutInSeconds);

        assertThat(actual)
                .isNotNull()
                .isEqualTo(expected);
    }

}
