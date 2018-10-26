package com.cta.contact.contactapi.service;

import java.util.List;

import javax.xml.bind.ValidationException;

import com.cta.contact.contactapi.DTO.MessageDTO;
import com.cta.contact.contactapi.entity.Contact;

/**
 * @author RA
 *
 */

public interface ContactService {

	public List<Contact> retrieveContacts();

	public Contact getContact(Long contactId);

	public MessageDTO saveContact(Contact contact) throws ValidationException ;

	public void deleteContact(Long contactId);

	public void updateContact(Contact contact);
	
	public Contact findFirstName(String firstname);
	public Contact findLastName(String lastname);
	public Contact findZipCode(String zipcode);
	

}
