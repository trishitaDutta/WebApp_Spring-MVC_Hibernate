/**
 * Simple Web Application using Spring MVC and Hibernate
 * Frontend design - Bootstrap3
 * Created by IntelliJ IDEA.
 * User: Trishita Dutta
 * Date: 24-07-2020
 * Time: 18:02
 */
package com.trishita.spring.service;

import com.trishita.spring.dao.CustomerDAO;
import com.trishita.spring.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    //need to inject CustomerDAO
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() {

        //delegate the method call to CustomerDAO
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public void saveCustomer(Customer thecustomer) {

        customerDAO.saveCustomer(thecustomer);
    }

    @Override
    @Transactional
    public Customer getCustomer(int theId) {

        return customerDAO.getCustomer(theId);
    }

    @Override
    @Transactional
    public void deleteCustomer(int theId) {

        //delegate the method to CustomerDAO
       customerDAO.deleteCustomer(theId);
    }

    @Override
    @Transactional
    public List<Customer> searchCustomers(String theSearchName) {

        return customerDAO.searchCustomers(theSearchName);
    }
}
