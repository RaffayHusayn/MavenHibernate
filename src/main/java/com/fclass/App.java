package com.fclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration conf = new Configuration().configure().addAnnotatedClass(Book.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();

        Book flowers4Algernon = new Book();
        flowers4Algernon.setAuthor("Raffay");
        flowers4Algernon.setId(0);
        flowers4Algernon.setName("Flowers for Algernon");

        Book theStranger = new Book();
        theStranger.setAuthor("Albert Camus");
        theStranger.setId(5);
        theStranger.setName("The Stranger");

        Book raffay_book = new Book();

        Transaction tx = session.beginTransaction();
//        session.save(flowers4Algernon);
//        session.save(theStranger);
        raffay_book = session.get(Book.class , 0);
        tx.commit();


//        Transaction tx1 = session.beginTransaction();
//        tx1.commit();

        System.out.println(raffay_book);



    }
}
