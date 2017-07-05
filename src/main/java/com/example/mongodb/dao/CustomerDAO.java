package com.example.mongodb.dao;

import java.util.List;

import com.example.rest.Customer;

public interface CustomerDAO {
	
	public void create(Customer customer);
	
	public void removeById(String id);
	
	public List<Customer> getAll();
	
	public Customer getOneById(String id);
	
	public List<Customer> getManybyName(String name);
	
	public void update(String id, String changedName);
}
