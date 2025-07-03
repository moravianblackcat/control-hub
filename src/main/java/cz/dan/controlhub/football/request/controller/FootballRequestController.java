package cz.dan.controlhub.football.request.controller;

import cz.dan.controlhub.football.request.service.FootballService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request/football")
@RequiredArgsConstructor
public class FootballRequestController {

    private final FootballService footballService;

    @PostMapping("/coach")
    public ResponseEntity<Void> requestCoaches(@RequestBody FootballCoachRequestDto request) {
        return getOkResponse(() -> footballService.requestCoaches(request));
    }

    @PostMapping("/player")
    public ResponseEntity<Void> requestPlayers(@RequestBody FootballPlayerRequestDto request) {
        return getOkResponse(() -> footballService.requestPlayers(request));
    }

    @PostMapping("/team")
    public ResponseEntity<Void> requestTeams(@RequestBody FootballTeamRequestDto request) {
        return getOkResponse(() -> footballService.requestTeams(request));
    }

    private ResponseEntity<Void> getOkResponse(Runnable runnable) {
        runnable.run();

        return ResponseEntity.status(200).build();
    }

}
