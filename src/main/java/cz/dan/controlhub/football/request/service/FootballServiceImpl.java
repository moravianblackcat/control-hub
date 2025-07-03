package cz.dan.controlhub.football.request.service;

import cz.dan.controlhub.football.request.controller.FootballCoachRequestDto;
import cz.dan.controlhub.football.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.request.controller.FootballTeamRequestDto;
import cz.dan.controlhub.football.request.service.mapper.FootballMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FootballServiceImpl implements FootballService {

    private final FootballMapper footballMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void requestCoaches(FootballCoachRequestDto request) {
        kafkaTemplate.send("fetcher.request.football.coach", footballMapper.from(request));
    }

    @Override
    public void requestPlayers(FootballPlayerRequestDto request) {
        kafkaTemplate.send("fetcher.request.football.player", footballMapper.from(request));
    }

    @Override
    public void requestTeams(FootballTeamRequestDto request) {
        kafkaTemplate.send("fetcher.request.football.team", footballMapper.from(request));
    }

}
