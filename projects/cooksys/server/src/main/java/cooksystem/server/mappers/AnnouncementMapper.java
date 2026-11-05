package cooksystem.server.mappers;

import cooksystem.server.entities.Announcement;
import org.mapstruct.Mapper;

import cooksystem.server.dtos.AnnouncementDto;
import cooksystem.server.dtos.AnnouncementRequestDto;
import cooksystem.server.entities.Announcement;

import java.util.*;

@Mapper(componentModel = "spring", uses = {AnnouncementMapper.class})
public interface AnnouncementMapper {

    AnnouncementDto entityToDto(Announcement announcement);
    List<AnnouncementDto> entitiesToDtos(List<Announcement> announcements);
    Announcement requestDtoToEntity(AnnouncementRequestDto announcementRequestDto);
}
