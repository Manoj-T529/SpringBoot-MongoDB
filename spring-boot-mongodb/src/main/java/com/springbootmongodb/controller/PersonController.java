package com.springbootmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootmongodb.collection.Person;
import com.springbootmongodb.service.PersonService;

import java.util.*;

@RestController
@RequestMapping("/api")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private MongoTemplate mongoTemplate;

	@PostMapping("/save")
	public Person addPerson(@RequestBody Person person) {
		return personService.save(person);
	}

	@GetMapping("/getAll")
	public List<Person> findAll() {
		return personService.findAll();
	}

	@GetMapping("/get/{lastName}")
	public List<Person> find(@PathVariable(value = "lastName") String lastName) {
		Query query = new Query();

		query.addCriteria(Criteria.where("lastName").is(lastName));

		return mongoTemplate.find(query, Person.class);

	}

	@GetMapping("/getName/{name}")
	public List<Person> findByName(@PathVariable(value = "name") String firstName) {

		return personService.find(firstName);

	}

	@GetMapping("/getOnlyName/{name}")
	public List<Person> findByOnlyName(@PathVariable(value = "name") String firstName) {

		return personService.findOnlyName(firstName);

	}

	@GetMapping("/getPerson/{age}")
	public List<Person> findByAge(@PathVariable(value = "age") int age) {

		MatchOperation matchOperation = Aggregation.match(new Criteria("age").is(age));

		SortOperation sortOperation = Aggregation.sort(Sort.by(Sort.Direction.DESC, "age"));

		Aggregation aggregation = Aggregation.newAggregation(matchOperation, sortOperation);

		AggregationResults output = mongoTemplate.aggregate(aggregation, "person", Person.class);

		return output.getMappedResults();

	}
}
