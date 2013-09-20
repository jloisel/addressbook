package com.vaadin.tutorial.addressbook.view;

import static com.google.common.base.Preconditions.checkNotNull;

import com.vaadin.data.Container.Filter;
import com.vaadin.data.Item;

/*
 * A custom filter for searching names and companies in the
 * contactContainer.
 */
final class PropertyIdConcatFilter implements Filter {
	private String needle;

	PropertyIdConcatFilter(final String needle) {
		this.needle = checkNotNull(needle).toLowerCase();
	}

	@Override
	public boolean passesFilter(final Object itemId, final Item item) {
		final StringBuilder haystack = new StringBuilder();
		
		for(final Object id : item.getItemPropertyIds()) {
			haystack.append(item.getItemProperty(id));
		}
		return haystack.toString().contains(needle);
	}

	@Override
	public boolean appliesToProperty(final Object id) {
		return true;
	}
}