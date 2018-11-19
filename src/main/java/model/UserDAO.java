package model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.service.ServiceRegistry;

public class UserDAO {

    public static SessionFactory getSessionFactory() {

        // Creating Configuration Instance & Passing Hibernate Configuration File
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration.configure().buildSessionFactory();
        return sessionFactory;
    }

    public static String createUser(User newUser) {
        Session session = getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            System.out.println("A user with same credentials already exists!!");
        } finally {
            session.close();
        }
        return newUser.getUsername();
    }

    public static void deleteUser(User newUser) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(newUser);
        transaction.commit();
        session.close();
    }

    public static User getUser(String username) {
        Session session = getSessionFactory().openSession();
        User user = session.load(User.class, username);
        //session.close();
        return user;
    }

}
