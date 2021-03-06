package com.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springdemo.entity.Customer;
import com.springdemo.service.CustomerService;
import com.springdemo.util.SortUtils.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject customer Service 
    @Autowired
	private CustomerService customerService;

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		//get the customer from dao
		List<Customer> theCustomer=customerService.getCustomers();
		
		//add the customer to model
		theModel.addAttribute("customers",theCustomer);
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		//create the model Attribute to bind the data
		Customer tHecustomer=new Customer();
		theModel.addAttribute("customer", tHecustomer);
		return "customer-form";
	}
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer tHeCustomer) {
		
		//save the Customer using our service
		customerService.saveCustomer(tHeCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId,
			                        Model theModel) {
		//get the Customer from service
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set customer as the model Attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		
		return "customer-form";
	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@ModelAttribute("customer") Customer tHeCustomer) {
		
		//save the Customer using our service
		customerService.saveCustomer(tHeCustomer);
		
		return "redirect:/customer/list";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId) {
		
		//delete the customer
		customerService.deleteCustomer(theId);
		return  "redirect:/customer/list";
	}
	
	@GetMapping("/list")
	public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {
		
		// get customers from the service
		List<Customer> theCustomers = null;
		
		// check for sort field
		if (sort != null) {
			int theSortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(theSortField);			
		}
		else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// add the customers to the model
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	
	
	
	
}


