package cz.dan.controlhub.football.player.request.service;

import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import cz.dan.controlhub.football.player.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.player.request.service.mapper.FootballPlayerMapper;
import cz.dan.controlhub.football.player.request.service.mapper.FootballPlayerMapperImpl;
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
class FootballPlayerServiceImplTest {

    @Spy
    private final FootballPlayerMapper footballPlayerMapper = new FootballPlayerMapperImpl();

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private FootballPlayerServiceImpl sut;

    @Captor
    private ArgumentCaptor<FootballPlayerRequest> footballPlayerRequestCaptor;

    @Test
    void sendsCorrectRequestIntoCorrectTopic() {
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

}