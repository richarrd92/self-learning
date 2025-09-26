package lemonadeStand.services.impl;

import lemonadeStand.entities.LemonadeStand;
import lemonadeStand.exceptions.BadRequestException;
import lemonadeStand.exceptions.NotFoundException;
import lemonadeStand.mappers.LemonadeStandMapper;
import lemonadeStand.model.LemonadeStandRequestDto;
import lemonadeStand.model.LemonadeStandResponseDto;
import lemonadeStand.repositories.LemonadeStandRepository;
import lemonadeStand.services.LemonadeStandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LemonadeStandServiceImpl implements LemonadeStandService {

    private final LemonadeStandRepository lemonadeStandRepository;
    private final LemonadeStandMapper lemonadeStandMapper;

    private void validateLemonadeStandRequest(LemonadeStandRequestDto lemonadeStandRequestDto) {
        if (lemonadeStandRequestDto == null || lemonadeStandRequestDto.getName() == null) {
            throw new BadRequestException("All fields are required on a lemonadeStand request dto");
        }
    }

    @Override
    public LemonadeStand getLemonadeStand(Long id) {
        Optional<LemonadeStand> optionalLemonadeStand = lemonadeStandRepository.findById(id);
        if (optionalLemonadeStand.isEmpty()) {
            throw new NotFoundException("No lemonadeStand with id: " + id);
        }
        return optionalLemonadeStand.get();
    }

    @Override
    public LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto) {
        validateLemonadeStandRequest(lemonadeStandRequestDto);
        return lemonadeStandMapper.entityToResponseDto(
                lemonadeStandRepository.saveAndFlush(lemonadeStandMapper.requestDtoToEntity(lemonadeStandRequestDto)));
    }

    @Override
    public List<LemonadeStandResponseDto> getAllLemonadeStands() {
        return lemonadeStandMapper.entitiesToResponseDtos(lemonadeStandRepository.findAll());
    }

    @Override
    public LemonadeStandResponseDto getLemonadeStandById(Long id) {
        return lemonadeStandMapper.entityToResponseDto(getLemonadeStand(id));
    }

    @Override
    public LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStandRequestDto) {
        if (lemonadeStandRequestDto == null) {
            throw new BadRequestException("At least 1 field must be sent to update");
        }
        LemonadeStand lemonadeStandToUpdate = getLemonadeStand(id);
        if (lemonadeStandRequestDto.getName() != null) {
            lemonadeStandToUpdate.setName(lemonadeStandRequestDto.getName());
        }
        return lemonadeStandMapper.entityToResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStandToUpdate));
    }

    @Override
    public LemonadeStandResponseDto deleteLemonadeStand(Long id) {
        LemonadeStand lemonadeStandToDelete = getLemonadeStand(id);
        lemonadeStandRepository.delete(lemonadeStandToDelete);
        return lemonadeStandMapper.entityToResponseDto(lemonadeStandToDelete);
    }
}
