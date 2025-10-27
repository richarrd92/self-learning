package cooksystem.server.services;

import cooksystem.server.dtos.AnnouncementDto;
import cooksystem.server.dtos.AnnouncementRequestDto;

import java.util.*;

public interface AnnouncementService {
    List<AnnouncementDto> getAllAnnouncements(Long id) throws Exception;
    AnnouncementDto createAnnouncement(Long id, AnnouncementRequestDto announcementRequestDto) throws Exception;
}
