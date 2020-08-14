/**
 * Simple Web Application using Spring MVC and Hibernate
 * Frontend design - Bootstrap3
 * Created by IntelliJ IDEA.
 * User: Trishita Dutta
 * Date: 24-07-2020
 * Time: 18:02
 */
package com.trishita.spring.dao;

import com.trishita.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

/**
 * @Transactional annotation automatically begin and end transaction for your Hibernate code
 * no need to write explicitly
 * <p>
 * session.beginTransaction();---------|    X
 * session.getTransaction.commit();----|    X
 * @Repository annotation applicable for DAO implementation class
 * Spring will automatically register this DAOImplementation class
 * thanks to Spring Component scanning!
 */
@Repository
public class CustomerDAOImpl implements CustomerDAO {

    //need to inject Hibernate Session Factory
    @Autowired
    public SessionFactory sessionFactory;


    @Override
    public List<Customer> getCustomers() {

        //get the current Hibernate Session
        Session session = sessionFactory.getCurrentSession();

        //create query to retrieve customer data
        //Query<Customer> customerQuery = session.createQuery("from Customer",Customer.class);

        // ---------ADVANCED---------
        //create query to retrieve customer sorted by first name
        Query<Customer> customerQuery =
                session.createQuery("from Customer order by firstName", Customer.class);

        //execute query and get the result
        List<Customer> customerList = customerQuery.getResultList();
        System.out.println("CustomerDAO: " + customerList);

        //return the results
        return customerList;
    }

    @Override
    public void saveCustomer(Customer thecustomer) {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //save the customer
        //so, hibernate will check if this is a new customer or an existing one
        //saveOrUpdate() method we can save a new customer or we can update an existing customer
        session.saveOrUpdate(thecustomer);
    }

    @Override
    public Customer getCustomer(int theId) {

        //get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        //retrieve the Customer from the database using primary key (ID)
        Customer customer = session.get(Customer.class, theId);

        //return the customer
        return customer;
    }

    @Override
    public void deleteCustomer(int theId) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        // only search by name if theSearchName is not empty
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery = currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        } else {
            // theSearchName is empty ... so just get all customers
            theQuery = currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;

    }
}

