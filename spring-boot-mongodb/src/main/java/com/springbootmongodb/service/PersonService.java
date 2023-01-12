package com.springbootmongodb.service;

import java.util.List;

import com.springbootmongodb.collection.Person;

public interface PersonService {

	Person save(Person person);

	List<Person> findAll();

	List<Person> find(String firstName);

	List<Person> findOnlyName(String firstName);

}
