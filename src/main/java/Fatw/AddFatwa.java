/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fatw;


import screensframework.Factory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author fathi
 */
public class AddFatwa {
    static SessionFactory factory;
   

    public AddFatwa() {

    }

    public int addFatwa(String hokm, String quren, String suna, String ijtihad) {
        fatwa ftw; 
        
        int index = -1;
        try {
            factory=Factory.getFactory();
                    
            //factory = new Configuration().configure("//hibernate.cfg.xml").addAnnotatedClass(fatwa.class).buildSessionFactory();
            Session session = factory.getCurrentSession();

            session.beginTransaction();
            ftw = new fatwa(hokm, quren, suna, ijtihad);
            session.save(ftw);
            index = ftw.getId();
            session.getTransaction().commit();
            session.close();
        } catch(Exception e){
            System.out.println("aaa"+e);
        } finally {
            //factory.close();
        }
        return index;
    }
    public static void close(){
        factory.close();
    }
}
