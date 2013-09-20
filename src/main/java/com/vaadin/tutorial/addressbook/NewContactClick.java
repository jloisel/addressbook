package com.vaadin.tutorial.addressbook;

import static com.google.common.base.Preconditions.checkNotNull;

import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Table;

/**
 * Action performed when NewContact button is clicked.
 * 
 * @author jloisel
 *
 */
final class NewContactClick implements ClickListener {
	private final BeanItemContainer<Contact> container;
	private final Table table;
	
	NewContactClick(final BeanItemContainer<Contact> datas, final Table table) {
		super();
		this.container = checkNotNull(datas);
		this.table = checkNotNull(table);
	}

	@Override
	public void buttonClick(final ClickEvent event) {
		container.removeAllContainerFilters();
		
		Contact contact = new Contact();
		contact.setFirstname("New");
		contact.setLastname("Contact");
		final BeanItem<Contact> item = container.addBean(contact);
		
		table.select(item);
	}

}
