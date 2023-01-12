package com.springbootmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.springbootmongodb.collection.Person;

@Repository
public interface PersonRepository extends MongoRepository<Person, String>{

	@Query("{'firstName':?0}")
	List<Person> findByName(String firstName);

	@Query(value="{'firstName':?0}",fields="{'lastName':0,'age':0,'hobbies':0,'personId':0}")
	List<Person> findOnlyName(String firstName);

}
