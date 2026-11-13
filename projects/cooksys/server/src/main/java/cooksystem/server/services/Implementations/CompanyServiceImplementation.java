package cooksystem.server.services.Implementations;

import cooksystem.server.dtos.CompanyDto;
import cooksystem.server.dtos.ProjectDto;
import cooksystem.server.entities.Company;
import cooksystem.server.entities.Team;
import cooksystem.server.exceptions.NotFoundException;
import cooksystem.server.mappers.CompanyMapper;
import cooksystem.server.repositories.CompanyRepository;
import cooksystem.server.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImplementation implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getAllCompanies() {
        return companyMapper.entitiesToDtos(companyRepository.findAll());
    }

    @Override
    public Company findCompany(Long id) throws Exception {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isEmpty()) {
            throw new Exception("A company with the provided id does not exist.");
        }
        return company.get();
    }
}
