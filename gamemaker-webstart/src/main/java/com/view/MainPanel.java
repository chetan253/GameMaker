package com.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.IAddActionListener;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class MainPanel extends JPanel implements IComposite, IAddActionListener {
	protected static Logger logger = LogManager.getLogger(MainPanel.class);
	private ArrayList<IComposite> compositeList;

	public MainPanel() {
		super();
		setBorder(BorderFactory.createLineBorder(Color.red));
		this.compositeList = new ArrayList<IComposite>();
		setLayout(new FlowLayout());
		setFocusable(true);
		requestFocusInWindow();
		logger.debug("MainPanel constructed");
	}

	public void draw(Graphics g) {
		for (IComposite composite : compositeList) {
			composite.draw(g);
		}
	}

	public Rectangle getBounds() {
		return null;
	}

	public ArrayList<IComposite> getCompositeList() {
		return compositeList;
	}

	public void addComponent(IComposite composite) {
		compositeList.add(composite);
		this.add((JPanel) composite);
	}

	public void removeComponent(IComposite composite) {
		compositeList.remove(composite);
	}

	public ObjectProperties getActiveObjectProperties() {
		for (IComposite composite : compositeList) {
			if (composite instanceof FormPanel)
				return ((FormPanel) composite).getActiveObjectProperties();
		}

		return null;
	}

	public void setFocusForGamePanel() {

		for (IComposite composite : compositeList) {
			if (composite instanceof GamePanel)
				((GamePanel) composite).requestFocus();
			return;
		}

	}

	@Override
	public void save(ObjectOutputStream op) {
		for (IComposite composite : compositeList) {
			composite.save(op);
		}
	}

	@Override
	public void load(ObjectInputStream ip) {
		for (IComposite composite : compositeList) {
			composite.load(ip);
		}
	}

	@Override
	public void addActionListener(ActionListener listener) {
		for (IComposite composite : compositeList) {
			if (composite instanceof IAddActionListener)
				((IAddActionListener) composite).addActionListener(listener);
		}
	}
}
