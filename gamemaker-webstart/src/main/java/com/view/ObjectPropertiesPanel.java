package com.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.Constants;
import com.infrastructure.ElementType;

public class ObjectPropertiesPanel extends JPanel {
	protected static Logger logger = LogManager.getLogger(ObjectPropertiesPanel.class);
	
	//Basic Properties
	private FormView formData;
	private JTextField elementName;
	private JTextField vXField;
	private JTextField vYField;
	private JTextField widthField;
	private JTextField heightField;
	private JCheckBox collectible;
	private JCheckBox rotateable;
	private JCheckBox fire;
	private JRadioButton keyDependent;
	private JRadioButton timeDependent;
	private JComboBox elementTypes;
	private JLabel movement;
	
	private int result;

	//Key Panel
	private JCheckBox left;
	private JCheckBox right;
	private JCheckBox up;
	private JCheckBox down;
	
	private JComboBox leftCombo;
	private JComboBox rightCombo;
	private JComboBox upCombo;
	private JComboBox downCombo;
	
	Object[] keyActions = { Constants.SELECT_KEY, Constants.MOVE_LEFT, Constants.MOVE_RIGHT, Constants.MOVE_UP,
			Constants.MOVE_DOWN, Constants.EXPLODE, Constants.FIRE };

	//Time Panel
	private JCheckBox leftMove;
	private JCheckBox rightMove;
	private JCheckBox upMove;
	private JCheckBox downMove;
	private JCheckBox freeMove;

	private ButtonGroup radioGroup;
	private ButtonGroup radioGroup2;

	//Background properties

	private JPanel colorPanel;
	private JButton color;
	private JButton background;
	private JLabel backgroundLocation;
	private JLabel bg;
	private JRadioButton addColor;
	private JRadioButton addImage;

	File selectedFile = null;

	private void createFormElements() {

		elementName = new JTextField(formData.getElementName(), 7);
		vXField = new JTextField(Integer.toString(formData.getVelX()), 7);
		vYField = new JTextField(Integer.toString(formData.getVelY()), 7);
		widthField = new JTextField(Integer.toString(formData.getWidth()), 7);
		heightField = new JTextField(Integer.toString(formData.getHeight()), 7);
		collectible = new JCheckBox(Constants.COLLECTIBLE);
		rotateable = new JCheckBox(Constants.ROTATEABLE);
		fire = new JCheckBox(Constants.FIRE);
		keyDependent = new JRadioButton(Constants.KEY_DEPENDENT);
		timeDependent = new JRadioButton(Constants.TIME_DEPENDENT);
		Object[] elements = ElementType.values();
		elementTypes = new JComboBox(elements);
		colorPanel = new JPanel();
		colorPanel.setPreferredSize(new Dimension(20, 20));
		color = new JButton(Constants.COLOR_SEL);
		background = new JButton(Constants.IMAGE_SEL);
		backgroundLocation = new JLabel(formData.getBackgroundLocation());
		bg = new JLabel(Constants.BACKGOUND);
		movement = new JLabel(Constants.MOVEMENT);

		Border blackline = BorderFactory.createLineBorder(Color.black);
		colorPanel.setBorder(blackline);
		colorPanel.setBackground(Color.BLACK);

		left = new JCheckBox(Constants.LEFT_KEY);
		right = new JCheckBox(Constants.RIGHT_KEY);
		up = new JCheckBox(Constants.UP_KEY);
		down = new JCheckBox(Constants.DOWN_KEY);

		leftMove = new JCheckBox(Constants.LEFT_KEY);
		rightMove = new JCheckBox(Constants.RIGHT_KEY);
		upMove = new JCheckBox(Constants.UP_KEY);
		downMove = new JCheckBox(Constants.DOWN_KEY);
		freeMove = new JCheckBox(Constants.FREE);

		leftCombo = new JComboBox(keyActions);
		rightCombo = new JComboBox(keyActions);
		upCombo = new JComboBox(keyActions);
		downCombo = new JComboBox(keyActions);

		radioGroup = new ButtonGroup();
		radioGroup2 = new ButtonGroup();

		addColor = new JRadioButton(Constants.COLOR);
		addImage = new JRadioButton(Constants.IMAGE);
	}

	public ObjectPropertiesPanel() {

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		formData = new FormView();
		createFormElements();
		addElements();
		Object[] options = { "OK" };
		result = JOptionPane.showOptionDialog(null, this, Constants.ADD_ELEMENT, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);
	}

