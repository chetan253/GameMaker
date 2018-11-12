package com.infrastructure;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface IComposite {
	public void draw(Graphics g);
	public void save(ObjectOutputStream op);
	public void load(ObjectInputStream ip);
	
}
