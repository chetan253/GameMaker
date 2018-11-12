package com.infrastructure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AbstractComponent implements IComposite, Serializable {

	private static final long serialVersionUID = 1L;
	private String componentName;
	private String baseName;
	private ObjectProperties objectProperties;
	private Drawable drawable;
	private String image;
	private Color color;
	private ComponentShape shape;
	private Direction direction;
	private boolean isCollectible;
	private boolean canFire;
	private boolean isPlayerObject;

	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public AbstractComponent() {
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ComponentShape getShape() {
		return shape;
	}

	public void setShape(ComponentShape shape) {
		this.shape = shape;
	}

	public Drawable getDrawable() {
		return drawable;
	}

	public void setDrawable(Drawable drawable) {
		this.drawable = drawable;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public ObjectProperties getObjectProperties() {
		return objectProperties;
	}

	public void setObjectProperties(ObjectProperties objectProperties) {
		this.objectProperties = objectProperties;
	}

	public AbstractComponent(ObjectProperties objectProperties) {
		this.objectProperties = new ObjectProperties();
		this.objectProperties.setX(objectProperties.getX());
		this.objectProperties.setY(objectProperties.getY());
		this.objectProperties.setComponentType(objectProperties.getComponentType());
		this.objectProperties.setObjectListType(objectProperties.getObjectListType());
		this.objectProperties.setHeight(objectProperties.getHeight());
		this.objectProperties.setWidth(objectProperties.getWidth());
		this.objectProperties.setVelX(objectProperties.getVelX());
		this.objectProperties.setVelY(objectProperties.getVelY());
		this.objectProperties.setCanCollect(objectProperties.getCanCollect());
	}

	public int getX() {
		return this.objectProperties.getX();
	}

	public void setX(int x) {
		this.objectProperties.setX(x);
	}

	public int getY() {
		return this.objectProperties.getY();
	}

	public void setY(int y) {
		this.objectProperties.setY(y);
	}

	public int getVelX() {
		return this.objectProperties.getVelX();
	}

	public void setVelX(int velX) {
		this.objectProperties.setVelX(velX);
	}

	public int getVelY() {
		return this.objectProperties.getVelY();
	}

	public void setVelY(int velY) {
		this.objectProperties.setVelY(velY);
	}

	public int getWidth() {
		return this.objectProperties.getWidth();
	}

	public void setWidth(int width) {
		this.objectProperties.setWidth(width);
	}

	public int getHeight() {
		return this.objectProperties.getHeight();
	}

	public boolean getCanCollect() {
		return this.objectProperties.getCanCollect();
	}

	public void setcanCollect(boolean canCollect) {
		this.objectProperties.setCanCollect(canCollect);
	}

	public void setHeight(int height) {
		this.objectProperties.setHeight(height);
	}

	public boolean getVisibility() {
		return this.objectProperties.isVisibility();
	}

	public void setVisbility(boolean visibility) {
		this.objectProperties.setVisibility(visibility);
	}

	public Rectangle getBounds() {
		return new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

	public int getBottomCoordinates() {
		return getY() + getHeight();
	}

	public int getRightCoordinates() {
		return getX() + getWidth();
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public boolean isCollectible() {
		return isCollectible;
	}

	public void setCollectible(boolean isCollectible) {
		this.isCollectible = isCollectible;
	}

	public boolean isCanFire() {
		return canFire;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}

	public boolean isPlayerObject() {
		return isPlayerObject;
	}

	public void setPlayerObject(boolean isPlayerObject) {
		this.isPlayerObject = isPlayerObject;
	}

	@Override
	public void draw(Graphics g) {
		if (getVisibility() && drawable != null)
			drawable.draw(this, g);
	}

	@Override
	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub

	}

	@Override
	public void load(ObjectInputStream ip) {
		// TODO Auto-generated method stub

	}

}
