package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.Utility;

public class UserDAO {

    public static String createUser(User newUser) {
        Session session = Utility.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(newUser);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return newUser.getUsername();
    }

    public static void deleteUser(User newUser) {
        Session session = Utility.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(newUser);
        transaction.commit();
        session.close();
    }

    public static User getUser(String username) {
        Session session = Utility.getSessionFactory().openSession();
        User user = session.get(User.class, username);
        session.close();
        return user;
    }

    public static void updateUser(User user) throws Exception {
        Session session = Utility.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (Exception e) {
            throw new Exception();
        } finally {
            session.close();
        }
    }
}
