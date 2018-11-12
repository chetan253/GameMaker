package com.behavior;

//import com.infrastructure.ActionBehavior;
import com.infrastructure.Constants;
import com.infrastructure.ObjectProperties;

public class Move{
	
	private ObjectProperties objectProperties;
	
	public Move(ObjectProperties objectProperties)
	{
		this.objectProperties = objectProperties;
	}

	/*@Override
	public void performAction() {

//		System.out.println(objectProperties.getVelX() + " " + objectProperties.getVelY());

		int newX = objectProperties.getX() + objectProperties.getVelX();
		int newY = objectProperties.getY() + objectProperties.getVelY();
		
		objectProperties.setX(objectProperties.getVelX() + objectProperties.getX());
		objectProperties.setY(objectProperties.getVelY() + objectProperties.getY());
						
//		System.out.println(objectProperties.getVelX() + " " + objectProperties.getVelY());
	}*/
	
}
