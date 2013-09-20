package com.vaadin.tutorial.addressbook.view;

public final class Views {

	private Views() {
		throw new IllegalAccessError();
	}
	
	public static ContactView newView() {
		return new VaadinView();
	}
}
