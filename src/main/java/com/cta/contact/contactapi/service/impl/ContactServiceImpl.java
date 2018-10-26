package com.cta.contact.contactapi.service.impl;

import java.util.List;
import java.util.Optional;

import javax.xml.bind.ValidationException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cta.contact.contactapi.DTO.MessageDTO;
import com.cta.contact.contactapi.DTO.Zipcodes;
import com.cta.contact.contactapi.entity.Contact;
import com.cta.contact.contactapi.repository.ContactRepository;
import com.cta.contact.contactapi.service.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public void setContactRepository(ContactRepository contactRepository) {
		this.contactRepository = contactRepository;
	}

	@Value("${resource.contacts}")
	private String zipcodeapiurl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Contact> retrieveContacts() {
		List<Contact> contacts = contactRepository.findAll();
		return contacts;
	}

	@Override
	public Contact getContact(Long contactId) {
		Optional<Contact> optCont = contactRepository.findById(contactId);
		return optCont.get();
	}

	@Override
	public MessageDTO saveContact(Contact contact) throws ValidationException {
		// check zip code with api before save.
			boolean isValidZipcode=validateZipAPI(contact.getCity(),contact.getState(),contact.getZipCode());
			MessageDTO msgDto = isValidZipcode ? new MessageDTO("Contact Saved Successfully",true) : new MessageDTO("Bad Request",false); 
		contactRepository.save(contact);
		System.out.println("Contact Saved Successfully");
      return msgDto;
	}

	@Override
	public void deleteContact(Long contactId) {
		contactRepository.deleteById(contactId);

	}

	@Override
	public void updateContact(Contact contact) {
		contactRepository.save(contact);

	}

	@Override
	public Contact findFirstName(String firstname) {
		return contactRepository.findByFirstName(firstname);
	}

	@Override
	public Contact findLastName(String lastname) {
		return contactRepository.findByLastName(lastname);

	}

	@Override
	public Contact findZipCode(String zipcode) {
		return contactRepository.findByZipCode(zipcode);

	}
	
	/**
	 * @param city
	 * @param State
	 * @param Zipcode
	 * @return
	 * @throws ValidationException
	 */
	public Boolean validateZipAPI(String city, String state, String zipCode) throws ValidationException {
		boolean validZipcode = false;
		if (StringUtils.isNotBlank(city) && StringUtils.isNotBlank(state) && StringUtils.isNotBlank(zipCode)) {
			StringBuilder apiurl = new StringBuilder();
			apiurl.append(zipcodeapiurl).append(city).append("/").append(state);
			System.out.println("URL for Zipcode API: " + apiurl.toString());

			Zipcodes zipcodeslist = null;
			try {
				ResponseEntity<Zipcodes> response = restTemplate.exchange(apiurl.toString(), HttpMethod.GET, null,
						Zipcodes.class);
				zipcodeslist = response.getBody();
				System.out.println("body" + zipcodeslist.toString());
			} catch (RestClientException restException) {
				restException.getStackTrace();
			}

			System.out.println("Zipcodes from api" + zipcodeslist.toString());
			if (zipcodeslist != null) {
				if (zipcodeslist.getZipcode() != null && !zipcodeslist.getZipcode().isEmpty()) {

					for (String zip : zipcodeslist.getZipcode()) {
						if (zip.equalsIgnoreCase(zipCode)) {
							validZipcode = true;
							System.out.printf("Zipcodes from api with zipcode=%s validzipcode=%b", zipCode,
									validZipcode);
							break;
						}
					}

				}
			}
		}
		return validZipcode;
	}

}
