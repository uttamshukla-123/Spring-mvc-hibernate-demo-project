package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Customer;


public interface CustomerDoa {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer tHeCustomer);
	
	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

}
