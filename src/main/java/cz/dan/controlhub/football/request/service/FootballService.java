package cz.dan.controlhub.football.request.service;

import cz.dan.controlhub.football.request.controller.FootballCoachRequestDto;
import cz.dan.controlhub.football.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.request.controller.FootballTeamRequestDto;

public interface FootballService {

    void requestCoaches(FootballCoachRequestDto request);

    void requestPlayers(FootballPlayerRequestDto request);

    void requestTeams(FootballTeamRequestDto request);

}
