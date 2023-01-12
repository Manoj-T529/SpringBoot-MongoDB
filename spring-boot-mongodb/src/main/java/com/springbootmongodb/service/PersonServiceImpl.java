package com.springbootmongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootmongodb.collection.Person;
import com.springbootmongodb.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonRepository personRepository;
	
	@Override
	public Person save(Person person) {
		// TODO Auto-generated method stub
		return personRepository.save(person);
	}

	@Override
	public List<Person> findAll() {
		// TODO Auto-generated method stub
		return personRepository.findAll();
	}

	@Override
	public List<Person> find(String firstName) {
		// TODO Auto-generated method stub
		return personRepository.findByName(firstName);
	}

	@Override
	public List<Person> findOnlyName(String firstName) {
		// TODO Auto-generated method stub
		return personRepository.findOnlyName(firstName);
	}

}
