package com.vaadin.tutorial.addressbook.model;

import java.util.List;

public interface ContactModel {

	/**
	 * @return all contacts
	 */
	List<Contact> getContacts();

	/**
	 * Adds the given contact to contact list.
	 * 
	 * @param contact to add
	 */
	void add(Contact contact);

	void remove(Contact contact);

	Contact getFirst();
}