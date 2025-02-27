package com.devsu.hackerearth.backend.client.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsu.hackerearth.backend.client.model.Person;
import com.devsu.hackerearth.backend.client.model.dto.PersonDto;
import com.devsu.hackerearth.backend.client.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> getAll() {
        // Get all persons
        List<Person> persons = personRepository.findAll();
        return persons.stream()
                .map(person -> new PersonDto(person.getId(), person.getName(), person.getDni(), person.getGender(),
                        person.getAge(),
                        person.getAddress(), person.getPhone()))
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getById(Long id) {
        // Get persons by id
        Optional<Person> personOptional = personRepository.findById(id);
        return personOptional
                .map(person -> new PersonDto(person.getId(), person.getName(), person.getDni(), person.getGender(),
                        person.getAge(),
                        person.getAddress(), person.getPhone()))
                .orElse(null);
    }

    @Override
    public PersonDto create(PersonDto personDto) {
        // Create person
        Person person = new Person(personDto.getName(), personDto.getDni(), personDto.getGender(),
                personDto.getAge(), personDto.getAddress(), personDto.getPhone());

        Person savedPerson = personRepository.save(person);
        return new PersonDto(savedPerson.getId(), savedPerson.getName(), savedPerson.getDni(), savedPerson.getGender(),
                savedPerson.getAge(),
                savedPerson.getAddress(), savedPerson.getPhone());
    }

    @Override
    public PersonDto update(PersonDto personDto) {
        // Update person
        Optional<Person> personOptional = personRepository.findById(personDto.getId());
        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setDni(personDto.getDni());
            person.setName(personDto.getName());
            person.setGender(personDto.getGender());
            person.setAddress(personDto.getAddress());
            person.setPhone(personDto.getPhone());

            Person updatedPerson = personRepository.save(person);
            return new PersonDto(updatedPerson.getId(), updatedPerson.getDni(), updatedPerson.getName(),
                    updatedPerson.getGender(),
                    updatedPerson.getAge(), updatedPerson.getAddress(), updatedPerson.getPhone());
        }
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // Delete person
        personRepository.deleteById(id);
    }

}
