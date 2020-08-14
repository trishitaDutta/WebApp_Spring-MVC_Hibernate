/**
 * Simple Web Application using Spring MVC and Hibernate
 * Frontend design - Bootstrap3
 * Created by IntelliJ IDEA.
 * User: Trishita Dutta
 * Date: 24-07-2020
 * Time: 18:02
 */
package com.trishita.spring.service;

import com.trishita.spring.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    public void saveCustomer(Customer thecustomer);

    Customer getCustomer(int theId);

    public void deleteCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
