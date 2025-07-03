package cz.dan.controlhub.person.request.service;

import cz.dan.controlhub.person.request.controller.PersonRequestDto;
import cz.dan.controlhub.person.request.service.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PersonRequestServiceImpl implements PersonRequestService {

    private final PersonMapper personMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void requestPerson(PersonRequestDto request) {
        kafkaTemplate.send("fetcher.request.person", personMapper.from(request));
    }

}
