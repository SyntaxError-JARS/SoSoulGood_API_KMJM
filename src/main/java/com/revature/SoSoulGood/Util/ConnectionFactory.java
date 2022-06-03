package com.revature.SoSoulGood.Util;

import com.revature.SoSoulGood.Models.Customer;
import com.revature.SoSoulGood.Models.Order;
import com.revature.SoSoulGood.Models.CreditCard;
import com.revature.SoSoulGood.Models.Menu;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {

    private static SessionFactory sessionFactory;
    private static Session session;

    public static Session getSession() throws IOException {
        if(sessionFactory == null) {
            Configuration configuration = new Configuration();
            Properties props = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            props.load(loader.getResourceAsStream("hibernate.properties"));

            // Add properties to configuration
            configuration.setProperties(props);
            // ONE ADDITIONAL STEPS I NEED TO INCLUDE
            configuration.addAnnotatedClass(Order.class);
            configuration.addAnnotatedClass(Menu.class);
            configuration.addAnnotatedClass(Customer.class);
            configuration.addAnnotatedClass(CreditCard.class);

            // ServiceRegistry
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }

        if(session == null) {
            session = sessionFactory.openSession();
        }

        return session;
    }

    public static void closeSession() {
        session.close();
        session = null;

    }
}

