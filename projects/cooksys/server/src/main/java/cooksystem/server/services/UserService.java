package cooksystem.server.services;

import cooksystem.server.dtos.*;
import cooksystem.server.entities.User;

import java.util.List;

public interface UserService {
    UserResponseDto getUser(Long id, Long userId);
    List<UserResponseDto> getAllUsers(Long userId);
    UserResponseDto createUser(UserRequestDto userRequestDto, Long companyId, Long userId);
    User findUser(Long id);
    List<UserResponseDto> getUsersByCompany(Long CompanyId, Long userId);
}
