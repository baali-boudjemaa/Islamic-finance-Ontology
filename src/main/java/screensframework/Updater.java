/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package screensframework;

import Case.cas;
import Fatw.fatwa;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import static screensframework.Updater.session;

/**
 *
 * @author fathi
 */
public class Updater {

    static Session session;
    static SessionFactory factory;

    public static void connect() {
        try{
           factory = Factory.getFactory(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        
        //factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").addAnnotatedClass(fatwa.class).buildSessionFactory();

    }

    public static void update(cas cs, fatwa ftw) {

        try (Session session = factory.openSession()) {
            session.beginTransaction();

            /*session.save(cs);
             session.save(cs);
             session.getTransaction().commit();*/
            // gets the book entity back
            //cas book = (cas) session.get(cas.class, bookId);*/
        }
    }

    public static void updateCase(cas cs) {
        connect();
        try  {
            session = factory.openSession();
            session.beginTransaction();
            String query = "update cas set act= :act , benifit= :benifit, transactio= :transactio  where id= :id ";
            Query h = session.createQuery(query);
            session.createQuery(query);
            h.setInteger("id", cs.getId());
            h.setString("act", cs.getAction());
            h.setString("benifit", cs.getBenifit());
            h.setString("transactio", cs.getTransaction());
            
            h.executeUpdate();
            session.close();
        } catch (Exception e) {

        }finally{
            close();
        }
    }

    public static void updateFatwa(fatwa ftw) {
        connect();
        try  {
            session = factory.openSession();
            session.beginTransaction();
            String query = "update fatwa set hokm= :hokm ,quren= :quren , suna= :suna , ijtihad = :ijtihad  where id= :id ";

            Query h = session.createQuery(query);

            h.setInteger("id", ftw.getId());
            h.setString("hokm", ftw.getHokm());
            h.setString("quren", ftw.getQuren());
            h.setString("suna", ftw.getIjtihad());
            h.setString("ijtihad", ftw.getIjtihad());

            h.executeUpdate();
            session.close();
        } catch (Exception e) {

        }finally{
            close();
        }
    }
    
    public static void close() {
        try {
            if (factory != null) {
                factory.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
}
