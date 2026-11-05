package cooksystem.server.services.Implementations;

import cooksystem.server.dtos.TeamDto;
import cooksystem.server.dtos.TeamRequestDto;
import cooksystem.server.entities.Company;
import cooksystem.server.entities.Team;
import cooksystem.server.entities.User;
import cooksystem.server.exceptions.BadRequestException;
import cooksystem.server.exceptions.NotFoundException;
import cooksystem.server.mappers.TeamMapper;
import cooksystem.server.repositories.TeamRepository;
import cooksystem.server.repositories.UserRepository;
import cooksystem.server.services.CompanyService;
import cooksystem.server.services.TeamService;
import cooksystem.server.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TeamServiceImplementation implements TeamService {

    private final CompanyService companyService;
    private final UserService userService;

    private final TeamMapper teamMapper;

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public List<TeamDto> getAllTeams(Long id) throws Exception {
        Company company = companyService.findCompany(id);
        return teamMapper.entitiesToDtos(teamRepository.findByCompany(company));
    }

    @Override
    public TeamDto createTeam(Long id, TeamRequestDto teamRequestDto) throws Exception {
        Company company = companyService.findCompany(id);

        if (teamRequestDto.getName() == null || teamRequestDto.getName().isBlank() ||
            teamRequestDto.getDescription() == null || teamRequestDto.getDescription().isBlank()) {
            throw new BadRequestException("Team name and description are required");
        }

        Optional<User> author = userRepository.findById(teamRequestDto.getAuthorId());
        if (author.isEmpty()) {
            throw new Exception("User does not exist");
        }
        if (!author.get().getUserState().isAdmin()) {
            throw new Exception("User is not an admin and can't create teams");
        }

        Set<Long> userIds = teamRequestDto.getUserIds();
        Set<User> users = new HashSet<>();
        for (Long userId : userIds) {
            User user = userService.findUser(userId);
            if (!user.getCompanies().contains(company)) {
                throw new BadRequestException(user.getProfile().getFirst() + " does not belong to the company");
            }
            else if (!user.getUserState().isActive()) {
                throw new BadRequestException(user.getProfile().getFirst() + " is inactive and teams can't be created with inactive users");
            }
            users.add(user);
        }

        Team team = teamMapper.requestDtoToEntity(teamRequestDto);
        team.setCompany(company);
        team.setUsers(users);

        for (User user : users) {
            user.getTeams().add(team);
        }

        return teamMapper.entityToDto(teamRepository.saveAndFlush(team));
    }

    @Override
    public Team findTeam(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        if (team.isEmpty()) {
            throw new NotFoundException("A team with the provided id does not exist.");
        }
        return team.get();
    }
}
