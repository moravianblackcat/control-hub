package cz.dan.controlhub.person.request.controller;

import cz.dan.controlhub.person.request.service.PersonRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/request/person")
@RequiredArgsConstructor
public class PersonRequestController {

    private final PersonRequestService personRequestService;

    @PostMapping
    public ResponseEntity<Void> requestPerson(@RequestBody PersonRequestDto request) {
        personRequestService.requestPerson(request);

        return ResponseEntity.status(201).build();
    }

}
