package com.springdemo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springdemo.dao.CustomerDoa;
import com.springdemo.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	//injet the customer dao
	@Autowired
	private CustomerDoa custdao;

	@Override
	@Transactional
	public List<Customer> getCustomers() {
		return custdao.getCustomers();
	}

	@Override
	@Transactional
	public void saveCustomer(Customer tHeCustomer) {
		custdao.saveCustomer(tHeCustomer);
		
	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		
		return custdao.getCustomer(theId);
	}

	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		custdao.deleteCustomer(theId);
		
	}

	



}
