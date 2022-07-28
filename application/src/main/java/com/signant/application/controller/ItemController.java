package com.signant.application.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.signant.application.model.Items;
import com.signant.application.repository.ItemRepository;

@RestController
@RequestMapping("/api")
public class ItemController {
	private final Logger log = LoggerFactory.getLogger(ItemController.class);

	private ItemRepository itemRepository;

	public ItemController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	};

	@GetMapping("/items")
	Collection<Items> groups() {
		return itemRepository.findAll();
	};
	
	@GetMapping("/items/{id}")
	public Optional<Items> getGroup(@PathVariable Long id) {
		Optional<Items> item= itemRepository.findById(id);
		return item;
		
	};
	@GetMapping("/items/getByName")
	@ResponseBody
	public Optional<Items> getGroup(@RequestParam String name) {
		Optional<Items> item= itemRepository.findByName(name);
		return item;
		
	};
	
	@PostMapping("/items")
    ResponseEntity<Items> createGroup(@Validated @RequestBody Items item) throws URISyntaxException {
        log.info("Request to create group: {}", item);
        Items result = itemRepository.save(item);
        return ResponseEntity.created(new URI("/api/items/" + result.getId()))
                .body(result);
    };

	@DeleteMapping("/items/{id}")
	public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
		log.info("Request to delete Item: {}", id);
		itemRepository.deleteById(id);
		return ResponseEntity.ok().build();
	};

	@PutMapping("/items/{id}")
	ResponseEntity<Items> updateGroup(@Validated @RequestBody Items item) {
		log.info("Request to update Item: {}", item);
		Items result = itemRepository.save(item);
		return ResponseEntity.ok().body(result);
	};

};
