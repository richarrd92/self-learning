package com.cooksys.ftd.social_media.services.Impl;
import com.cooksys.ftd.social_media.repositories.HashtagRepository;
import org.springframework.stereotype.Service;
import com.cooksys.ftd.social_media.services.ValidateService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ValidateServiceImpl implements ValidateService {
    private final HashtagRepository hashtagRepository;

    @Override
    public boolean hashtagExists(String label) {
        return hashtagRepository.existsByLabelIgnoreCase(label);
    }
}