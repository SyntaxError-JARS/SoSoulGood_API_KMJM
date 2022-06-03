package com.revature.SoSoulGood.Services;

import com.revature.SoSoulGood.Exceptions.InvalidRequestException;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Exceptions.AuthenticationException;
import com.revature.SoSoulGood.Dao.CustomerDao;
import com.revature.SoSoulGood.Models.Customer;
import com.revature.SoSoulGood.Util.Logger;

import java.util.List;

public class CustomerServices {

    private CustomerDao customerDao;
    private Logger logger = Logger.getLogger();
    public CustomerServices(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }


    public List<Customer> readAll(){
        // TODO: What CustomerDao intellisense telling me?
        List<Customer> customers = customerDao.findAll();
        return customers;
    }


    public Customer readById(String username) throws ResourcePersistanceException{

        Customer customer = customerDao.findByUsername(username);
        return customer;
    }

    public Customer update(Customer updatedCustomer) {
        if (!customerDao.update(updatedCustomer)){
            return null;
        }

        return updatedCustomer;
    }

    public boolean delete(String username) {
        return customerDao.delete(username);
    }

    public boolean validateIdNotUsed(String username){
        return customerDao.checkUsername(username);
    }

    public Customer create(Customer newCustomer){
        System.out.println("Customer trying to be registered: " + newCustomer);
        logger.info("Customer trying to be registered: ");

        System.out.println("before issue?");
        Customer persistedCustomer = customerDao.create(newCustomer);
        System.out.println("before issue????");

        if(persistedCustomer == null){
            throw new ResourcePersistanceException("Customer was not persisted to the database");
        }
//        logger.info("Customer was persisted: " + newCustomer);
        return persistedCustomer;
    }

    public boolean validateInput(Customer newCustomer) {
        logger.debug("Validating Customer: " + newCustomer);
        if(newCustomer == null) return false;
        if(newCustomer.getUsername() == null || newCustomer.getUsername().trim().equals("")) return false;
        if(newCustomer.getfname() == null || newCustomer.getfname().trim().equals("")) return false;
        if(newCustomer.getlname() == null || newCustomer.getlname().trim().equals("")) return false;
        return newCustomer.getPassword() == null || newCustomer.getPassword().trim().equals("");
    }

    public Customer authenticateCustomer(String username, String password){

        if(password == null || password.trim().equals("") || username == null || username.trim().equals("")) {
            throw new InvalidRequestException("Either username or password is an invalid. Please try again");
        }

        Customer authenticatedCustomer = customerDao.authenticateCustomer(username, password);

        if (authenticatedCustomer == null){
            throw new AuthenticationException("Unauthenticated user, information provided was not correct.");
        }

        return authenticatedCustomer;

    }

}
