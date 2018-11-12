package com.commands;

import java.util.Random;

import com.infrastructure.AbstractComponent;

public class ChangeDirectionToUp implements Command {

	private AbstractComponent abstractComponent;
	private Random random;

	public ChangeDirectionToUp(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
		this.random = new Random();
	}

	@Override
	public void execute() {
		int velX = abstractComponent.getVelX();
		int velY = abstractComponent.getVelY();

		if (velX != 0) {
			abstractComponent.setVelY(-1 * Math.abs(velX));
		} else {
			abstractComponent.setVelY(-1 * Math.abs(velY));
		}
		abstractComponent.setVelX(0);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

}
