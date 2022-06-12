package com.cs544.project.person.repository;

import com.cs544.project.person.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User, Long> {

}
