package cz.dan.controlhub.football.player.request.controller;

import cz.dan.controlhub.football.player.request.service.FootballPlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request/football/player")
@RequiredArgsConstructor
public class FootballPlayerRequestController {

    private final FootballPlayerService footballPlayerService;

    @PostMapping
    public ResponseEntity<Void> requestPlayers(@RequestBody FootballPlayerRequestDto request) {
        footballPlayerService.requestPlayers(request);

        return ResponseEntity.status(201).build();
    }

}
