package cooksystem.server.services;

import cooksystem.server.dtos.CompanyDto;
import cooksystem.server.entities.Company;

import java.util.List;

public interface CompanyService {

    List<CompanyDto> getAllCompanies();

    Company findCompany(Long id) throws Exception;

}
