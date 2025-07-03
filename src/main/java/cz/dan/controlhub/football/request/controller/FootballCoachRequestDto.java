package cz.dan.controlhub.football.request.controller;

import java.util.List;

public record FootballCoachRequestDto(List<Long> coachIds) {
}
