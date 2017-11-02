package screensframework;

import Fatw.fatwa;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Factory {

    static SessionFactory factory;

    public static SessionFactory getFactory() {
        factory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
        return factory;
    }

    public static void close() {
        if (factory != null) {
            factory.close();
        }
    }
}
