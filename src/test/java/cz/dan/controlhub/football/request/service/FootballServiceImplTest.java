package cz.dan.controlhub.football.request.service;

import cz.dan.avro.fetcher.request.FootballCoachRequest;
import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import cz.dan.avro.fetcher.request.FootballTeamRequest;
import cz.dan.controlhub.football.request.controller.FootballCoachRequestDto;
import cz.dan.controlhub.football.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.request.controller.FootballTeamRequestDto;
import cz.dan.controlhub.football.request.service.mapper.FootballMapper;
import cz.dan.controlhub.football.request.service.mapper.FootballMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;

import static cz.dan.avro.fetcher.Source.Sportmonks;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FootballServiceImplTest {

    @Spy
    private final FootballMapper footballMapper = new FootballMapperImpl();

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private FootballServiceImpl sut;

    @Captor
    private ArgumentCaptor<FootballCoachRequest> footballCoachRequestCaptor;

    @Captor
    private ArgumentCaptor<FootballPlayerRequest> footballPlayerRequestCaptor;

    @Captor
    private ArgumentCaptor<FootballTeamRequest> footballTeamRequestCaptor;

    @Test
    void sendsCorrectFootballCoachRequestIntoCorrectTopic() {
        sut.requestCoaches(new FootballCoachRequestDto(List.of(2L)));

        verify(kafkaTemplate, times(1))
                .send(eq("fetcher.request.football.coach"), footballCoachRequestCaptor.capture());
        assertThat(footballCoachRequestCaptor.getValue())
                .isNotNull()
                .extracting(
                        FootballCoachRequest::getIds,
                        FootballCoachRequest::getSource
                ).containsExactly(
                        List.of(2L),
                        Sportmonks
                );
    }

    @Test
    void sendsCorrectFootballPlayerRequestIntoCorrectTopic() {
        sut.requestPlayers(new FootballPlayerRequestDto(List.of(15L)));

        verify(kafkaTemplate, times(1))
                .send(eq("fetcher.request.football.player"), footballPlayerRequestCaptor.capture());
        assertThat(footballPlayerRequestCaptor.getValue())
                .isNotNull()
                .extracting(
                        FootballPlayerRequest::getIds,
                        FootballPlayerRequest::getSource
                ).containsExactly(
                        List.of(15L),
                        Sportmonks
                );
    }

    @Test
    void sendsCorrectFootballTeamRequestIntoCorrectTopic() {
        sut.requestTeams(new FootballTeamRequestDto(List.of(25L)));

        verify(kafkaTemplate, times(1))
                .send(eq("fetcher.request.football.team"), footballTeamRequestCaptor.capture());
        assertThat(footballTeamRequestCaptor.getValue())
                .isNotNull()
                .extracting(
                        FootballTeamRequest::getIds,
                        FootballTeamRequest::getSource
                ).containsExactly(
                        List.of(25L),
                        Sportmonks
                );
    }

}