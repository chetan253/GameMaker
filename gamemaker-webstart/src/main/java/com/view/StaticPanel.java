package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.components.Clock;
import com.controller.GamePlayController;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;
import com.infrastructure.IPanel;
import com.infrastructure.ObjectProperties;
import com.infrastructure.StaticPanelButton; 

public class StaticPanel extends JPanel implements IComposite{
	protected static Logger logger = LogManager.getLogger(StaticPanel.class);
	private WindowFrame windowFrame;
	private ObjectProperties selected = new ObjectProperties();
	private ArrayList<AbstractComponent> compositeList;
	private GamePlayController gamePlayController;
	
	public StaticPanel(WindowFrame window)
	{ 
		super();
		compositeList=new ArrayList<>();
		this.windowFrame=window;
		setBorder( BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		logger.debug("StaticPanel constructed");
	}
	
	public void createButtons(GamePlayController gamePlayController) {
		this.gamePlayController = gamePlayController;
		createPlayButton();
		createPauseButton();
		createRestartButton();
		createSaveButton();
		createLoadButton();
		
	}
	private void createPlayButton() {
		StaticPanelButton playButton = new StaticPanelButton("Play", Color.green, windowFrame, gamePlayController);
		playButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(playButton);
	}

	private void createPauseButton() {
		StaticPanelButton pauseButton = new StaticPanelButton("Pause", Color.red, windowFrame, gamePlayController );
		pauseButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(pauseButton);
	}

	private void createRestartButton() {
		StaticPanelButton restartButton = new StaticPanelButton("Restart", Color.blue, windowFrame, gamePlayController);
		restartButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(restartButton);
	
	}
	
	private void createSaveButton() {
		StaticPanelButton saveButton = new StaticPanelButton("Save", Color.blue, windowFrame, gamePlayController);
		saveButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(saveButton);
	
	}
	
	private void createLoadButton() {
		StaticPanelButton loadButton = new StaticPanelButton("Load", Color.blue, windowFrame, gamePlayController);
		loadButton.setAlignmentX(0);
		this.add(Box.createRigidArea(new Dimension(10, 100)));
		this.add(loadButton);
	
	}

	public ObjectProperties getSelected() {
		return selected;
	}

	public void setSelected(ObjectProperties selected) {
		this.selected = selected;
	}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		repaint();
	}

	
	@Override
	public void paintComponent(Graphics g)
	{
		
		for(IComposite composite : compositeList) {
//			System.out.println(composite);
			composite.draw(g);
		}	
	}

	@Override
	public void save(ObjectOutputStream op) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(ObjectInputStream ip) {
		// TODO Auto-generated method stub
		
	}

	public void addComponent(AbstractComponent component) {
		// TODO Auto-generated method stub
		compositeList.add(component);
		
	}
}
