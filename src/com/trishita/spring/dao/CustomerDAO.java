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

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();

    void saveCustomer(Customer thecustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
