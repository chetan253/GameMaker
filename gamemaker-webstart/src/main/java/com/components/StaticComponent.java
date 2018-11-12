package com.components;

import java.awt.Color;
import java.awt.Graphics;

import com.infrastructure.AbstractComponent;
import com.infrastructure.ObjectProperties;

public class StaticComponent extends AbstractComponent{
	
	public StaticComponent(ObjectProperties objectProperties) {
		super(objectProperties);
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
	}

}
