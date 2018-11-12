package com.infrastructure;

import java.io.Serializable;

public class ObjectProperties implements Serializable {

	private ObjectListType objectListType;
	private ComponentType componentType;

	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	private boolean canCollect = false;
	// private boolean event = false;
	// private boolean collectible = false;
	private boolean visibility = true;

	// public String getType() {
	// return type;
	// }
	//
	// public void setType(String type) {
	// this.type = type;
	// }

	public ObjectProperties() {
		this.x = 5;
		this.y = 5;
		this.velX = 1;
		this.velY = 1;
		this.width = 50;
		this.height = 50;
		// this.type = "";
		this.canCollect = false;
		// this.collectible = false;
		// this.event = false;
		this.visibility = true;
		this.objectListType = ObjectListType.ACTION;
	}

	public ObjectProperties(int x, int y, int velX, int velY, int width, int height, String type, boolean canCollect,
			boolean event, boolean collectible, boolean visibility) {
		super();
		this.x = x;
		this.y = y;
		this.velX = velX;
		this.velY = velY;
		this.width = width;
		this.height = height;
		// this.type = type;
		this.canCollect = canCollect;
		// this.collectible = collectible;
		// this.event = event;
		this.objectListType = ObjectListType.ACTION;
		this.componentType = componentType.BALL;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean getCanCollect() {
		return canCollect;
	}

	public void setCanCollect(boolean canCollect) {
		this.canCollect = canCollect;
	}

	public boolean isVisibility() {
		return visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public ObjectListType getObjectListType() {
		return objectListType;
	}

	public void setObjectListType(ObjectListType objectListType) {
		this.objectListType = objectListType;
	}

	public ComponentType getComponentType() {
		return componentType;
	}

	public void setComponentType(ComponentType componentType) {
		this.componentType = componentType;
	}
}
