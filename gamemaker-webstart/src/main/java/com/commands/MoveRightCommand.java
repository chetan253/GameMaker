package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveRightCommand implements Command{
	
	private static final long serialVersionUID = 788831467916562104L;
	private AbstractComponent abstractComponent;
	
	public MoveRightCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}
	
	@Override
	public void execute() {
		abstractComponent.setX(abstractComponent.getX() + abstractComponent.getVelX());
	}

	@Override
	public void undo() {
		abstractComponent.setX(abstractComponent.getX() - abstractComponent.getVelX());		
	}

}
