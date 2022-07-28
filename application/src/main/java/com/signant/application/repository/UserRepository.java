package com.signant.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.signant.application.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
