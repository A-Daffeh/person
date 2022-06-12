package com.cs544.project.person.service;

import com.cs544.project.person.entity.Applicant;
import com.cs544.project.person.entity.User;
import com.cs544.project.person.repository.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ApplicantService {

    @Autowired
    private IUserDao userDao;

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

}
