package com.vaadin.tutorial.addressbook.model;

import java.util.List;

public interface ContactModel {

	List<Contact> getContacts();

	void add(Contact contact);

	void remove(Contact contact);

	Contact getLast();

}