package com.vaadin.tutorial.addressbook.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Stores {@link Contact} instances in a {@link LinkedList}.
 * 
 * @author jloisel
 *
 */
final class ListModel implements ContactModel {
	private final LinkedList<Contact> contacts;
	
	ListModel(final LinkedList<Contact> contacts) {
		super();
		this.contacts = checkNotNull(contacts);
	}

	@Override
	public List<Contact> getContacts() {
		return contacts;
	}

	@Override
	public void add(final Contact contact) {
		contacts.add(contact);
	}

	@Override
	public void remove(final Contact contact) {
		contacts.remove(contact);
	}

	@Override
	public Contact getFirst() {
		return contacts.getFirst();
	}
}