	private JPanel addPropertiesPanel() {
		JPanel properties = new JPanel();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		properties.setLayout(gridbag);
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.HORIZONTAL;

		c.gridx = 0;
		c.gridy = 0;
		properties.add(new JLabel(Constants.ELEMENT_TYPE), c);

		c.gridx = 1;
		c.gridy = 0;
		properties.add(elementTypes, c);

		c.gridx = 2;
		c.gridy = 0;
		properties.add(new JLabel(Constants.ELEMENT_NAME), c);

		c.gridx = 3;
		c.gridy = 0;
		properties.add(elementName, c);

		c.gridx = 0;
		c.gridy = 1;
		properties.add(new JLabel(Constants.VEL_X), c);

		c.gridx = 1;
		c.gridy = 1;
		properties.add(vXField, c);

		c.gridx = 2;
		c.gridy = 1;
		properties.add(new JLabel(Constants.VEL_Y), c);

		c.gridx = 3;
		c.gridy = 1;
		properties.add(vYField, c);

		c.gridx = 0;
		c.gridy = 2;
		properties.add(new JLabel(Constants.WIDTH), c);

		c.gridx = 1;
		c.gridy = 2;
		properties.add(widthField, c);

		c.gridx = 2;
		c.gridy = 2;
		properties.add(new JLabel(Constants.HEIGHT), c);

		c.gridx = 3;
		c.gridy = 2;
		properties.add(heightField, c);

		c.gridx = 0;
		c.gridy = 3;
		properties.add(collectible, c);

		c.gridx = 1;
		c.gridy = 3;

		properties.add(rotateable, c);

		
		c.gridx = 2;
		c.gridy = 3;
		properties.add(fire,c);

		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 4;
		Border blackline = BorderFactory.createLineBorder(Color.black);
		movement.setHorizontalAlignment(SwingConstants.CENTER);
		movement.setBorder(blackline);
		properties.add(movement, c);

		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		properties.add(keyDependent, c);

		c.gridx = 3;
		c.gridy = 5;
		properties.add(timeDependent, c);

		radioGroup.add(keyDependent);
		radioGroup.add(timeDependent);

		c.gridx = 0;
		c.gridy = 6;
		properties.add(left, c);

		c.gridx = 1;
		c.gridy = 6;
		properties.add(leftCombo, c);

		c.gridx = 0;
		c.gridy = 7;
		properties.add(right, c);

		c.gridx = 1;
		c.gridy = 7;
		properties.add(rightCombo, c);

		c.gridx = 0;
		c.gridy = 8;
		properties.add(up, c);

		c.gridx = 1;
		c.gridy = 8;
		properties.add(upCombo, c);

		c.gridx = 0;
		c.gridy = 9;
		properties.add(down, c);

		c.gridx = 1;
		c.gridy = 9;
		properties.add(downCombo, c);

		c.gridx = 3;
		c.gridy = 6;
		properties.add(leftMove, c);

		c.gridx = 3;
		c.gridy = 7;
		properties.add(rightMove, c);

		c.gridx = 3;
		c.gridy = 8;
		properties.add(upMove, c);

		c.gridx = 3;
		c.gridy = 9;
		properties.add(downMove, c);

		c.gridx = 3;
		c.gridy = 10;
		properties.add(freeMove, c);

		disableKeyElements();
		keyDependent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (keyDependent.isSelected()) {
					enableKeyElements();
					disableTimeElements();
				} else {
					disableKeyElements();
					enableTimeElements();
				}
			}
		});

		disableTimeElements();
		timeDependent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (timeDependent.isSelected()) {
					enableTimeElements();
					disableKeyElements();
				} else {
					disableTimeElements();
					enableKeyElements();
				}
			}
		});

		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 4;
		bg.setHorizontalAlignment(SwingConstants.CENTER);
		bg.setBorder(blackline);
		properties.add(bg, c);

		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		disableColor();
		properties.add(addColor, c);

		c.gridx = 1;
		c.gridy = 12;
		properties.add(color, c);

		color.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Color newColor = JColorChooser.showDialog(colorPanel, Constants.CHOOSE_COLOR, colorPanel.getBackground());

				colorPanel.setBackground(newColor);
			}
		});

		c.gridx = 2;
		c.gridy = 12;
		properties.add(colorPanel, c);

		c.gridx = 0;
		c.gridy = 13;
		disableImage();
		properties.add(addImage, c);

		radioGroup2.add(addColor);
		radioGroup2.add(addImage);

		addColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (addColor.isSelected()) {
					enableColor();
					disableImage();
				}

			}
		});

		addImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				disableColor();
				enableImage();

			}
		});

		c.gridx = 1;
		c.gridy = 13;
		properties.add(background, c);

		c.gridx = 2;
		c.gridy = 13;
		c.gridwidth = 3;
		properties.add(backgroundLocation, c);

		background.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				int result = fileChooser.showOpenDialog(background);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					backgroundLocation.setText(selectedFile.getName());
				}
			}
		});

		return properties;
	}

	private void addElements() {

		this.add(addPropertiesPanel());

	}

	public void enableColor() {
		color.setEnabled(true);
		colorPanel.setEnabled(true);
	}

	public void disableColor() {
		color.setEnabled(false);
		colorPanel.setEnabled(false);
	}

	public void enableImage() {
		background.setEnabled(true);
		backgroundLocation.setEnabled(true);
	}

	public void disableImage() {
		background.setEnabled(false);
		backgroundLocation.setEnabled(false);
	}

	public void disableKeyElements() {
		left.setEnabled(false);
		right.setEnabled(false);
		up.setEnabled(false);
		down.setEnabled(false);
		leftCombo.setEnabled(false);
		rightCombo.setEnabled(false);
		upCombo.setEnabled(false);
		downCombo.setEnabled(false);
	}

	public void enableKeyElements() {
		left.setEnabled(true);
		right.setEnabled(true);
		up.setEnabled(true);
		down.setEnabled(true);
		leftCombo.setEnabled(true);
		rightCombo.setEnabled(true);
		upCombo.setEnabled(true);
		downCombo.setEnabled(true);
	}

	public void enableTimeElements() {
		leftMove.setEnabled(true);
		rightMove.setEnabled(true);
		upMove.setEnabled(true);
		downMove.setEnabled(true);
		freeMove.setEnabled(true);
	}

	public void disableTimeElements() {
		leftMove.setEnabled(false);
		rightMove.setEnabled(false);
		upMove.setEnabled(false);
		downMove.setEnabled(false);
		freeMove.setEnabled(false);
	}

	public FormView getProperties() {

		if (result == JOptionPane.OK_OPTION) {
			formData.setElementType((ElementType) elementTypes.getSelectedItem());
			formData.setElementName(elementName.getText());
			formData.setVelX(Integer.parseInt(vXField.getText()));
			formData.setVelY(Integer.parseInt(vYField.getText()));
			formData.setWidth(Integer.parseInt(widthField.getText()));
			formData.setHeight(Integer.parseInt(heightField.getText()));
			formData.setCollectible(collectible.isSelected());

			Map<Integer, String> keyActionMap = null;
			ArrayList<String> timeActionArray = null;
			if (keyDependent.isSelected()) {
				keyActionMap = new HashMap<Integer, String>();
				if (left.isSelected())
					keyActionMap.put(KeyEvent.VK_LEFT, keyActions[leftCombo.getSelectedIndex()].toString());
				if (right.isSelected())
					keyActionMap.put(KeyEvent.VK_RIGHT, keyActions[rightCombo.getSelectedIndex()].toString());
				if (up.isSelected())
					keyActionMap.put(KeyEvent.VK_UP, keyActions[upCombo.getSelectedIndex()].toString());
				if (down.isSelected())
					keyActionMap.put(KeyEvent.VK_DOWN, keyActions[downCombo.getSelectedIndex()].toString());
				
			}

			if (timeDependent.isSelected()) {
				timeActionArray = new ArrayList<String>();
				if (leftMove.isSelected())
					timeActionArray.add(Constants.LEFT_KEY);
				if (rightMove.isSelected())
					timeActionArray.add(Constants.RIGHT_KEY);
				if (upMove.isSelected())
					timeActionArray.add(Constants.UP_KEY);
				if (downMove.isSelected())
					timeActionArray.add(Constants.DOWN_KEY);
				if (freeMove.isSelected())
					timeActionArray.add(Constants.FREE);
			}
			formData.setKeyActionMap(keyActionMap);
			formData.setTimeActionArray(timeActionArray);
			
			formData.setColor(colorPanel.getBackground());
			formData.setRotateable(rotateable.isSelected());

			formData.setFire(fire.isSelected());
			if(selectedFile!=null && selectedFile.getPath()!=null && !"".equalsIgnoreCase(selectedFile.getPath()))
				formData.setBackgroundLocation(selectedFile.getPath());
			
			logger.debug("Form data populated");
		}
		
		return formData;
	}

}
