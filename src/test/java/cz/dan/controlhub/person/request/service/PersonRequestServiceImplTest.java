package cz.dan.controlhub.person.request.service;

import cz.dan.avro.fetcher.request.PersonRequest;
import cz.dan.controlhub.person.request.controller.PersonRequestDto;
import cz.dan.controlhub.person.request.service.mapper.PersonMapper;
import cz.dan.controlhub.person.request.service.mapper.PersonMapperImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonRequestServiceImplTest {

    @Spy
    private final PersonMapper personMapper = new PersonMapperImpl();

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private PersonRequestServiceImpl sut;

    @Captor
    private ArgumentCaptor<PersonRequest> personRequestCaptor;

    @Test
    void sendsCorrectRequestIntoCorrectTopic() {
        sut.requestPerson(new PersonRequestDto("CZE", "Petr", "Čech",
                "Petr Čech", "Petr Cech", LocalDate.of(1982, 5, 20)));

        verify(kafkaTemplate, times(1))
                .send(eq("fetcher.request.person"), personRequestCaptor.capture());
        assertThat(personRequestCaptor.getValue())
                .isNotNull()
                .extracting(
                        PersonRequest::getNationality,
                        PersonRequest::getFirstName,
                        PersonRequest::getLastName,
                        PersonRequest::getName,
                        PersonRequest::getDisplayName,
                        PersonRequest::getDateOfBirth
                ).containsExactly(
                        "CZE",
                        "Petr",
                        "Čech",
                        "Petr Čech",
                        "Petr Cech",
                        LocalDate.of(1982, 5, 20)
                );
    }

}