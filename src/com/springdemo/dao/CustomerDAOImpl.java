package com.springdemo.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDoa {

	// need to inject the session factory
	@Autowired(required = true)
	private SessionFactory sessionfactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer tHeCustomer) {
		
		// get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		
		//save the Customer
		currentSession.saveOrUpdate(tHeCustomer);
		
	}

	@Override
	public Customer getCustomer(int theId) {
		
		// get the current hibernate session
		Session currentSession = sessionfactory.getCurrentSession();
		
		//now retrieve \read from database using primary key
		Customer theCustomer=currentSession.get(Customer.class, theId);
		
		return theCustomer;
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the current hibernate session
				Session currentSession = sessionfactory.getCurrentSession();
		
		//delete the object with primary key
			  Query theQuery=currentSession.createQuery("delete from Customer where id=:customerId");
			  theQuery.setParameter("customerId", theId);
			  theQuery.executeUpdate();
	}
}







