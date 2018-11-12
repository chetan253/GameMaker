package com.commands;

import com.infrastructure.AbstractComponent;

public class MoveCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5600203752621923992L;
	private AbstractComponent abstractComponent;
	/**
	 * Store the current state then update
	 */
	public MoveCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}

	/**
	 * This command was created when the timer ticked, so we advanced the ball accordingly
	 */
	public void execute() {
		abstractComponent.setX(abstractComponent.getX() + abstractComponent.getVelX());
		abstractComponent.setY(abstractComponent.getY() + abstractComponent.getVelY());
	}

	/**
	 * This command was created when the timer un-ticked, so we return ball to previous state
	 */
	@Override
	public void undo() {
		abstractComponent.setX(abstractComponent.getX() - abstractComponent.getVelX());
		abstractComponent.setY(abstractComponent.getY() - abstractComponent.getVelY());
	}
	
	
}
