package com.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Iterator;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.Collider;
import com.infrastructure.CollisionType;
import com.infrastructure.Constants;

public class CollisionFormPanel extends JPanel {
	protected static Logger logger = LogManager.getLogger(CollisionFormPanel.class);
	private ColliderData colliderData;
	private Object[] dataList;
	private JComboBox primaryElement;
	private JComboBox secondaryElement;
	private JComboBox primaryAction;
	private JComboBox secondaryAction;

	private Object[] collisionData;

	private Set<Collider> collidersList;

	private int result;

	public CollisionFormPanel(Object[] dataList) {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.dataList = dataList;
		//this.collidersList = colliders;
		colliderData = new ColliderData();
		createFormElements();
		this.add(addCollisionPanel());
		Object[] options = { Constants.OK };
		result = JOptionPane.showOptionDialog(null, this, Constants.ADD_COLLISION, JOptionPane.OK_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, options, null);

		logger.debug("CollisionFormPanel constructed");
	}

	public void createFormElements() {

		primaryElement = new JComboBox(dataList);
		secondaryElement = new JComboBox(dataList);

		collisionData = CollisionType.values();
		primaryAction = new JComboBox(collisionData);
		secondaryAction = new JComboBox(collisionData);
	}

	private JPanel addCollisionPanel() {
		JPanel collision = new JPanel();

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		collision.setLayout(gridbag);
		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.NONE;
		c.gridwidth = 1;

		c.gridx = 0;
		c.gridy = 0;
		collision.add(new JLabel(Constants.PRIMARY_ELE), c);

		c.gridx = 1;
		c.gridy = 0;
		collision.add(new JLabel(Constants.SECONDARY_ELE), c);

		c.gridx = 2;
		c.gridy = 0;
		collision.add(new JLabel(Constants.PRIMARY_ACT), c);

		c.gridx = 3;
		c.gridy = 0;
		collision.add(new JLabel(Constants.SECONDARY_ACT), c);

		int i = 0;
		int row = 1;

		/*if (!collidersList.isEmpty()) {
			
			Iterator<Collider> itr = collidersList.iterator();
			while (itr.hasNext()) 
			 {
				Collider collider = itr.next();

				c.gridx = 0;
				c.gridy = row;
				JComboBox priEle = new JComboBox(dataList);
				priEle.setSelectedItem(collider.getPrimaryComponent().getBaseName());
				collision.add(priEle, c);

				c.gridx = 1;
				c.gridy = row;
				JComboBox secEle = new JComboBox(dataList);
				secEle.setSelectedItem(collider.getSecondaryComponent().getBaseName());
				collision.add(secEle, c);

				c.gridx = 2;
				c.gridy = row;
				JComboBox priAct = new JComboBox(collisionData);
				priAct.setSelectedItem(collider.getPrimaryCollisionType());
				collision.add(priAct, c);

				c.gridx = 3;
				c.gridy = row;
				JComboBox secAct = new JComboBox(collisionData);
				secAct.setSelectedItem(collider.getSecondaryCollisionType());
				collision.add(secAct, c);

				row++;
			}
		}*/

		c.gridx = 0;
		c.gridy = row;
		collision.add(primaryElement, c);

		c.gridx = 1;
		c.gridy = row;
		collision.add(secondaryElement, c);

		c.gridx = 2;
		c.gridy = row;
		collision.add(primaryAction, c);

		c.gridx = 3;
		c.gridy = row;
		collision.add(secondaryAction, c);

		return collision;
	}

	public ColliderData getProperties() {
		if (result == JOptionPane.OK_OPTION) {

			logger.debug("Collision added");

			colliderData.setPrimaryElement((String) primaryElement.getSelectedItem());
			colliderData.setSecondaryElement((String) secondaryElement.getSelectedItem());
			colliderData.setPrimaryAct((CollisionType) primaryAction.getSelectedItem());
			colliderData.setSecondaryAct((CollisionType) secondaryAction.getSelectedItem());
		}
		return colliderData;
	}

}