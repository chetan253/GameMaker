package com.infrastructure;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.controller.GamePlayController;
import com.view.WindowFrame;

public class StaticPanelButton extends JButton {
	
	String name;
	private WindowFrame windowFrame;
//	private ObjectProperties selected = new ObjectProperties();
//	public HashMap<String, Object> selected = new HashMap<String, Object>();
//	public ObjectProperties selected = null;
	
	JTextField vXField;
	JTextField vYField;
	JTextField widthField;
	JTextField heightField;
	JCheckBox canCollectField;

	
	JCheckBox collectible;
	JRadioButton event;
	ButtonGroup group;
	
	public StaticPanelButton(String name, Color yellow, WindowFrame windowFrame, GamePlayController gamePlayController) {
		this.name = name;
		setText(name);
//		selected.setType(name);
		setActionCommand(name);
		addActionListener(gamePlayController);
		setVisible(true);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.windowFrame = windowFrame;
		
//		this.windowFrame.getStaticPanel().selected=selected;
		
	}
	
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		if (name == "Save") {
//			
////			windowFrame.save();
//		}
//	}
}
