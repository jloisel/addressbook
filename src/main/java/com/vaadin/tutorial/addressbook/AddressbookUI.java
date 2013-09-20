package com.vaadin.tutorial.addressbook;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.tutorial.addressbook.model.ContactModel;
import com.vaadin.tutorial.addressbook.model.Models;
import com.vaadin.tutorial.addressbook.presenter.Presenter;
import com.vaadin.tutorial.addressbook.presenter.Presenters;
import com.vaadin.tutorial.addressbook.view.ContactView;
import com.vaadin.tutorial.addressbook.view.Views;
import com.vaadin.ui.UI;

/**
 * Sample entry point class. See web.xml for declaration details.
 * 
 * @author jloisel
 *
 */
@Title("Addressbook")
public final class AddressbookUI extends UI {

	/*
	 * After UI class is created, init() is executed. You should build and wire
	 * up your user interface here.
	 */
	@Override
	protected void init(final VaadinRequest request) {
		final ContactView view = Views.newView();
		final ContactModel model = Models.newModel();
		final Presenter presenter = Presenters.newPresenter(view, model);
		setContent(view);
		
		presenter.onViewOpen();
	}
}
