package cz.dan.controlhub.football.player.request.service.mapper;

import cz.dan.controlhub.football.player.request.controller.FootballPlayerRequestDto;
import org.mapstruct.Mapper;

 import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface FootballPlayerMapper {

    @Mapping(source = "playerIds", target = "ids")
    @Mapping(constant = "Sportmonks", target = "source")
    FootballPlayerRequest from(FootballPlayerRequestDto request);

}
