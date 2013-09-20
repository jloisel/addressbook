package com.vaadin.tutorial.addressbook.presenter;

import static com.google.common.base.Preconditions.checkNotNull;

import com.vaadin.tutorial.addressbook.model.Contact;
import com.vaadin.tutorial.addressbook.model.ContactModel;
import com.vaadin.tutorial.addressbook.view.ContactView;
import com.vaadin.tutorial.addressbook.view.ViewListener;

final class PresenterImpl implements ViewListener, Presenter {
	private final ContactView view;
	private final ContactModel model;

	PresenterImpl(final ContactView view, final ContactModel model) {
		super();
		this.view = checkNotNull(view);
		this.model = checkNotNull(model);
		view.addListener(this);
	}
	
	@Override
	public void onViewOpen() {
		view.setContacts(model.getContacts());
	}
	
	@Override
	public void onAdd() {
		final Contact contact = new Contact();
		contact.setFirstname("New");
		contact.setLastname("Contact");
		model.add(contact);
		
		view.addContact(contact);
		view.select(contact);
	}

	@Override
	public void onRemove(final Contact contact) {
		model.remove(contact);
		view.removeContact(contact);
		view.select(model.getLast());
	}

	@Override
	public void onFilter(final String needle) {
		view.filter(needle);
	}

	@Override
	public void onSelection(final Contact contact) {
		view.select(contact);
	}
}
