package com.revature.SoSoulGood.Services;

import com.revature.SoSoulGood.Dao.OrderDao;
import com.revature.SoSoulGood.Exceptions.ResourcePersistanceException;
import com.revature.SoSoulGood.Models.Order;
import com.revature.SoSoulGood.Util.Logger;

import java.util.List;


public class OrderServices {
    private OrderDao orderDao;
    private Logger logger = Logger.getLogger();
    public OrderServices(OrderDao orderDao) {
        this.orderDao=orderDao;
    }

    public List<Order> readAll(){
        // TODO: What MenuDao intellisense telling me?
        List<Order> order = orderDao.findAll();
        return order;
    }


    public Order readById(String id) throws ResourcePersistanceException {

        Order order = orderDao.findById(id);
        return order;
    }

    public Order update(Order updatedOrder) {
        if (!orderDao.update(updatedOrder)) {
            return null;
        }

        return updatedOrder;
    }

    public boolean delete(String username) {
        return orderDao.delete(username);
    }

    public boolean validateIdNotUsed(String username) {
        return orderDao.checkId(username);
    }

    public Order create(Order newOrder) {
        System.out.println("Order trying to be registered: " + newOrder);
        logger.info("Order trying to be registered: ");

        System.out.println("before issue?");
        Order persistedOrder = orderDao.create(newOrder);
        System.out.println("before issue????");

        if (persistedOrder == null) {
            throw new ResourcePersistanceException("Order was not persisted to the database upon registration");
        }
//        logger.info("Order has been persisted: " + newCustomer);
        return persistedOrder;
    }
}


