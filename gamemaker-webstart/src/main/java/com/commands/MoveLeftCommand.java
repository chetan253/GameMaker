package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveLeftCommand implements Command{
	
	private static final long serialVersionUID = 7442203211517097152L;
	private AbstractComponent abstractComponent;
	
	public MoveLeftCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setX(abstractComponent.getX() - abstractComponent.getVelX());
	}

	@Override
	public void undo() {
		abstractComponent.setX(abstractComponent.getX() + abstractComponent.getVelX());		
	}

}
