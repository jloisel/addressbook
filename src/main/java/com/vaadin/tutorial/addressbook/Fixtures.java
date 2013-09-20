package com.vaadin.tutorial.addressbook;

import com.vaadin.data.util.BeanItemContainer;

/**
 * Initial data when starting the application.
 * 
 * @author jloisel
 *
 */
final class Fixtures {
	private static final String[] FIRSTNAMES = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
		"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
		"Lisa", "Marge" };
	
	private static final String[] LASTNAMES = { "Smith", "Gordon", "Simpson", "Brown", "Clavel",
		"Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
		"Barks", "Ross", "Schneider", "Tate" };
	
	private Fixtures() {
		throw new IllegalAccessError();
	}

	/*
	 * Generate some in-memory example data to play with. In a real application
	 * we could be using SQLContainer, JPAContainer or some other to persist the
	 * data.
	 */
	static BeanItemContainer<Contact> createRandomContacts() {
		final BeanItemContainer<Contact> ic = new BeanItemContainer<Contact>(Contact.class);
		
		for (int i = 0; i < 1000; i++) {
			final Contact contact = new Contact();
			contact.setFirstname(FIRSTNAMES[(int) (FIRSTNAMES.length * Math.random())]);
			contact.setLastname(LASTNAMES[(int) (LASTNAMES.length * Math.random())]);
			ic.addBean(contact);
		}
		
		return ic;
	}
}
