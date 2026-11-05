package cooksystem.server.services;

import cooksystem.server.dtos.CredentialsRequestDto;
import cooksystem.server.dtos.CredentialsResponseDto;
import cooksystem.server.dtos.LogoutResponseDto;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    LogoutResponseDto logout(HttpServletRequest request);
    CredentialsResponseDto loginWithSession(CredentialsRequestDto credentialsRequestDto, HttpServletRequest request);
}
