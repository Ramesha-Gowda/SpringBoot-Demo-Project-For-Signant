package com.signant.application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.signant.application.model.Items;


public interface ItemRepository extends JpaRepository<Items, Long> {
	Optional<Items> findByName(String name);
}
