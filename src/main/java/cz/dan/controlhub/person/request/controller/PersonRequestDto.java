package cz.dan.controlhub.person.request.controller;

import java.time.LocalDate;

public record PersonRequestDto(String nationality, String firstName, String lastName, String name,
                               String displayName, LocalDate dateOfBirth) {
}
