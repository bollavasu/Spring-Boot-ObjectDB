package com.journaldev.spring;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.journaldev.spring.AddressBook;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String welcome() {
		insertAddressBook();
		return "Welcome to Spring Boot REST...";
	}
	
	public void insertAddressBook() {
      EntityManagerFactory emf
      = Persistence.createEntityManagerFactory(
         "$objectdb/db/address-book.odb");
      EntityManager em = emf.createEntityManager();
      em.getTransaction().begin();
      AddressBook b1=new AddressBook("Alex",
         "Australia");
      AddressBook b2=new AddressBook("Chris",
         "Canada");
      AddressBook b3=new AddressBook("Patrick",
         "USA");
      AddressBook b4=new AddressBook("Peter",
         "UK");
      AddressBook b5=new AddressBook("Bob",
         "Germany");
      em.persist(b1);
      em.persist(b2);
      em.persist(b3);
      em.persist(b4);
      em.persist(b5);
      em.getTransaction().commit();
      TypedQuery<AddressBook> query
      = em.createQuery("SELECT b FROM AddressBook b",
         AddressBook.class);
      List<AddressBook> results = query.getResultList();
      for (AddressBook bb : results) {
         System.out.println(bb);
      }
      em.close();
      emf.close();
   }
   
}
