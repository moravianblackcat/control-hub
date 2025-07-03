package cz.dan.controlhub.football.request.service.mapper;

import cz.dan.avro.fetcher.request.FootballCoachRequest;
import cz.dan.avro.fetcher.request.FootballPlayerRequest;
import cz.dan.avro.fetcher.request.FootballTeamRequest;
import cz.dan.controlhub.football.request.controller.FootballCoachRequestDto;
import cz.dan.controlhub.football.request.controller.FootballPlayerRequestDto;
import cz.dan.controlhub.football.request.controller.FootballTeamRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface FootballMapper {

    @Mapping(source = "coachIds", target = "ids")
    @Mapping(constant = "Sportmonks", target = "source")
    FootballCoachRequest from(FootballCoachRequestDto request);

    @Mapping(source = "playerIds", target = "ids")
    @Mapping(constant = "Sportmonks", target = "source")
    FootballPlayerRequest from(FootballPlayerRequestDto request);

    @Mapping(source = "teamIds", target = "ids")
    @Mapping(constant = "Sportmonks", target = "source")
    FootballTeamRequest from(FootballTeamRequestDto request);

}
