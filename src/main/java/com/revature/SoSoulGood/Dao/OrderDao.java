package com.revature.SoSoulGood.Dao;

import com.revature.SoSoulGood.Models.Order;
import com.revature.SoSoulGood.Util.ConnectionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.util.List;
public class OrderDao {

    public Order create(Order newOrder) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newOrder);
            transaction.commit();
            return newOrder;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public List<Order> findAll() {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            List<Order> order = session.createQuery("FROM Orders").list();
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }


    public Order findById(String id) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Order order = session.get(Order.class, Integer.valueOf(id));
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean update(Order updatedOrder) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedOrder);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }


    public boolean delete(String id) {
        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Order order = session.load(Order.class, Integer.valueOf(id));
            session.remove(order);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }

    public Order authenticateOrder(String id, String password){

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Orders where id= :id and password= :password");
            query.setParameter("id", id);
            query.setParameter("password", password);
            Order order = (Order) query.uniqueResult();
            transaction.commit();
            return order;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionFactory.closeSession();
        }

    }
    public boolean checkId(String id) {

        try {
            Session session = ConnectionFactory.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Orders where id= :id");
            query.setParameter("id", id);
            Order order = (Order) query.uniqueResult();
            transaction.commit();
            if(order == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionFactory.closeSession();
        }
    }

}
