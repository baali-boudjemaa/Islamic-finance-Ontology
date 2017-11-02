/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Case;

import Fatw.fatwa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author fathi
 */
public class AddCase {
    static SessionFactory factory;
    public AddCase(){
        
    }
    public void addCase(String action, String benifit, String transaction,int index){
         cas c;
        try {
            factory = new Configuration().configure("/Case/hibernate.cfg.xml").addAnnotatedClass(cas.class).buildSessionFactory();
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            c = new cas(action, benifit, transaction, index);
            session.save(c);
            session.getTransaction().commit();
            session.close();
        } finally {
            
        }
    }
    public static void close(){
        factory.close();
    }
}
