package com.revature.SoSoulGood.Services;

import com.revature.SoSoulGood.Dao.CreditCardDao;
import com.revature.SoSoulGood.Exceptions.AuthenticationException;
import com.revature.SoSoulGood.Exceptions.InvalidRequestException;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Models.CreditCard;
import com.revature.SoSoulGood.Util.Logger;

import java.util.List;


public class CreditCardServices {

    private CreditCardDao creditCardDao;
    private Logger logger = Logger.getLogger();

    public CreditCardServices(CreditCardDao creditCardDao) {
        this.creditCardDao = creditCardDao;
    }

    public List<CreditCard> readAll(){

        List<CreditCard> creditCards = creditCardDao.findAll();
        return creditCards;
    }

    public CreditCard readById(String id) throws ResourcePersistanceException {

        CreditCard creditCard = creditCardDao.findById(id);
        return creditCard;
    }


    public CreditCard update(CreditCard updatedCreditCard) {
        if (!creditCardDao.update(updatedCreditCard)){
            return null;
        }

        return updatedCreditCard;
    }


    public boolean delete(String email) {
        return creditCardDao.delete(email);
    }

    public boolean validateEmailNotUsed(String email){
        return creditCardDao.checkEmail(email);
    }

    public CreditCard create(CreditCard newCreditCard){
        logger.info("CreditCard trying to be registered: " + newCreditCard);
        if(!validateInput(newCreditCard)){ // checking if false
            // TODO: throw - what's this keyword?
            throw new InvalidRequestException("User input was not validated, either empty String or null values");
        }

        // TODO: Will implement with JDBC (connecting to our database)
        if(validateEmailNotUsed(newCreditCard.getUsername())){
            throw new InvalidRequestException("User email is already in use. Please try again with another email or login into previous made account.");
        }

        CreditCard persistedCreditCard = creditCardDao.create(newCreditCard);

        if(persistedCreditCard == null){
            throw new ResourcePersistanceException("Credit card was not persisted to the database upon registration");
        }
        logger.info("CreditCard has been persisted: " + newCreditCard);
        return persistedCreditCard;
    }

    // intelisense told me to input this
    private boolean validateInput(CreditCard newCreditCard) {
        return false;
    }


}
