package cz.dan.controlhub.person.request.service.mapper;

import cz.dan.avro.fetcher.request.PersonRequest;
import cz.dan.controlhub.person.request.controller.PersonRequestDto;
import org.mapstruct.Mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface PersonMapper {

    PersonRequest from(PersonRequestDto request);

}
