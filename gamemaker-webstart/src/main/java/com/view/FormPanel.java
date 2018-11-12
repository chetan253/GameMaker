
package com.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.IAddActionListener;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class FormPanel extends JPanel implements IComposite, IAddActionListener {

	protected static Logger logger = LogManager.getLogger(FormPanel.class);

	private ObjectProperties active;
	private List<ObjectPanelButton> objectButtons; // should we need it lets see?
	private String backgroundPath;
	GridBagConstraints c = new GridBagConstraints();
	private boolean formPanelStatus;
	
	public FormPanel(WindowFrame window) {
		super();
		this.objectButtons = new ArrayList<>();
		this.active = new ObjectProperties();
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setMaximumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setMinimumSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));
		setPreferredSize(new Dimension(Constants.FORM_PANEL_WIDTH, Constants.FORM_PANEL_HEIGHT));

		logger.debug("FormPanel constructed");
	}

	public ObjectProperties getActive() {
		return active;
	}

	public void setSelected(ObjectProperties selected) {
		this.active = selected;
	}

	public BufferedImage resize(BufferedImage img, int width, int height) {
		Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resized.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return resized;
	}

	public void initializeFormPanel() {

		this.setLayout(new BorderLayout());

		JPanel designer = new JPanel();

		GridBagLayout gridbag = new GridBagLayout();
		designer.setLayout(gridbag);
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JLabel designGame = new JLabel(Constants.DESIGNER);
		designGame.setFont(new Font(Constants.HELVETICA, Font.BOLD, 20));
		designGame.setHorizontalAlignment(JLabel.CENTER);
		designer.add(designGame, c);

		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 1;
		designer.add(createSetBackgroundButton(), c);

		c.gridx = 0;
		c.gridy = 2;
		designer.add(createButton(), c);

		c.gridx = 0;
		c.gridy = 3;
		designer.add(createCollisionButton(), c);

		this.add(designer, BorderLayout.NORTH);
		if(!formPanelStatus)
			designer.setVisible(false);
		
		JPanel player = new JPanel();
		player.setLayout(gridbag);
		c.insets = new Insets(10, 10, 10, 10);
		c.anchor = GridBagConstraints.NORTHWEST;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JLabel playGame = new JLabel(Constants.PLAYER);
		playGame.setFont(new Font(Constants.HELVETICA, Font.BOLD, 20));
		playGame.setHorizontalAlignment(JLabel.CENTER);
		player.add(playGame, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		player.add(createLoadButton(), c);

		c.gridx = 0;
		c.gridy = 2;
		player.add(createSaveButton(), c);

		c.gridx = 0;
		c.gridy = 3;
		player.add(createPlayButton(), c);

		c.gridx = 0;
		c.gridy = 4;
		player.add(createPauseButton(), c);

		this.add(player, BorderLayout.CENTER);
	}

	public ObjectProperties getActiveObjectProperties() {
		return active;
	}

	public ObjectPanelButton createSetBackgroundButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.BACKGROUND, null);
		this.objectButtons.add(button);
		button.setPreferredSize(new Dimension(150, 50));
		return button;
	}

	private ObjectPanelButton createButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.ELEMENT, Color.YELLOW);
		this.objectButtons.add(button);
		button.setPreferredSize(new Dimension(150, 50));
		return button;
	}

	private ObjectPanelButton createCollisionButton() {
		ObjectPanelButton collisionButton = new ObjectPanelButton(ComponentType.COLLISION, Color.YELLOW);
		this.objectButtons.add(collisionButton);
		collisionButton.setPreferredSize(new Dimension(150, 50));
		return collisionButton;
	}

	private ObjectPanelButton createLoadButton() {
		ObjectPanelButton loadButton = new ObjectPanelButton(ComponentType.LOAD, Color.CYAN);
		loadButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(loadButton);
		return loadButton;
	}

	private ObjectPanelButton createSaveButton() {
		ObjectPanelButton saveButton = new ObjectPanelButton(ComponentType.SAVE, Color.BLUE);
		saveButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(saveButton);
		return saveButton;
	}

	private ObjectPanelButton createPlayButton() {
		ObjectPanelButton playButton = new ObjectPanelButton(ComponentType.PLAY, Color.GREEN);
		playButton.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(playButton);
		return playButton;
	}

	private ObjectPanelButton createPauseButton() {
		ObjectPanelButton button = new ObjectPanelButton(ComponentType.PAUSE, Color.GREEN);
		button.setPreferredSize(new Dimension(150, 50));
		this.objectButtons.add(button);
		return button;
	}

	public void draw(Graphics g) {
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}
	
	public void setFormPanelStatus(boolean formPanelStatus) {
		this.formPanelStatus = formPanelStatus;
	}

	public void setBackground(String background) {
		this.backgroundPath = background;
	}

	@Override
	public void save(ObjectOutputStream op) {
	}

	@Override
	public void load(ObjectInputStream ip) {
	}

	@Override
	public void addActionListener(ActionListener listener) {
		for (ObjectPanelButton button : objectButtons) {
			button.addActionListener(listener);
		}
	}
}
