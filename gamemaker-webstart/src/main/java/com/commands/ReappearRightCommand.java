package com.commands;

import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;

public class ReappearRightCommand implements Command, Serializable{

	private static final long serialVersionUID = -894891433393652603L;
	private AbstractComponent component;
	
	public ReappearRightCommand(AbstractComponent component) {
		this.component = component;
	}

	@Override
	public void execute() {
		component.setX(Constants.GAME_PANEL_WIDTH);
	}

	@Override
	public void undo() {
		
	}

}
