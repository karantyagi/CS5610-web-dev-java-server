package com.example.webdevsummer1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer1.models.Hello;

public interface HelloRepository extends CrudRepository<Hello, Integer> {
	
}


