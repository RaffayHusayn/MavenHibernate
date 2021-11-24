package com.fclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLOutput;
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
        Configuration conf = new Configuration().configure().addAnnotatedClass(Book.class).addAnnotatedClass(Library.class).addAnnotatedClass(Publisher.class);
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

        Author camus = new Author();
        camus.setAuthorName("Albert Camus");
        camus.setAuthorAge(101);
        camus.setBooks(Arrays.asList("camus book 1", "camus book 2", "camus book 3"));


        Publisher oxford = new Publisher();
//        oxford.setPublisherId(10001);
        oxford.setPublisherName("Oxford Publishing");



        Publisher martyn = new Publisher();
//        martyn.setPublisherId(10002);
        martyn.setPublisherName("Martyn's Publishing");


        Book flowers4Algernon = new Book();
        flowers4Algernon.setAuthor(raffay);
        flowers4Algernon.setId(0);
        flowers4Algernon.setName("Flowers for Algernon");

        Book hitchhiker  = new Book();
        hitchhiker.setAuthor(camus);
        hitchhiker.setId(10);
        hitchhiker.setName("Hitchhiker's Guide to the Galaxy");

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
        oxford.setBooks(Arrays.asList(flowers4Algernon));
        martyn.setBooks(Arrays.asList(flowers4Algernon, theStranger));
        theStranger.setLibraryList(Arrays.asList(nyLib,queensLib ));
        theStranger.setPublisher(martyn);
        hitchhiker.setPublisher(oxford);
        hitchhiker.setLibraryList(Arrays.asList(nyLib));
        flowers4Algernon.setPublisher(martyn);
        flowers4Algernon.setLibraryList(Arrays.asList(nyLib,queensLib ));
        nyLib.setBooks(Arrays.asList(flowers4Algernon, theStranger));
        queensLib.setBooks(Arrays.asList(flowers4Algernon, theStranger));


//        Transaction tx = session.beginTransaction();
//        session.persist(flowers4Algernon);
//        session.persist(theStranger);
//        session.persist(hitchhiker);
//        tx.commit();


        Transaction tx1 = session.beginTransaction();
        Book bookRetrieved = session.get(Book.class,5);
        System.out.println(bookRetrieved.getName());
        Library libraryRetrieved = session.get(Library.class,1001 );
        System.out.println(libraryRetrieved.getName());
        List<Book> libraryBooks = libraryRetrieved.getBooks();
        for(Book b:libraryBooks){
            System.out.println(b.getName());
        }

//        Publisher publisherRetrieved = session.get(Publisher.class, 2);
//        List<Book> publisherBooks = publisherRetrieved.getBooks();
//        for(Book b: publisherBooks){
//            System.out.println(b.getName());
//        }

//        System.out.println(bookRetrieved.getLibraryList());
        tx1.commit();



//

    }
}
