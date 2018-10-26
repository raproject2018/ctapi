package com.cta.contact.contactapi.IT;


import static org.junit.Assert.assertTrue;

import javax.xml.bind.ValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.cta.contact.contactapi.ContactApiApplication;
import com.cta.contact.contactapi.entity.Contact;
import com.cta.contact.contactapi.service.impl.ContactServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ContactApiApplication.class)
public class ContactAPI_IT {

	
	@Autowired
	private ContactServiceImpl contactServiceImpl;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	
	@Test
	public void addContact() throws ValidationException {

		Contact ct = new Contact();
		ct.setId(4L);
		ct.setFirstName("Steve");
		ct.setLastName("Jacob");
		ct.setCity("Chicago");
		ct.setState("IL");
		ct.setZipCode("60605");

		boolean validate = contactServiceImpl.validateZipAPI(ct.getCity(), ct.getState(), ct.getZipCode());

		assertTrue(validate);

	}
	

}

