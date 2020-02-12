package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	
	
	//need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<Customer> getCustomers() {
		
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName",Customer.class);
		//execute query and get the result list
		List<Customer> customers = theQuery.getResultList();
		//return the results
		
		return customers;
	}


	@Override
	public void saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(customer);

	}


	@Override
	public Customer getCustomer(int id) {
		// TODO Auto-generated method stub
		//get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		//now reatrive/read from the database using primary key
		Customer theCustomer = currentSession.get(Customer.class, id);
		
		return theCustomer;
	}


	@Override
	public void deleteCustomer(int id) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query theQuery = 
				currentSession.createQuery("delete from Customer where id =:customerId ");
		
		theQuery.setParameter("customerId", id);
		
		theQuery.executeUpdate();
		
	}

}
