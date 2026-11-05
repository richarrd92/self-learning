package cooksystem.server.services.Implementations;

import cooksystem.server.dtos.AnnouncementDto;
import cooksystem.server.dtos.AnnouncementRequestDto;
import cooksystem.server.entities.Announcement;
import cooksystem.server.entities.Company;
import cooksystem.server.entities.User;
import cooksystem.server.exceptions.BadRequestException;
import cooksystem.server.mappers.AnnouncementMapper;
import cooksystem.server.repositories.AnnouncementRepository;
import cooksystem.server.repositories.UserRepository;
import cooksystem.server.services.AnnouncementService;
import cooksystem.server.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImplementation implements AnnouncementService {

    private final CompanyService companyService;

    private final AnnouncementRepository announcementRepository;
    private final UserRepository userRepository;

    private final AnnouncementMapper announcementMapper;

    @Override
    public List<AnnouncementDto> getAllAnnouncements(Long id) throws Exception {
        Company company = companyService.findCompany(id);
        List<Announcement> sortedList = announcementRepository.findByCompanyOrderByDateDesc(company);
        return announcementMapper.entitiesToDtos(sortedList);
    }

    @Override
    public AnnouncementDto createAnnouncement(Long id, AnnouncementRequestDto announcementRequestDto) throws Exception {
        Company company = companyService.findCompany(id);

        if (announcementRequestDto.getTitle() == null || announcementRequestDto.getTitle().isBlank()
                || announcementRequestDto.getMessage() == null || announcementRequestDto.getMessage().isBlank()) {
            throw new BadRequestException("Title and message can't be blank");
        }

        Optional<User> user = userRepository.findById(announcementRequestDto.getAuthorId());
        if (user.isEmpty()) {
            throw new Exception("User does not exist");
        }
        if (!user.get().getUserState().isAdmin()) {
            throw new Exception("User is not an admin and can't create announcements");
        }

        Announcement announcement = announcementMapper.requestDtoToEntity(announcementRequestDto);
        Optional<User> author = userRepository.findById(announcementRequestDto.getAuthorId());
        author.ifPresent(announcement::setAuthor);
        announcement.setCompany(company);
        return announcementMapper.entityToDto(announcementRepository.saveAndFlush(announcement));
    }
}
