package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import entity.CustomerEntity;
import entity.ItemEntity;
import entity.OrderDetailEntity;
import entity.OrderEntity;

public class SessionFactoryConfiguration {

    private static SessionFactoryConfiguration sessionFactoryConfiguration;

    private SessionFactory sessionFactory;

    private SessionFactoryConfiguration() {
        Configuration configuration = new Configuration().configure()
        .addAnnotatedClass(CustomerEntity.class)
        .addAnnotatedClass(OrderEntity.class)
        .addAnnotatedClass(ItemEntity.class)
        .addAnnotatedClass(OrderDetailEntity.class);

        sessionFactory = configuration.buildSessionFactory();
    }

    public static SessionFactoryConfiguration getInstance() {
        return sessionFactoryConfiguration == null ? 
                sessionFactoryConfiguration = new SessionFactoryConfiguration()
                : sessionFactoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }

}