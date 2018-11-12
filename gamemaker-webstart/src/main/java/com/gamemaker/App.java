package com.gamemaker;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.controller.GameMakerController;
import com.controller.GamePlayController;
import com.controller.OnlineGameSaveController;
import com.observable.GameTimer;
import com.view.FormPanel;
import com.view.GamePanel;
import com.view.MainPanel;
import com.view.WindowFrame;

public class App {

	private static boolean gameMakerStatus;
	private static String gameId;
	protected static Logger logger = LogManager.getLogger(App.class);
	private static JRadioButton pacman;
	private static JRadioButton spaceInvaders;
	private static JRadioButton frogger;
	private static InputStream gameLoadFile;
	private static ButtonGroup buttonGroup;

	public static void makeGame() throws ClassNotFoundException {

		GameTimer gameTimer = new GameTimer();
		gameTimer.startTimer();

		JFrame userNameFrame = new JFrame();
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel jLabel = new JLabel();
		jLabel.setText("Enter your name: ");
		JTextField textField = new JTextField(10);
		panel.add(jLabel);
		panel.add(textField);
		String userName = new String();
		buttonGroup = new ButtonGroup();

		if (!gameMakerStatus) {
			pacman = new JRadioButton("Pac-Man");
			pacman.setActionCommand(pacman.getText());
			buttonGroup.add(pacman);
			panel.add(pacman);
			spaceInvaders = new JRadioButton("Space-Invaders");
			spaceInvaders.setActionCommand(spaceInvaders.getText());
			buttonGroup.add(spaceInvaders);
			panel.add(spaceInvaders);
			frogger = new JRadioButton("Frogger");
			frogger.setActionCommand(frogger.getText());
			buttonGroup.add(frogger);
			panel.add(frogger);
			pacman.setSelected(true);
			
			userNameFrame.add(panel);
		}

		int option = JOptionPane.showConfirmDialog(null, panel, "User Input : ", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (JOptionPane.OK_OPTION == option) {
			userName = textField.getText();
			if (!gameMakerStatus) {
				gameId = buttonGroup.getSelection().getActionCommand();
			}
		} else {
			// Do something else.
			System.exit(0);
		}
		userNameFrame.dispose();

		OnlineGameSaveController gameSaveController = OnlineGameSaveController.getInstance();

		gameSaveController.initSession(gameId, userName);

		WindowFrame windowFrame = new WindowFrame();

		MainPanel mainPanel = new MainPanel();
		windowFrame.addComponent(mainPanel);

		FormPanel formPanel = new FormPanel(windowFrame);
		mainPanel.addComponent(formPanel);

		GamePanel gamePanel = new GamePanel();
		mainPanel.addComponent(gamePanel);
		windowFrame.setGamePanel(gamePanel);

		GameMakerController gmController = new GameMakerController(windowFrame, gameTimer);

		GamePlayController gpController = new GamePlayController(windowFrame, gameTimer, gmController);

		gmController.setGamePlayController(gpController);
		formPanel.setFormPanelStatus(gameMakerStatus);
		formPanel.initializeFormPanel();

		gamePanel.addControllerListener(gmController);
		
		if(gameId != null) {
			ClassLoader classLoader = App.class.getClassLoader();
			
			if(gameId.equals(frogger.getText())) {
				gameLoadFile = classLoader.getResourceAsStream("Frogger.ser");
			}
			else if(gameId.equals(spaceInvaders.getText())) {
				gameLoadFile = classLoader.getResourceAsStream("SpaceInvaders.ser");
                System.out.print(gameLoadFile==null);
			}
			else {
				gameLoadFile = classLoader.getResourceAsStream("Pacman.ser");
			}
			gmController.load(gameLoadFile);
			windowFrame.setFocusForGamePanel();
		}
		
		windowFrame.addActionListener(gmController);
		
		windowFrame.setVisible(true);
		windowFrame.pack();
	}

	public static void main(String[] args) {
		BasicConfigurator.configure();

		gameMakerStatus = args[0].equals("GameMaker") ? true : false;
		gameId = args[0].equals("GameMaker") ? null : args[1];

		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					makeGame();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
}
