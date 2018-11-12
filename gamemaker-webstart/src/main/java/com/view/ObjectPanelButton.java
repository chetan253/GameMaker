package com.view;

import java.awt.Color;

import javax.swing.JButton;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.ComponentType;
import com.infrastructure.Constants;

@SuppressWarnings("serial")
public class ObjectPanelButton extends JButton {
	protected static Logger logger = LogManager.getLogger(ObjectPanelButton.class);

	public ObjectPanelButton(ComponentType componentType, Color yellow) {

		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);

		if (componentType == ComponentType.ELEMENT)
			setText(Constants.ELEMENT);

		if (componentType == ComponentType.COLLISION)
			setText(Constants.COLLISION);

		if (componentType == ComponentType.BACKGROUND)
			setText(Constants.BACKGROUND);

		if (componentType == ComponentType.SAVE)
			setText(Constants.SAVE);

		if (componentType == ComponentType.LOAD)
			setText(Constants.LOAD);

		if (componentType == ComponentType.PLAY)
			setText(Constants.PLAY);

		if (componentType == ComponentType.PAUSE)
			setText(Constants.PAUSE);

	}
}
