package com.vaadin.tutorial.addressbook.view;

import java.util.List;

import com.vaadin.tutorial.addressbook.model.Contact;
import com.vaadin.ui.Component;

/**
 * CRUD (Create, Read, Update and Delete) view on {@link Contact}.
 * 
 * @author jloisel
 *
 */
public interface ContactView extends Component {
	/**
	 * Sets a new list of contacts to display.
	 * 
	 * @param contacts list of {@link Contact}
	 */
	void setContacts(final List<Contact> contacts);
	
	/**
	 * Selects the given {@link Contact} for edition.
	 * 
	 * @param contact contact to select
	 */
	void select(final Contact contact);
	
	/**
	 * Filters contact list with the given {@code needle}.
	 * 
	 * @param needle string to filter on
	 */
	void filter(final String needle);
	
	/**
	 * Adds the given contact to the contact list.
	 * 
	 * @param contact
	 */
	void addContact(final Contact contact);
	
	/**
	 * Removes the given contact from the contact list.
	 * 
	 * @param contact
	 */
	void removeContact(final Contact contact);
	
	/**
	 * Adds a listener to receive view events.
	 * 
	 * @param listener
	 */
	void addListener(final ViewListener listener);
}
