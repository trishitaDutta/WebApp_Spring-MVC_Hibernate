/**
 * Simple Web Application using Spring MVC and Hibernate
 * Frontend design - Bootstrap3
 * Created by IntelliJ IDEA.
 * User: Trishita Dutta
 * Date: 24-07-2020
 * Time: 18:02
 */

package com.trishita.spring.controller;

import com.trishita.spring.entity.Customer;
import com.trishita.spring.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * step:(1)  Inject the DAO into the controller class
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    //since we have introduced Service layer, need to Inject Service to the controller class
    //we will no longer use DAO injection to the controller class directly
    @Autowired
    private CustomerService customerService; //spring will scan for a component that implements CustomerDAO interface and inject it

    /**
     * Model is used to pass the data between controller ans view
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listCustomers(Model model) {

        //get the customers from the CustomerService
        List<Customer> customerList = customerService.getCustomers();
        System.out.println(customerList);

        //add the customers to the Spring MVC model as a MODEL_ATTRIBUTE
        model.addAttribute("customers", customerList);

        return "listCustomers";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model){

        //create model attribute to bind form data
        Customer customer = new Customer();

        // Add customer object to the model as a model_attribute
        model.addAttribute("customer",customer);

        return "customerForm";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer") Customer thecustomer){

        //save the customer using our service
        customerService.saveCustomer(thecustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId, Model model){

        //get the customer from the Customer Service
        Customer thecustomer = customerService.getCustomer(theId);

        //set customer as a model attribute to pre-populate the form
        model.addAttribute("customer", thecustomer);

        //send over to our form
        return "customerForm";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId) {

        // delete the customer
        customerService.deleteCustomer(theId);

        return "redirect:/customer/list";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {

        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "listCustomers";
    }
}
