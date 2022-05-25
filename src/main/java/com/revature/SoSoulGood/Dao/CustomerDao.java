package com.revature.SoSoulGood.Dao;

import com.revature.SoSoulGood.Models.Customer;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerDao implements Crudable<Customer> {



    @Override
    public Customer create(Customer customerInfo) {
        return null;
    }

    @Override
    public ArrayList findAll() throws IOException {
        return null;
    }

    @Override
    public Customer findByID(String username) {
        return null;
    }

    @Override
    public boolean update(Customer updatedCustomer) {
        return false;
    }


    @Override
    public boolean delete(String username) {
        return false;
    }
}
