package com.example.microservices.review;


import com.example.microservices.company.Company;
import com.example.microservices.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CompanyService companyService;


    public List<Review> getAllReviews(Long companyId) {
        if (companyId != null) {
            return reviewRepository.findByCompanyId(companyId);
        } else {
            return null;
        }
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }



    public boolean createReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId);
        if (company == null) {
            return false;
        } else {
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
    }
    public boolean updateReview(Long companyId, Long id, Review updatedReview) {
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            updatedReview.setCompany(companyService.getCompanyById(id));
            updatedReview.setId(id);
            reviewRepository.save(updatedReview);
            return true;
        } else {
            return false;
        }
    }
    public boolean deleteReviewById(Long companyId,Long id) {
        if(companyService.getCompanyById(companyId)!=null && reviewRepository.existsById(id)) {
            Review review = reviewRepository.findById(id).orElse(null);
            Company company=review.getCompany();
            company.getReviews().remove(review);
            review.setCompany(null);
            companyService.updateCompany(companyId,company);
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
