package com.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.io.Serializable;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawRectColor implements Drawable, Serializable{
	
	private static final long serialVersionUID = 4L;

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(component.getColor());
		Rectangle2D shape = new Rectangle2D.Float();
		shape.setFrame(component.getX(), component.getY(), component.getWidth(), component.getHeight());
		g2d.fill(shape);
	}

}
