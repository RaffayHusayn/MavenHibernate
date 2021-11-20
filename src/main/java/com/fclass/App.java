package com.fclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration conf = new Configuration().configure().addAnnotatedClass(Book.class).addAnnotatedClass(Library.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();

        Author raffay = new Author();
        raffay.setAuthorName("Raffay");
        raffay.setAuthorAge(24);
        raffay.setBooks(Arrays.asList("raffay book 1", "raffay book 2", "raffay book 3"));


        Author herbert = new Author();
        herbert.setAuthorName("Frank Herbert");
        herbert.setAuthorAge(100);
        herbert.setBooks(Arrays.asList("herbert book 1", "herbert book 2", "herbert book 3"));


        Book flowers4Algernon = new Book();
        flowers4Algernon.setAuthor(raffay);
        flowers4Algernon.setId(0);
        flowers4Algernon.setName("Flowers for Algernon");

        Book theStranger = new Book();
        theStranger.setAuthor(herbert);
        theStranger.setId(5);
        theStranger.setName("The Stranger");

        Library nyLib  = new Library();
        nyLib.setId(1001);
        nyLib.setCity("New York");
        nyLib.setName("New York Public Library");

        Library queensLib  = new Library();
        queensLib.setId(1002);
        queensLib.setCity("Queens");
        queensLib.setName("Queens Public Library");

        //setting many to many relations list for library and books
        theStranger.setLibraryList(Arrays.asList(nyLib,queensLib ));
        flowers4Algernon.setLibraryList(Arrays.asList(nyLib,queensLib ));
        nyLib.setBooks(Arrays.asList(flowers4Algernon, theStranger));
        queensLib.setBooks(Arrays.asList(flowers4Algernon, theStranger));


//        Book raffay_book = new Book();

        Transaction tx = session.beginTransaction();
        session.save(flowers4Algernon);
        session.save(theStranger);
        session.save(nyLib);
        session.save(queensLib);
//        raffay_book = session.get(Book.class , 0);
        tx.commit();


//        Transaction tx1 = session.beginTransaction();
//        tx1.commit();

//        System.out.println(raffay_book);



    }
}
