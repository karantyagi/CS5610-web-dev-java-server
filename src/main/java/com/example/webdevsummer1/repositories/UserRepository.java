package com.example.webdevsummer1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdevsummer1.models.User;

public interface UserRepository
extends CrudRepository<User, Integer>{
}