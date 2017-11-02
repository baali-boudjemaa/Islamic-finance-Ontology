/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Case.cas;
import Fatw.fatwa;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author fathi
 */
public class Insert {

    public static void insert(cas cs,fatwa ftw) {
        try (Session session = Factory.getFactory().getSessionFactory().openSession()) {
            session.beginTransaction();
            cs.setFtw(ftw);
            
            /* String query="update fatwa set hokm= :n where id= :m ";
            
            Query h = session.createQuery(query);
            h.setString("n", "yyyyyyyyyyyy");
            h.setInteger("m", 8);
            h.executeUpdate();*/
            
            //session.save(cs);
            session.save(cs);
            session.getTransaction().commit();
            // gets the book entity back
            //cas book = (cas) session.get(cas.class, bookId);*/
        }
    }
    public static void close(){
        try{
            
        }catch(Exception e){
            
        }
    }
}
