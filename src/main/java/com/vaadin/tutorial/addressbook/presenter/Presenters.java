package com.vaadin.tutorial.addressbook.presenter;

import com.vaadin.tutorial.addressbook.model.ContactModel;
import com.vaadin.tutorial.addressbook.view.ContactView;

public final class Presenters {

	private Presenters() {
		throw new IllegalAccessError();
	}
	
	public static Presenter newPresenter(final ContactView view, final ContactModel model) {
		return new ContactPresenter(view, model);
	}
}
