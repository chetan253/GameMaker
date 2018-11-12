package com.commands;

import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;

public class ReappearLeftCommand implements Command, Serializable{

	private static final long serialVersionUID = -894891433393652603L;
	private AbstractComponent component;
	
	public ReappearLeftCommand(AbstractComponent component) {
		this.component = component;
	}

	@Override
	public void execute() {
		component.setX(0);
	}

	@Override
	public void undo() {
		
	}

}
