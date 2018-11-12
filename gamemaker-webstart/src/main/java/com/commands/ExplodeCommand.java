package com.commands;

import com.infrastructure.AbstractComponent;

public class ExplodeCommand implements Command{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1805728381304220674L;
	private AbstractComponent component;
	
	public ExplodeCommand(AbstractComponent component) {
		this.component = component;
	}
	
	@Override
	public void execute() {
		component.setVisbility(false);
	}

	@Override
	public void undo() {
		component.setVisbility(true);
	}

}
