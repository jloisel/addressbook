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

/* 
 * UI class is the starting point for your app. You may deploy it with VaadinServlet
 * or VaadinPortlet by giving your UI class name a parameter. When you browse to your
 * app a web page showing your UI is automatically generated. Or you may choose to 
 * embed your UI to an existing web page. 
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
