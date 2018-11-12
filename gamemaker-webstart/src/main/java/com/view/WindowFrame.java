package com.view;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.infrastructure.Constants;
import com.infrastructure.IAddActionListener;
import com.infrastructure.IComposite;
import com.infrastructure.ObjectProperties;

@SuppressWarnings("serial")
public class WindowFrame extends JFrame implements IComposite, IAddActionListener {
	protected static Logger logger = LogManager.getLogger(WindowFrame.class);
	private ArrayList<IComposite> compositeList;
	private GamePanel gamePanel; // game panel is also used in gameplay controller
	private JFileChooser fileChooser;

	public WindowFrame() {
		super();
		this.compositeList = new ArrayList<IComposite>();
		initializeUI();
		logger.debug("WindowFrame constructed");
	}

	public void setFocusForGamePanel() {

		for (IComposite composite : compositeList) {
			if (composite instanceof MainPanel)
				((MainPanel) composite).setFocusForGamePanel();
			return;
		}

	}

	public String showOpenDialog() {
		try {
			fileChooser = new JFileChooser();
			fileChooser.setFileFilter(new FileNameExtensionFilter("serialize file", "ser"));
			int rVal = fileChooser.showOpenDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String name = fileChooser.getSelectedFile().toString();
				return name;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public String showSaveDialog() {
		try {
			fileChooser = new JFileChooser();
			fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
			fileChooser.setFileFilter(new FileNameExtensionFilter("serialize file", "ser"));
			int rVal = fileChooser.showSaveDialog(null);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				String name = fileChooser.getSelectedFile().toString();
				if (!name.endsWith(".ser"))
					name += ".ser";
				return name;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}

	private void initializeUI() {
		setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
	}

	public void setVisible() {
		super.setVisible(true);
	}

	public void draw(Graphics g) {
		for (IComposite composite : compositeList) {
			composite.draw(g);
		}
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

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public void setGamePanel(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	public ObjectProperties getActiveObjectProperties() {
		for (IComposite composite : compositeList) {
			if (composite instanceof MainPanel)
				return ((MainPanel) composite).getActiveObjectProperties();
		}

		return null;
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
