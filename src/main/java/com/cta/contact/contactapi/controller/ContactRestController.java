package com.cta.contact.contactapi.controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cta.contact.contactapi.DTO.MessageDTO;
import com.cta.contact.contactapi.entity.Contact;
import com.cta.contact.contactapi.service.ContactService;

/**
 * @author RA
 *
 */

@RestController
public class ContactRestController {

	@Autowired
	private ContactService contactService;

	public void setContactService(ContactService contactService) {
		this.contactService = contactService;
	}
	


	@GetMapping("/api/contacts")
	public List<Contact> getContacts() {
		List<Contact> contacts = contactService.retrieveContacts();
		return contacts;
	}

	@GetMapping("/api/contacts/{contactId}")
	public Contact getContact(@PathVariable(name = "contactId") Long contactId) {
		return contactService.getContact(contactId);
	}

	@GetMapping("/api/contacts/findfirst")
	public Contact getContactByFirstName(@RequestParam(name = "firstname") String firstname) {
		return contactService.findFirstName(firstname);
	}

	@GetMapping("/api/contacts/findlast")
	public Contact getContactByLastName(@RequestParam(name = "lastname") String lastname) {
		return contactService.findLastName(lastname);
	}

	@GetMapping("/api/contacts/findzip")
	public Contact getContactByZipCode(@RequestParam(name = "zipcode") String zipcode) {
		return contactService.findZipCode(zipcode);
	}

	@PostMapping("/api/contacts")
	public ResponseEntity<MessageDTO> saveContact(Contact contact) {

		try {
			MessageDTO msgDto = contactService.saveContact(contact);
			return msgDto.isSuccessful() ? new ResponseEntity<>(msgDto, HttpStatus.OK)
					: new ResponseEntity<>(msgDto, HttpStatus.BAD_REQUEST);

		} catch (ValidationException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/api/contacts/{contactId}")
	public void deleteContact(@PathVariable(name = "contactId") Long contactId) {
		contactService.deleteContact(contactId);
		System.out.println("Contact Deleted Successfully");
	}

	@PutMapping("/api/employees/{contactId}")
	public void updateContact(@RequestBody Contact contact, @PathVariable(name = "contactId") Long contactId) {
		Contact cont = contactService.getContact(contactId);
		if (cont != null) {
			contactService.updateContact(contact);
		}

	}
	

	

}
