/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fatw;

import screensframework.Factory;
import Case.cas;
import java.math.BigInteger;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class GetFatwa {

    static Session session;
    static SessionFactory factory;

    public void connect() {
        factory = Factory.getFactory();
        //factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").addAnnotatedClass(fatwa.class).buildSessionFactory();

    }

    public fatwa getFatwa(int id) {
        connect();
         fatwa fatwa = new fatwa();
       try{
        Transaction tx = null;
        session = factory.openSession();
        tx = session.beginTransaction();
        session.flush();
        tx.commit();
       
        List<fatwa> f = session.createQuery(" from fatwa where id=" + id).list();
        for (fatwa ftw : f) {
            fatwa = ftw;
        }
        /*fatwa.setHokm(f.get(1).toString());
         fatwa.setQuren(f.get(2).toString());
         fatwa.setSuna(f.get(3).toString());
         fatwa.setSuna(f.get(4).toString());*/
       }catch(Exception e){
           
       }finally{
           close();
       }

        return fatwa;
    }

    public List<fatwa> getFatwa() {
        List<fatwa> fatwas = new ArrayList<>();
        try {
            connect();

           // factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").addAnnotatedClass(fatwa.class).buildSessionFactory();
            // Long lastId = ((BigInteger) session.createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult()).longValue();
            Transaction tx = null;
            session = factory.openSession();
            tx = session.beginTransaction();
            session.flush();
            tx.commit();
            fatwas = session.createQuery("from fatwa ").list();

            for (fatwa f : fatwas) {
                System.out.println("///" + f.toString());
            }

        } catch (Exception e) {

        }finally{
            close();
        }

        return fatwas;
    }

    public static void close() {
        try {
            if (factory != null) {
                session.close();
                factory.close();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

    }
  
}
