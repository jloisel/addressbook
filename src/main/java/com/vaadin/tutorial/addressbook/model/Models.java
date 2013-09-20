package com.vaadin.tutorial.addressbook.model;

import java.util.LinkedList;

public class Models {
	
	private static final String[] FIRSTNAMES = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
		"Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
		"Lisa", "Marge" };
	
	private static final String[] LASTNAMES = { "Smith", "Gordon", "Simpson", "Brown", "Clavel",
		"Simons", "Verne", "Scott", "Allison", "Gates", "Rowling",
		"Barks", "Ross", "Schneider", "Tate" };

	public static ContactModel newModel() {
		final LinkedList<Contact> container = new LinkedList<Contact>();
		
		for (int i = 0; i < 1000; i++) {
			final Contact contact = new Contact();
			contact.setFirstname(FIRSTNAMES[(int) (FIRSTNAMES.length * Math.random())]);
			contact.setLastname(LASTNAMES[(int) (LASTNAMES.length * Math.random())]);
			container.add(contact);
		}
		
		return new Model(container);
	}
}
