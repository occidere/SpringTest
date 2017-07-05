package com.example.rest;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.example.mongodb.dao.*;

@Named
@Path("/")
public class CustomerRest {
	private static List<Customer> customers = new ArrayList<>();
	
	private static ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
	private static CustomerDAO customerDAO = ctx.getBean("customerDAO", CustomerDAO.class);
	
	static{
		Customer customer1 = new Customer(1, "Customer 1", "customer1@gmail.com", "1234");
		Customer customer2 = new Customer(2, "Customer 2", "customer2@gmail.com", "1234");
		Customer customer3 = new Customer(3, "Customer 3", "customer3@gmail.com", "1234");
		Customer customer4 = new Customer(4, "Customer 4", "customer4@gmail.com", "1234");
		customerDAO.create(customer1);
		customerDAO.create(customer2);
		customerDAO.create(customer3);
		customerDAO.create(customer4);
	}
	
//	@GET
	@POST
	@Path("login")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@QueryParam("email") String email, @QueryParam("password") String password){
		for(Customer c : customerDAO.getAll()){
			//System.out.printf("c.getEmail()=%s, c.getPassword()=%s, email=%s, password=%s\n", c.getEmail(), c.getPassword(), email, password);
			if(email.equals(c.getEmail()) && password.equals(c.getPassword())){
				return c;
			}
		}
		return new Customer(-1, "Invalid", "Invalid", "Invalid");
	}

//	@GET
	@POST
	@Path("getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(@QueryParam("id") long id){
		for(Customer c : customerDAO.getAll()){
			if(id == c.getId()){
				return c;
			}
		}
		return new Customer(-1, "Invalid", "Invalid", "Invalid");
	}
	
//	@GET
	@POST
	@Path("register")
	@Produces(MediaType.TEXT_PLAIN)
	public String setCustomer(@QueryParam("id") long id, @QueryParam("name") String name, 
			@QueryParam("email") String email, @QueryParam("password") String password){
		Customer newCustomer = new Customer(id, name, email, password);
		customerDAO.create(newCustomer);
		return "Adding new customer successfully";
	}
	
}
