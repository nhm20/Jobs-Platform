package com.example.microservices.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> findAll() {
        return companyRepository.findAll();
    }
    public String createCompany(Company company) {
        companyRepository.save(company);
        return "Company created successfully!";
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    public boolean deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            return false;
        } else {
            companyRepository.delete(company);
            return true;
        }
    }

    public boolean updateCompany(Long id, Company updatedCompany) {
        Company existingCompany = companyRepository.findById(id).orElse(null);
        if (existingCompany == null) {
            return false;
        } else {
            existingCompany.setName(updatedCompany.getName());
            existingCompany.setDescription(updatedCompany.getDescription());
            existingCompany.setJobs(updatedCompany.getJobs());
            companyRepository.save(existingCompany);
            return true;
        }
    }
}
