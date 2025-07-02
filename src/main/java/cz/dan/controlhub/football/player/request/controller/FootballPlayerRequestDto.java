package cz.dan.controlhub.football.player.request.controller;

import java.util.List;

public record FootballPlayerRequestDto(List<Long> playerIds) {
}
