package lemonadeStand.services.impl;

import lemonadeStand.entities.Lemonade;
import lemonadeStand.exceptions.BadRequestException;
import lemonadeStand.exceptions.NotFoundException;
import lemonadeStand.mappers.LemonadeMapper;
import lemonadeStand.model.LemonadeRequestDto;
import lemonadeStand.model.LemonadeResponseDto;
import lemonadeStand.repositories.LemonadeRepository;
import lemonadeStand.services.LemonadeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LemonadeServiceImpl implements LemonadeService {

    private LemonadeRepository lemonadeRepository;
    private LemonadeMapper lemonadeMapper;

    private void setLemonadePrice(Lemonade lemonade) {
        lemonade.setPrice(lemonade.getLemonJuice() * .20 + lemonade.getWater() * .01 + lemonade.getSugar() * .15
                + lemonade.getIceCubes() * .05 + .50);
    }

    private void validateLemonadeRequest(LemonadeRequestDto lemonadeRequestDto) {
        if (lemonadeRequestDto == null || lemonadeRequestDto.getLemonJuice() == null
                || lemonadeRequestDto.getWater() == null
                || lemonadeRequestDto.getSugar() == null || lemonadeRequestDto.getIceCubes() == null) {
            throw new BadRequestException("All fields are required on a lemonade request dto");
        }
    }

    @Override
    public Lemonade getLemonade(Long id) {
        Optional<Lemonade> optionalLemonade = lemonadeRepository.findByIdAndDeletedFalse(id);
        if (optionalLemonade.isEmpty()) {
            throw new NotFoundException("No lemonade found with id: " + id);
        }
        return optionalLemonade.get();
    }

    @Override
    public List<LemonadeResponseDto> getAllLemonades() {
        return lemonadeMapper.entitiesToResponseDtos(lemonadeRepository.findAllByDeletedFalse());
    }

    @Override
    public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto) {
        validateLemonadeRequest(lemonadeRequestDto);
        Lemonade lemonadeToSave = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
        setLemonadePrice(lemonadeToSave);
        lemonadeToSave.setDeleted(false);
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToSave));
    }

    @Override
    public LemonadeResponseDto getLemonadeById(Long id) {
        return lemonadeMapper.entityToResponseDto(getLemonade(id));
    }

    @Override
    public LemonadeResponseDto updateLemonade(Long id, LemonadeRequestDto lemonadeRequestDto) {
        validateLemonadeRequest(lemonadeRequestDto);
        Lemonade lemonadeToUpdate = getLemonade(id);
        lemonadeToUpdate.setIceCubes(lemonadeRequestDto.getIceCubes());
        lemonadeToUpdate.setLemonJuice(lemonadeRequestDto.getLemonJuice());
        lemonadeToUpdate.setWater(lemonadeRequestDto.getWater());
        lemonadeToUpdate.setSugar(lemonadeRequestDto.getSugar());
        setLemonadePrice(lemonadeToUpdate);
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToUpdate));
    }

    @Override
    public LemonadeResponseDto deleteLemonade(Long id) {
        Lemonade lemonadeToDelete = getLemonade(id);
        lemonadeToDelete.setDeleted(true);
        return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToDelete));
    }

}
