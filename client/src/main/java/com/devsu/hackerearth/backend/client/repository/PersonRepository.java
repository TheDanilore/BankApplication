package com.devsu.hackerearth.backend.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsu.hackerearth.backend.client.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
