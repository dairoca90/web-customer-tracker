package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	
	private CustomerService theService;
	
	@Autowired
	public CustomerController(CustomerService theService) {
		this.theService = theService;
	}

	@GetMapping("/list")
	public String listCustomer(Model theModel) {
		//get the customer from db
		List<Customer> theCustomers = theService.getCustomers();
		//add customers to model
		
		theModel.addAttribute("customers",theCustomers);
		
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		//create model attribubte to bind data
		Customer theCustomer = new Customer();
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	@PostMapping(value="/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		
		this.theService.saveCustomer(customer);
		
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/list/{customerId}")
	public String showFormForUpdate(@PathVariable(value="customerId") int id,
																	Model theModel) {
		//Get the customer from the service
		Customer theCustomer = theService.getCustomer(id);
		//set the customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer",theCustomer);
		//send over to out form
		
		return "customer-form";
	}
	
	@GetMapping("/list/delete/{customerId}")
	public String deleteCustomer(@PathVariable(value="customerId") int id) {
		//Delete the customer
		theService.deleteCustomer(id);
		//set the customer as a model attribute to pre-populate the form
		
		return "redirect:/customer/list";
	}
	
	
}
