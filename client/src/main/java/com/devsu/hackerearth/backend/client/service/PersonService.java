package com.devsu.hackerearth.backend.client.service;

import java.util.List;

import com.devsu.hackerearth.backend.client.model.dto.PersonDto;

public interface PersonService {

    public List<PersonDto> getAll();

    public PersonDto getById(Long id);

    public PersonDto create(PersonDto personDto);

    public PersonDto update(PersonDto personDto);

    public void deleteById(Long id);
}
