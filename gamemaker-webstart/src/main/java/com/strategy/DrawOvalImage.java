package com.strategy;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

import java.io.Serializable;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


import com.infrastructure.AbstractComponent;
import com.infrastructure.Drawable;

public class DrawOvalImage implements Drawable, Serializable{

	private static final long serialVersionUID = 3L;

	@Override
	public void draw(AbstractComponent component, Graphics g) {
		Graphics2D g2d = (Graphics2D) g.create();
		BufferedImage img=null;
		try
		{
			img = ImageIO.read(new File(component.getImage()));
			img=resize(img,component.getWidth(),component.getHeight());
			g2d.drawImage(img,component.getX(), component.getY(), null);
		} 
		catch (IOException e) 
		{
			//add logger here
			g2d.fill(new Ellipse2D.Double(component.getX(), component.getY(), component.getWidth(), component.getHeight()));
		}
		g2d.dispose();
	}
	
	private BufferedImage resize(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();
		return resized;
	}
}
