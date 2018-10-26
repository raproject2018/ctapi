package com.cta.contact.contactapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cta.contact.contactapi.entity.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long>{
	Contact findByFirstName(String firstname);
	Contact findByLastName(String lastname);
	Contact findByZipCode(String zipcode);
}
