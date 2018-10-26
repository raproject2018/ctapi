package com.cta.contact.contactapi;

import static org.junit.Assert.assertTrue;

import java.net.URISyntaxException;

import javax.xml.bind.ValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.cta.contact.contactapi.entity.Contact;
import com.cta.contact.contactapi.service.impl.ContactServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= WebEnvironment.RANDOM_PORT)
	public class ContactAPITest
	{
	    @LocalServerPort
	    int randomServerPort;
	    
		@Value("${resource.contacts}")
		private String zipcodeapiurl;
		
		@Autowired
		private ContactServiceImpl contactServiceImpl;
	     
	    @Test
	    public void testGetEmployeeListSuccess() throws URISyntaxException, ValidationException
	    {
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
