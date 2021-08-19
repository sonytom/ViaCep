package com.example.spring.repository;



import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.example.spring.entity.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);
  
  Collection<Customer> findAll()throws DataAccessException;

  Customer findById(long id);
}

