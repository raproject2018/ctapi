package com.cta.contact.contactapi.DTO;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Zipcodes {
	@JsonProperty("zip_codes")
	private List<String> zipcode;

	public List<String> getZipcode() {
		if (this.zipcode==null){
			this.zipcode=new ArrayList<>();
		}
		return zipcode;
	}

	public void setZipcode(List<String> zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		return "Zipcodes [zipcode=" + zipcode + "]";
	}

	
}
