package model;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.Utility;

public class LicenseDAO {

    public static License retrieve(String licenseId) {
        Session session = Utility.getSessionFactory().openSession();
        License license = session.get(License.class, licenseId);
        session.close();
        return license;
    }

    public static void create(License license) {
        Session session = Utility.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.save(license);
            transaction.commit();
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            session.close();
        }
    }


}
