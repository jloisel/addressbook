package com.vaadin.tutorial.addressbook.view;

import com.vaadin.tutorial.addressbook.model.Contact;

public interface ViewListener {
	void onAdd();
	void onRemove(final Contact contact);
	void onSelection(final Contact contact);
	void onFilter(final String needle);
}