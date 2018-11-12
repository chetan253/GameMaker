package com.view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.ElementType;

public class FormView {
	protected static Logger logger = LogManager.getLogger(FormView.class);
	private ElementType elementType;
	private String elementName;
	private int x;
	private int y;
	private int velX;
	private int velY;
	private int width;
	private int height;
	private boolean collectible;
	private boolean rotateable;
	private boolean fire;
	private int actionType;
	private Color color;
	private String backgroundLocation;
	private Map<Integer, String> keyActionMap;
	private ArrayList<String> timeActionArray;

	public String getBackgroundLocation() {
		return backgroundLocation;
	}

	public void setBackgroundLocation(String backgroundLocation) {
		this.backgroundLocation = backgroundLocation;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}


	public ElementType getElementType() {
		return elementType;
	}

	public void setElementType(ElementType elementType) {
		this.elementType = elementType;
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

	public boolean isCollectible() {
		return collectible;
	}

	public void setCollectible(boolean collectible) {
		this.collectible = collectible;
	}

	public boolean isRotateable() {
		return rotateable;
	}

	public void setRotateable(boolean rotateable) {
		this.rotateable = rotateable;
	}

	public boolean isFire() {
		return fire;
	}

	public void setFire(boolean fire) {
		this.fire = fire;
	}

	public int getActionType() {
		return actionType;
	}

	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	public Map<Integer, String> getKeyActionMap() {
		return keyActionMap;
	}

	public void setKeyActionMap(Map<Integer, String> keyActionMap) {
		this.keyActionMap = keyActionMap;
	}

	public ArrayList<String> getTimeActionArray() {
		return timeActionArray;
	}

	public void setTimeActionArray(ArrayList<String> timeActionArray) {
		this.timeActionArray = timeActionArray;
	}

}
