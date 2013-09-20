package com.vaadin.tutorial.addressbook;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Contact bean.
 * 
 * @author jloisel
 *
 */
public final class Contact implements Serializable {
	@NotNull
	@Size(min = 1)
	private String firstname;
	
	@NotNull
	@Size(min = 1)
	private String lastname;
	
	public Contact() {
		super();
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(final String firstname) {
		this.firstname = checkNotNull(firstname);
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(final String lastname) {
		this.lastname = checkNotNull(lastname);
	}
}
