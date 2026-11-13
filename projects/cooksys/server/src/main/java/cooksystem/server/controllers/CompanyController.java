package cooksystem.server.controllers;

import cooksystem.server.dtos.CompanyDto;
import cooksystem.server.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping
    public List<CompanyDto> getCompanies() {
        return companyService.getAllCompanies();
    }

}
