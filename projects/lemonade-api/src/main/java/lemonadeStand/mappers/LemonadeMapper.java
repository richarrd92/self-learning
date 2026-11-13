package lemonadeStand.mappers;

import lemonadeStand.entities.Lemonade;
import lemonadeStand.model.LemoandeDto;
import lemonadeStand.model.LemonadeRequestDto;
import lemonadeStand.model.LemonadeResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LemonadeMapper {

    Lemonade requestDtoToEntity(LemonadeRequestDto lemonadeRequestDto);

    Lemonade lemonadeDtoToEntity(LemoandeDto lemonadeDto);

    LemonadeResponseDto entityToResponseDto(Lemonade lemonade);

    List<LemonadeResponseDto> entitiesToResponseDtos(List<Lemonade> lemonades);

}
