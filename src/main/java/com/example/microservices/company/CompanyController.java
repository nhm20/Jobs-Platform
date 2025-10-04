package com.example.microservices.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public ResponseEntity<List<Company>>findAll() {
        return ResponseEntity.ok(companyService.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<String> createCompany(@RequestBody Company company) {
        String response = companyService.createCompany(company);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        if (company != null) {
            return new ResponseEntity<>(company, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
        boolean isDeleted = companyService.deleteCompanyById(id);
        if (isDeleted) {
            return new ResponseEntity<>("Company deleted successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompany(@PathVariable Long id, @RequestBody Company updatedCompany) {
        boolean updated = companyService.updateCompany(id, updatedCompany);
        if (updated) {
            return new ResponseEntity<>("Company updated successfully!", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Company not found!", HttpStatus.NOT_FOUND);
        }
    }

}
