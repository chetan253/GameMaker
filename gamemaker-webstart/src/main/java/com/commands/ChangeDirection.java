package com.commands;

import java.util.Arrays;
import java.util.Random;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Direction;

public class ChangeDirection implements Command{
	
	private AbstractComponent component;
	private Random random;
	private Direction[] directions = {Direction.LEFT, Direction.RIGHT, Direction.UP, Direction.DOWN};
	
	public ChangeDirection(AbstractComponent component) {
		this.component = component;
		this.random = new Random();
	}

	@Override
	public void execute() {
		
		component.setX(component.getX() - component.getVelX());
		component.setY(component.getY() - component.getVelY());
		
		Direction oldDirection = component.getDirection();
		int index = Arrays.asList(directions).indexOf(oldDirection);
		while(index == Arrays.asList(directions).indexOf(oldDirection) || index == -1) {
			index = random.nextInt(directions.length);
		}	
		Direction newDirection = directions[index];
		switch(newDirection) {
			case UP:{
				new ChangeDirectionToUp(component).execute(); 
				break;
			}
			case DOWN:{
				new ChangeDirectionToDown(component).execute();
				break;
			}
			case RIGHT:{
				new ChangeDirectionToRight(component).execute();
				break;
			}
			case LEFT:{
				new ChangeDirectionToLeft(component).execute();
				break;
			}
			default: {
				return;
			}

		}
		
		component.setDirection(newDirection);
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	
	

}
