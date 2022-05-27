package com.springdemo.dao;

import java.util.List;

import com.springdemo.entity.Customer;


public interface CustomerDoa {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer tHeCustomer);

}
