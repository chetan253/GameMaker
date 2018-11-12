package com.infrastructure;

import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RectangularShape;
import java.io.Serializable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Collision implements Serializable {
	
	protected static Logger logger = LogManager.getLogger(Collision.class);
	private static final long serialVersionUID = 6L;
	private AbstractComponent primaryComponent;
	private AbstractComponent secondaryComponent;
	private String primaryCompAction;
	private String secondaryCompAction;
	
	public Collision() {
		
	}
	
	public boolean checkIntersectionBetweenElements(AbstractComponent component1, AbstractComponent component2) {
		
		return component1.getBounds().intersects(component2.getBounds());
		
	}
	
	public RectangularShape getDims(AbstractComponent component) {
		if(component.getShape() == ComponentShape.RECTANGLE)
			return new Rectangle(component.getX(), component.getY(), component.getWidth(), component.getHeight());
		else
			return new Ellipse2D.Double(component.getX(), component.getY(), component.getWidth(), component.getHeight());
	}
	
	public Direction checkCollisionBetweenAbstractComponentAndBounds(AbstractComponent component) {
 		//get current position of component
 		int left =  component.getX();
 		int right = component.getX() + component.getWidth();
 		int top = component.getY();
 		int bottom = component.getY() + component.getHeight();
 		
 		
 		if(left + component.getVelX() <=0){
 		    return Direction.X;
 		}
 		if(right + component.getVelX() >= Constants.GAME_PANEL_WIDTH){
 			return Direction.X;
 		}
 		if(top + component.getVelY() <=0){
 			return Direction.Y;
 		}
 		if(bottom + component.getVelY() >= Constants.GAME_PANEL_HEIGHT){
 			return Direction.Y;
 		}
 	
		return Direction.NONE;
 	
	}
	
	public Direction checkCollisionBetweenAbstractComponents(AbstractComponent component1, AbstractComponent component2) {

		ElementCoordinates e1 = getElementCoordinates(component1);
		ElementCoordinates e2 = getElementCoordinates(component2);
		
		if(component1.getShape() == ComponentShape.RECTANGLE) {
			if((component1.getX() <= e2.getTopRightX()) || (e1.getTopRightX() >= component2.getX()))
				return Direction.X;
			if((component1.getY() <= e2.getBottomLeftY()) || (e1.getBottomLeftY() >= component2.getY()))
				return Direction.Y;
		}
		
		//Approaching from top right and going towards 
		if((e1.getBottomRightX() >= component2.getX() && e1.getBottomRightY() <= component2.getY()) ||
				(e1.getBottomLeftX() <= e2.getTopRightX() && e1.getBottomLeftY() <= e2.getTopRightY())) {
			return Direction.Y;
		}
		else if((e1.getBottomRightX() <= component2.getX() && e1.getBottomRightY() <= component2.getY()) || 
				(e1.getTopRightX() <= component2.getX() && e1.getTopRightY() <= e2.getBottomLeftY())) {
			return Direction.X;
		}
		else if((e1.getTopRightX() >= e2.getBottomLeftX() && e1.getTopRightY() >= e2.getBottomLeftY()) ||
				(component1.getX() <= e2.getBottomRightX() && component1.getY() >= e2.getBottomRightY())) {
			return Direction.Y;
		}
		else if((component1.getX() >= e2.getBottomRightX() && component1.getY() <= e2.getBottomRightY()) || 
				(e1.getBottomLeftX() >= e2.getTopRightX() && e1.getBottomLeftY() >= e2.getTopRightY())) {
			return Direction.X;
		}
		return Direction.BOTH;
	}
	
	public ElementCoordinates getElementCoordinates(AbstractComponent component){
		ElementCoordinates componentCoordinates = new ElementCoordinates(component);
		componentCoordinates.setBottomLeftX(component.getX() + component.getHeight());
		componentCoordinates.setBottomLeftY(component.getY() + component.getHeight());
		componentCoordinates.setBottomRightX(component.getX() + component.getWidth() + component.getHeight());
		componentCoordinates.setBottomRightY(component.getY() + component.getHeight() + component.getWidth());
		componentCoordinates.setTopRightX(component.getX() + component.getWidth());
		componentCoordinates.setTopRightY(component.getY() + component.getX() + component.getWidth());
		return componentCoordinates;
	}
	
	public class ElementCoordinates{
		int bottomLeftY;
		int bottomLeftX;
		int bottomRightY;
		int bottomRightX;
		int topRightX;
		int topRightY;
		private AbstractComponent component;
		public ElementCoordinates(AbstractComponent component) {
			this.component = component;
		}
		public int getBottomLeftY() {
			return bottomLeftY;
		}
		public void setBottomLeftY(int bottomLeftY) {
			this.bottomLeftY = bottomLeftY;
		}
		public int getBottomLeftX() {
			return bottomLeftX;
		}
		public void setBottomLeftX(int bottomLeftX) {
			this.bottomLeftX = bottomLeftX;
		}
		public int getBottomRightY() {
			return bottomRightY;
		}
		public void setBottomRightY(int bottomRightY) {
			this.bottomRightY = bottomRightY;
		}
		public int getBottomRightX() {
			return bottomRightX;
		}
		public void setBottomRightX(int bottomRightX) {
			this.bottomRightX = bottomRightX;
		}
		public int getTopRightX() {
			return topRightX;
		}
		public void setTopRightX(int topRightX) {
			this.topRightX = topRightX;
		}
		public int getTopRightY() {
			return topRightY;
		}
		public void setTopRightY(int topRightY) {
			this.topRightY = topRightY;
		}
		public AbstractComponent getElement() {
			return component;
		}
		public void setElement(AbstractComponent component) {
			this.component = component;
		}
		
	}
	
}
