package com.example.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.entity.EntityModel;


public interface EntityRepository extends CrudRepository<EntityModel,String> {
	EntityModel findByEmailIdIgnoreCase(String emailId);

}
