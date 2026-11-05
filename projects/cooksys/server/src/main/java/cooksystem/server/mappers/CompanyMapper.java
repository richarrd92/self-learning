package cooksystem.server.mappers;

import cooksystem.server.dtos.CompanyDto;
import cooksystem.server.entities.Company;
import org.mapstruct.Mapper;

import java.util.*;

@Mapper(componentModel = "spring", uses = {CompanyMapper.class})
public interface CompanyMapper {
    List<CompanyDto> entitiesToDtos(List<Company> companies);
}