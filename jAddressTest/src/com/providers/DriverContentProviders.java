package com.providers;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class DriverContentProviders implements IStructuredContentProvider{

	
	 /**
	   * Gets the elements
	   * 
	   * @param inputElement
	   *            the List of elements
	   * @return Object[]
	   */
	  public Object[] getElements(Object inputElement) {
	    return ((List) inputElement).toArray();
	  }

	  /**
	   * Disposes any resources
	   */
	  public void dispose() {
	    // Do nothing
	  }

	  /**
	   * Called when the input changes
	   * 
	   * @param viewer
	   *            the viewer
	   * @param oldInput
	   *            the old input
	   * @param newInput
	   *            the new input
	   */
	  public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	    // Do nothing
	  }
	}

	
