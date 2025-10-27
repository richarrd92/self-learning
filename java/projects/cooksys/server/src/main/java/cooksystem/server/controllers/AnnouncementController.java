package cooksystem.server.controllers;

import cooksystem.server.dtos.AnnouncementDto;
import cooksystem.server.dtos.AnnouncementRequestDto;
import cooksystem.server.services.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/announcements")
public class AnnouncementController {

    private final AnnouncementService announcementService;

    @GetMapping("/{companyId}")
    public List<AnnouncementDto> getAnnouncements(@PathVariable Long companyId) throws Exception {
        return announcementService.getAllAnnouncements(companyId);
    }

    @PostMapping("/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AnnouncementDto createAnnouncement(@PathVariable Long companyId, @RequestBody AnnouncementRequestDto announcementRequestDto) throws Exception {
        return announcementService.createAnnouncement(companyId, announcementRequestDto);
    }
}
