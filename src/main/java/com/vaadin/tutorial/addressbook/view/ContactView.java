package com.vaadin.tutorial.addressbook.view;

import java.util.List;

import com.vaadin.tutorial.addressbook.model.Contact;
import com.vaadin.ui.Component;

public interface ContactView extends Component {
	void setContacts(final List<Contact> contacts);
	void select(final Contact contact);
	void filter(final String needle);
	void addContact(final Contact contact);
	void removeContact(final Contact contact);
	
	void addListener(final ViewListener listener);
}
