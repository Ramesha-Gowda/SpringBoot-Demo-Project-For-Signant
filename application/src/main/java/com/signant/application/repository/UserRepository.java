package com.signant.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.signant.application.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByEmail(String email);
}
