package com.cs544.project.person.service;

import com.cs544.project.person.entity.Applicant;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.repository.IUserDao;
import com.cs544.project.person.value_object.ApplicationsResponseTemplate;
import com.cs544.project.person.value_object.JobApplication;
import com.cs544.project.person.value_object.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ApplicantService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    RestTemplate restTemplate;

    public User register(Applicant applicant){
        return userDao.save(applicant);
    }

    public User getApplicant(Long id){
        return userDao.findById(id).orElse(null);
    }

    public User updateApplicant(Long id, Applicant applicant){
        Applicant storedApplicant = (Applicant) userDao.findById(id).orElse(null);

        if(storedApplicant != null){
            if(Objects.nonNull(applicant.getName()) && !"".equalsIgnoreCase(applicant.getName())){
                storedApplicant.setName(applicant.getName());
            }
            if(Objects.nonNull(applicant.getEmail()) && !"".equalsIgnoreCase(applicant.getEmail())){
                storedApplicant.setEmail(applicant.getEmail());
            }
            if(Objects.nonNull(applicant.getPassword()) && !"".equalsIgnoreCase(applicant.getPassword())){
                storedApplicant.setPassword(applicant.getPassword());
            }
            if(Objects.nonNull(applicant.getBiography()) && !"".equalsIgnoreCase(applicant.getBiography())){
                storedApplicant.setBiography(applicant.getBiography());
            }
            userDao.save(storedApplicant);
        }
        return storedApplicant;
    }

    public void deleteApplicant(Long id){
        userDao.deleteById(id);
    }

    public List<ResponseTemplate> getAllJobsFromCompany(Long companyId) {
        return restTemplate.getForObject("http://JOB-SERVICE/jobs/company/" + companyId, ArrayList.class);
    }

    public List<ApplicationsResponseTemplate> getAllApplicantsForApplicant(Long applicantId) {
        return restTemplate.getForObject("http://APPLICATIONS/applications/applicant/" + applicantId, ArrayList.class);
    }

    public JobApplication applyForJob(JobApplication application) {
        User applicant = userDao.findById(application.getApplicantId()).orElse(null);
        if(applicant != null) {
            application.setApplicantName(applicant.getName());
            application.setApplicantContact(applicant.getEmail());
        }
        return restTemplate.postForObject("http://APPLICATIONS/applications/add", application, JobApplication.class);
    }

    public void deleteApplicationForApplicant(Long applicationId) {
        restTemplate.delete("http://APPLICATIONS/applications/application/"+applicationId, JobApplication.class);
    }

    public ResponseTemplate getJobsByTitle(String jobTitle) {
        return restTemplate.getForObject("http://JOB-SERVICE/jobs/job/" + jobTitle, ResponseTemplate.class);
    }

    public ApplicationsResponseTemplate getApplicationById(Long applicationId) {
        return restTemplate.getForObject("http://APPLICATIONS/applications/" + applicationId, ApplicationsResponseTemplate.class);
    }
}
