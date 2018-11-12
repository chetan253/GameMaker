package com.commands;

import com.infrastructure.AbstractComponent;

public class ChangeDirectionToLeft implements Command {

	private AbstractComponent abstractComponent;

	public ChangeDirectionToLeft(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
	}

	@Override
	public void execute() {
		int velX = abstractComponent.getVelX();
		int velY = abstractComponent.getVelY();

		if (velX != 0) {
			abstractComponent.setVelX(-1 * Math.abs(velX));
		} else {
			abstractComponent.setVelX(-1 * Math.abs(velY));
		}
		abstractComponent.setVelY(0);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
