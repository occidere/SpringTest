package com.example.mongodb.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

import com.example.mongodb.dao.CustomerDAO;
import com.example.mongodb.main.MongoDBMain;
import com.example.rest.Customer;

public class CustomerDAOImpl implements CustomerDAO{
	private MongoOperations mongoOps;
	private static final String COLLECTION = MongoDBMain.getCollection();
	public static final MongoClient mongo = MongoDBMain.getMongoClient();
	
	public CustomerDAOImpl(MongoOperations mongoOps){
		this.mongoOps=mongoOps;
    }
	
	public void create(Customer customer) {
		System.out.println("DAO: Adding new data");
		this.mongoOps.insert(customer, COLLECTION);
		System.out.println("DAO: Added!");
		
	}

	public void removeById(String id) {
		System.out.println("DAO: Querying data id:"+id);
		Query query = new Query(Criteria.where("_id").is(id));
		System.out.println("DAO: Deleting data id:"+id);
        WriteResult result = this.mongoOps.remove(query, Customer.class, COLLECTION);
        System.out.println("DAO: Deleted!");
		
	}

	public List<Customer> getAll() {
		System.out.println("DAO: Return all data in "+COLLECTION+" collection");
		return this.mongoOps.findAll(Customer.class, COLLECTION);
	}

	public Customer getOneById(String id) {
		System.out.println("DAO: Querying data id:"+id);
		Query query = new Query(Criteria.where("_id").is(id));
		System.out.println("DAO: Return data id:"+id);
        return this.mongoOps.findOne(query, Customer.class, COLLECTION);
	}

	public List<Customer> getManybyName(String name) {
		System.out.println("DAO: Querying all data name:"+name);
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		System.out.println("DAO: Return data");
		return mongoOps.find(query, Customer.class, COLLECTION);
	}

	public void update(String id, String changedName) {
		System.out.println("DAO: Querying data id :"+id);
		Query query = new Query(Criteria.where("_id").is(id));
		Update update = new Update();
		update.set("name", changedName);
		System.out.println("DAO: Updating data id: "+id);
		System.out.println("DAO: Changing name to "+changedName);
		this.mongoOps.findAndModify(query, update, Customer.class, COLLECTION);
		
	}



}