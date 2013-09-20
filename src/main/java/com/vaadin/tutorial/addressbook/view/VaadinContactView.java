package com.vaadin.tutorial.addressbook.view;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.tutorial.addressbook.model.Contact;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

final class VaadinContactView extends CustomComponent implements ContactView {

	/* User interface components are stored in session. */
	private final Table table = new Table();
	private final TextField searchField = new TextField();
	private final Button addNewContactButton = new Button("New");
	private final Button removeContactButton = new Button("Remove this contact");
	private final FormLayout editorLayout = new FormLayout();
	private final FieldGroup editorFields = new BeanFieldGroup<Contact>(Contact.class);
	
	private BeanItemContainer<Contact> container;

	private final Collection<ViewListener> listeners;
	
	VaadinContactView() {
		super();
		this.listeners = new HashSet<ViewListener>();
		initLayout();
		initContactList();
		initEditor();
		initSearch();
		initAddRemoveButtons();
	}
	
	@Override
	public void addListener(final ViewListener listener) {
		listeners.add(listener);
	}

	/*
	 * In this example layouts are programmed in Java. You may choose use a
	 * visual editor, CSS or HTML templates for layout instead.
	 */
	private void initLayout() {

		/* Root of the user interface component tree is set */
		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		setCompositionRoot(splitPanel);

		/* Build the component tree */
		VerticalLayout leftLayout = new VerticalLayout();
		splitPanel.addComponent(leftLayout);
		splitPanel.addComponent(editorLayout);
		leftLayout.addComponent(table);
		HorizontalLayout bottomLeftLayout = new HorizontalLayout();
		leftLayout.addComponent(bottomLeftLayout);
		bottomLeftLayout.addComponent(searchField);
		bottomLeftLayout.addComponent(addNewContactButton);

		/* Set the contents in the left of the split panel to use all the space */
		leftLayout.setSizeFull();

		/*
		 * On the left side, expand the size of the contactList so that it uses
		 * all the space left after from bottomLeftLayout
		 */
		leftLayout.setExpandRatio(table, 1);
		table.setSizeFull();

		/*
		 * In the bottomLeftLayout, searchField takes all the width there is
		 * after adding addNewContactButton. The height of the layout is defined
		 * by the tallest component.
		 */
		bottomLeftLayout.setWidth("100%");
		searchField.setWidth("100%");
		bottomLeftLayout.setExpandRatio(searchField, 1);

		/* Put a little margin around the fields in the right side editor */
		editorLayout.setMargin(true);
		editorLayout.setVisible(false);
	}

	private void initEditor() {

		editorLayout.addComponent(removeContactButton);

		/* User interface can be created dynamically to reflect underlying data. */
		for (final String fieldName : ImmutableList.of("firstname","lastname")) {
			final TextField field = new TextField(fieldName);
			field.setValidationVisible(true);
			editorLayout.addComponent(field);
			field.setWidth("100%");

			editorFields.bind(field, fieldName);
		}

		/*
		 * Data can be buffered in the user interface. When doing so, commit()
		 * writes the changes to the data source. Here we choose to write the
		 * changes automatically without calling commit().
		 */
		editorFields.setBuffered(false);
	}

	private void initSearch() {

		/*
		 * We want to show a subtle prompt in the search field. We could also
		 * set a caption that would be shown above the field or description to
		 * be shown in a tooltip.
		 */
		searchField.setInputPrompt("Search contacts");

		/*
		 * Granularity for sending events over the wire can be controlled. By
		 * default simple changes like writing a text in TextField are sent to
		 * server with the next Ajax call. You can set your component to be
		 * immediate to send the changes to server immediately after focus
		 * leaves the field. Here we choose to send the text over the wire as
		 * soon as user stops writing for a moment.
		 */
		searchField.setTextChangeEventMode(TextChangeEventMode.LAZY);

		/*
		 * When the event happens, we handle it in the anonymous inner class.
		 * You may choose to use separate controllers (in MVC) or presenters (in
		 * MVP) instead. In the end, the preferred application architecture is
		 * up to you.
		 */
		searchField.addTextChangeListener(new TextChangeListener() {
			@Override
			public void textChange(final TextChangeEvent event) {
				for(final ViewListener listener : listeners) {
					listener.onFilter(event.getText());
				}
			}
		});
	}

	private void initAddRemoveButtons() {
		addNewContactButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(final ClickEvent event) {
				for(final ViewListener listener : listeners) {
					listener.onAdd();
				}
			}
		});

		removeContactButton.addClickListener(new ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				final Object itemId = table.getValue();
				for(final ViewListener listener : listeners) {
					listener.onRemove(container.getItem(itemId).getBean());
				}
			}
		});
	}

	private void initContactList() {
		table.setSelectable(true);
		table.setImmediate(true);

		table.addValueChangeListener(new Property.ValueChangeListener() {
			@Override
			public void valueChange(ValueChangeEvent event) {
				final Object itemId = table.getValue();

				final BeanItem<Contact> item = container.getItem(itemId);
				final Contact selected = item == null ? null : item.getBean();
				for(final ViewListener listener : listeners) {
					listener.onSelection(selected);
				}
			}
		});
	}

	@Override
	public void setContacts(final List<Contact> contacts) {
		container = new BeanItemContainer<Contact>(Contact.class, contacts);
		table.setContainerDataSource(container);
	}
	
	@Override
	public void select(final Contact contact) {
		final Object itemId = container.getBeanIdResolver().getIdForBean(contact);

		if (itemId != null) {
			editorFields.setItemDataSource(table.getItem(itemId));
		}
		editorLayout.setVisible(itemId != null);
	}
	
	@Override
	public void filter(final String needle) {
		container.removeAllContainerFilters();
		container.addContainerFilter(new PropertyIdConcatFilter(needle));
	}

	@Override
	public void addContact(final Contact contact) {
		container.removeAllContainerFilters();
		container.addItem(contact);
	}
	
	@Override
	public void removeContact(final Contact contact) {
		container.removeAllContainerFilters();
		container.removeItem(contact);
	}
}
