package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.controller.GameMakerController;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Constants;
import com.infrastructure.IComposite;

@SuppressWarnings("serial")
public class GamePanel extends JPanel implements IComposite {

	protected static Logger logger = LogManager.getLogger(GamePanel.class);

	private ArrayList<AbstractComponent> compositeList;
	private String imgPath;

	public GamePanel() {
		compositeList = new ArrayList<>();
		setBorder(BorderFactory.createLineBorder(Color.blue));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT));

		logger.debug("GamePanel constructed");
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (imgPath != null && !"".equalsIgnoreCase(imgPath)) {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File(imgPath));
				img = resize(img, Constants.GAME_PANEL_WIDTH, Constants.GAME_PANEL_HEIGHT);
			} catch (IOException e) {
				logger.error("Image not found/readable");
			}
			g.drawImage(img, 0, 0, this);
		}
		for (AbstractComponent composite : compositeList) {
			if (composite.getVisibility())
				composite.draw(g);
		}
	}

	public void draw(Graphics g) {
		repaint();
	}

	public void addComponent(AbstractComponent abstractComponent) {
		compositeList.add(abstractComponent);
	}

	public void removeComponent(AbstractComponent abstractComponent) {
		compositeList.remove(abstractComponent);
	}

	public BufferedImage resize(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return resized;
	}

	@Override
	public void save(ObjectOutputStream op) {

		try {
			op.writeObject(compositeList);
			op.writeObject(imgPath);
		} catch (IOException e) {
			logger.error("Error in Save");
		}
	}

	@Override
	public void load(ObjectInputStream ip) {
		try {
			compositeList = (ArrayList<AbstractComponent>) ip.readObject();
			imgPath = (String) ip.readObject();
		} catch (java.lang.ClassNotFoundException | IOException e) {
			logger.error("Error in Load");
		}
	}

	public ArrayList<AbstractComponent> getComponentList() {
		return (this.compositeList);
	}

	public void addControllerListener(GameMakerController controller) {
		addMouseListener(controller);
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
