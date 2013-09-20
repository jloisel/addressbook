package com.vaadin.tutorial.addressbook.view;

import com.vaadin.navigator.View;

/**
 * {@link View} factory.
 * 
 * @author jloisel
 *
 */
public final class Views {

	private Views() {
		throw new IllegalAccessError();
	}
	
	/**
	 * @return new contact view
	 */
	public static ContactView newView() {
		return new VaadinContactView();
	}
}
