package cz.dan.controlhub.football.player.request.service;

import cz.dan.controlhub.football.player.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.player.request.service.mapper.FootballPlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FootballPlayerServiceImpl implements FootballPlayerService {

    private final FootballPlayerMapper footballPlayerMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void requestPlayers(FootballPlayerRequestDto request) {
        kafkaTemplate.send("fetcher.request.football.player", footballPlayerMapper.from(request));
    }

}
