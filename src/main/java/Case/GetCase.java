/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Case;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import screensframework.Dialogue;
import screensframework.Factory;
import static screensframework.ScreensFramework.cases;

/**
 *
 * @author fathi
 */
public class GetCase {

    static SessionFactory factory;
    static Session session;

    public GetCase() {

    }

    public void connect() {
        factory = Factory.getFactory();
    }

    public List<cas> getCases() {

        List<cas> cases = new ArrayList<>();
        try {
            connect();
            //factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").addAnnotatedClass(cas.class).buildSessionFactory();
            Transaction tx = null;
            session = factory.openSession();
            tx = session.beginTransaction();
            session.flush();
            tx.commit();
            cases = session.createQuery(" from  cas ").list();

            for (cas cv : cases) {
                System.out.println("aa;:" + cv.toString());
            }

           // session.close();
        } catch (Exception e) {
            e.printStackTrace();
            // System.err.println("vvvvvv"+e);
        }finally{
            close();
        }

        return cases;
    }

    public static void close() {

        if (factory != null) {
            session.close();
            factory.close();
        }

    }
}
