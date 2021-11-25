package com.fclass;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Configuration conf = new Configuration().configure().addAnnotatedClass(Book.class).addAnnotatedClass(Library.class).addAnnotatedClass(Publisher.class).addAnnotatedClass(Borrower.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        SessionFactory factory = conf.buildSessionFactory(serviceRegistry);
        Session session = factory.openSession();

        Borrower borrower1 = new Borrower();
        borrower1.setName("anil kapoor");
        borrower1.setAge(54);

        Borrower borrower2 = new Borrower();
        borrower2.setName("prem chopra");
        borrower2.setAge(100);


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


        Transaction tx = session.beginTransaction();



        //------------------HQL------------------


        Query q1 = session.createQuery("from Borrower");
        List<Borrower> borrowers= q1.list();
        for(Borrower b: borrowers){
            System.out.println("Name : " + b.getName() +  " Age : "+ b.getAge());
        }










//        session.persist(flowers4Algernon);
//        session.persist(theStranger);
//        session.persist(hitchhiker);
//        session.persist(borrower1);
//        session.persist(borrower2);
//
//        Borrower b = session.get(Borrower.class , 3);
//        System.out.println(b.getName());
//        Book book2Retrieved = session.get(Book.class,5);
//        System.out.println(book2Retrieved.getName());
//        Query q1 = session.createQuery("from Borrower where id = 4");
//        q1.setCacheable(true);
//        Borrower b1 = (Borrower) q1.uniqueResult();
//        System.out.println(b1.getName());
        tx.commit();
        session.close();
//
//        Session session2 = factory.openSession();
//        Transaction tx2 = session2.beginTransaction();
//        Query q2 = session2.createQuery("from Borrower where id = 4");
//        q2.setCacheable(true);
//        Borrower b2 = (Borrower) q2.uniqueResult();
//        System.out.println(b2.getName());
//        Borrower b1 = session2.get(Borrower.class , 3);
//        System.out.println(b1.getName());
//        Book bookRetrieved = session2.get(Book.class,5);
//        System.out.println(bookRetrieved.getName());
//        Library libraryRetrieved = session2.get(Library.class,1001 );
//        System.out.println(libraryRetrieved.getName());
//        List<Book> libraryBooks = libraryRetrieved.getBooks();
//        for(Book b:libraryBooks){
//            System.out.println(b.getName());
//        }

//        Publisher publisherRetrieved = session.get(Publisher.class, 2);
//        List<Book> publisherBooks = publisherRetrieved.getBooks();
//        for(Book b: publisherBooks){

//
//        Transaction tx1 = session.beginTransaction();
//        Book bookRetrieved = session.get(Book.class,5);
//        System.out.println(bookRetrieved.getName());
//        Library libraryRetrieved = session.get(Library.class,1001 );
//        System.out.println(libraryRetrieved.getName());
//        List<Book> libraryBooks = libraryRetrieved.getBooks();
//        for(Book b:libraryBooks){
//            System.out.println(b.getName());
//        }
////
//        tx2.commit();
//        session2.close();



//

    }
}
