package com.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFileChooser;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.commands.ChangeDirection;
import com.commands.Command;
import com.commands.MoveDownCommand;
import com.commands.MoveLeftCommand;
import com.commands.MoveRightCommand;
import com.commands.MoveUpCommand;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.Collision;
import com.infrastructure.CollisionType;
import com.infrastructure.ComponentType;
import com.infrastructure.Constants;
import com.infrastructure.Direction;
import com.infrastructure.ElementType;
import com.infrastructure.ObjectProperties;
import com.observable.GameTimer;
import com.strategy.DrawOvalColor;
import com.strategy.DrawOvalImage;
import com.strategy.DrawRectColor;
import com.view.ColliderData;
import com.view.CollisionFormPanel;
import com.view.FormView;
import com.view.ObjectPropertiesPanel;
import com.view.WindowFrame;

public class GameMakerController implements ActionListener, MouseListener {

	protected static Logger logger = LogManager.getLogger(GameMakerController.class);
	private WindowFrame windowFrame;
	private FormView formData;
	private ColliderData colliderData;
	private AbstractComponent component;
	private ArrayList<AbstractComponent> allComponents;
	private ArrayList<AbstractComponent> timeComponents;
	private ArrayList<AbstractComponent> fireComponents;
	private ArrayList<AbstractComponent> collectibles;
	private ArrayList<AbstractComponent> playerObjects;
	private ArrayList<AbstractComponent> bullets;
	private ArrayList<Collider> colliders;
	private ArrayList<String> componentNames;
	private HashMap<String, AbstractComponent> componentIdMap;
	private HashMap<Integer, List<Command>> keyActionMap;
	private ArrayList<AbstractComponent> rotatorList;
	private GameTimer gameTimer;
	private GamePlayController gamePlayController;
	private Collision collision;
	private int score;
	private int totalCollectibles = 0;
	private int idCounter;
	private boolean gameStarted;

	public GameMakerController() {
		init();
	}
	
	public GameMakerController(WindowFrame windowFrame, GameTimer gameTimer) {
		this.windowFrame = windowFrame;
		this.gameTimer = gameTimer;
		init();
		initBounds("TOPWALL", 0, 1, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("LEFTWALL", 1, 0, 2, Constants.GAME_PANEL_HEIGHT);
		initBounds("BOTTOMWALL", 0, Constants.GAME_PANEL_HEIGHT, Constants.GAME_PANEL_WIDTH, 2);
		initBounds("RIGHTWALL", Constants.GAME_PANEL_WIDTH, 0, 2, Constants.GAME_PANEL_HEIGHT);
	}
	
	public void init() {
		allComponents = new ArrayList<>();
		timeComponents = new ArrayList<>();
		colliders = new ArrayList<>();
		collectibles = new ArrayList<>();
		keyActionMap = new HashMap<>();
		componentIdMap = new HashMap<>();
		rotatorList = new ArrayList<>();
		fireComponents = new ArrayList<>();
		bullets = new ArrayList<>();
		playerObjects = new ArrayList<>();
		componentNames = new ArrayList<>();
		this.gameStarted = false;
		this.collision = new Collision();
	}

	public void addComponent(int x, int y) {
		Command command;
		createAbstractComponent();
		component = getComponent();

		if (component != null) {
			component.setX(x);
			component.setY(y);
			componentIdMap.put(component.getComponentName(), component);
			allComponents.add(component);
			if (formData.getKeyActionMap() != null) {
				for (Map.Entry<Integer, String> entry : formData.getKeyActionMap().entrySet()) {
					Integer key = entry.getKey();
					command = createCommand(entry.getValue(), component);
					if (keyActionMap.containsKey(key)) {
						keyActionMap.get(key).add(command);
					} else {
						keyActionMap.put(key, new ArrayList<Command>());
						keyActionMap.get(key).add(command);
					}
				}
				component.setPlayerObject(true);
				playerObjects.add(component);
			} else if (formData.getTimeActionArray() != null) {

				if (formData.getTimeActionArray().contains(Constants.FREE)) {
					component.setDirection(Direction.FREE);

				} else if ((formData.getTimeActionArray()).size() == 4) {
					new ChangeDirection(component).execute();

				}
				if (!formData.isRotateable())
					timeComponents.add(component);
			}
		}

		if (formData.isCollectible()) {
			component.setCollectible(true);
			collectibles.add(component);
		}

		if (formData.isRotateable())
			rotatorList.add(component);

	}

	public void addCollider() {

		List<AbstractComponent> primaryComponents = getComponentByName(colliderData.getPrimaryElement());
		List<AbstractComponent> secondaryComponents = getComponentByName(colliderData.getSecondaryElement());

		for (AbstractComponent primeComponent : primaryComponents)
			for (AbstractComponent secComponent : secondaryComponents) {
				Collider collider = new Collider(primeComponent, secComponent, colliderData.getPrimaryAct(),
						colliderData.getSecondaryAct(), collision);
				colliders.add(collider);
			}
	}

	public void initBounds(String name, int x, int y, int width, int height) {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent component = new AbstractComponent(objectProperties);
		component.setX(x);
		component.setY(y);
		component.setWidth(width);
		component.setHeight(height);
		component.setVisbility(true);
		component.setColor(Color.BLACK);
		component.setDrawable(new DrawRectColor());
		component.setComponentName(name);
		component.setBaseName(name);
		componentIdMap.put(name + "_", component);
		componentNames.add(name);
		windowFrame.getGamePanel().addComponent(component);
		windowFrame.draw(null);
	}

	public Command createCommand(String commandType, AbstractComponent component) {
		switch (commandType) {
		case Constants.MOVE_DOWN:
			return new MoveDownCommand(component);
		case Constants.MOVE_UP:
			return new MoveUpCommand(component);
		case Constants.MOVE_LEFT:
			return new MoveLeftCommand(component);
		case Constants.MOVE_RIGHT:
			return new MoveRightCommand(component);
		default:
			return null;
		}
	}

	// Creates Component by taking form data user defined for game element from view
	public void createAbstractComponent() {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent component = new AbstractComponent(objectProperties);
		// component.setX(formData.getX());
		// component.setY(formData.getY());
		if (formData != null) {
			component.setComponentName(formData.getElementName() + "_" + Integer.toString(idCounter));
			component.setBaseName(formData.getElementName());
			component.setVelX(formData.getVelX());
			component.setVelY(formData.getVelY());
			component.setColor(formData.getColor());
			component.setWidth(formData.getWidth());
			component.setHeight(formData.getHeight());
			component.setImage(formData.getBackgroundLocation());
			component.setVisbility(true);
			component.setCanFire(formData.isFire());
			if (null != component.getImage() && !component.getImage().equalsIgnoreCase("")) {
				component.setDrawable(new DrawOvalImage());
			} else {
				if (formData.getElementType() == ElementType.CIRCLE) {
					component.setDrawable(new DrawOvalColor());
				} else if (formData.getElementType() == ElementType.RECTANGLE) {
					component.setDrawable(new DrawRectColor());
				}
			}

			this.idCounter++;
		}

		if (component.isCanFire()) {
			fireComponents.add(component);
		}

		setComponent(component);
	}

	public void createBullet(AbstractComponent parentComponent) {
		ObjectProperties objectProperties = new ObjectProperties();
		AbstractComponent bullet = new AbstractComponent(objectProperties);
		bullet.setX(parentComponent.getX() + (parentComponent.getWidth() / 2));
		bullet.setY(parentComponent.getY());
		bullet.setVelX(0);
		bullet.setVelY(10);
		bullet.setColor(Color.BLACK);
		bullet.setWidth(4);
		bullet.setHeight(8);
		bullet.setVisbility(true);
		bullet.setDrawable(new DrawRectColor());
		windowFrame.getGamePanel().addComponent(bullet);
		bullets.add(bullet);

		addBulletstoColliders(bullet);
	}

	public void addBulletstoColliders(AbstractComponent bullet) {
		for (AbstractComponent component : allComponents) {
			if (component.isCollectible()) {
				Collider collider = new Collider(bullet, component, CollisionType.EXPLODE, CollisionType.EXPLODE,
						collision);
				colliders.add(collider);
			}
		}
	}

	public void save() {
		// pause();
		try {
			String fileName = windowFrame.showSaveDialog();
			if (!fileName.isEmpty()) {
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				windowFrame.save(out);
				// out.writeObject(commandQueue);

				out.writeObject(allComponents);// remove this if you need to remove all components in future
				out.writeObject(timeComponents);
				out.writeObject(componentIdMap);
				out.writeObject(colliders);
				out.writeObject(keyActionMap);
				out.writeObject(componentNames);
				out.writeObject(collectibles);
				out.writeObject(fireComponents);
				out.writeObject(playerObjects);

				out.close();
				fileOut.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

	}

	public void load(InputStream fileName) throws ClassNotFoundException {
		try {
			if (fileName != null) {
				ObjectInputStream in = new ObjectInputStream(fileName);

				windowFrame.load(in);

				allComponents = (ArrayList<AbstractComponent>) in.readObject();
				timeComponents = (ArrayList<AbstractComponent>) in.readObject();
				componentIdMap = (HashMap<String, AbstractComponent>) in.readObject();
				colliders = (ArrayList<Collider>) in.readObject();
				keyActionMap = (HashMap<Integer, List<Command>>) in.readObject();
				componentNames = (ArrayList<String>) in.readObject();
				collectibles = (ArrayList<AbstractComponent>) in.readObject();
				fireComponents = (ArrayList<AbstractComponent>) in.readObject();
				playerObjects = (ArrayList<AbstractComponent>) in.readObject();

				in.close();
//				fileIn.close();
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		windowFrame.draw(null);
	}

	private List<AbstractComponent> getComponentByName(String name) {

		List<AbstractComponent> components = new ArrayList<>();

		for (Entry<String, AbstractComponent> component : componentIdMap.entrySet()) {
			if (component.getKey().startsWith(name + "_")) {
				components.add(component.getValue());
			}
		}

		return components;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		ComponentType componentType = ComponentType.valueOf(e.getActionCommand().toUpperCase());

		if (componentType.equals(ComponentType.BACKGROUND)) {

			// JPanel panel = new JPanel(new ImageIcon("images/background.png").getImage());
			windowFrame.getGamePanel().setImgPath(fileExplorer());
			windowFrame.getGamePanel().repaint();
			// windowFrame.getContentPane()
		}

		else if (componentType.equals(ComponentType.PLAY)) {
			totalCollectibles = collectibles.size();
			gameTimer.registerObserver(gamePlayController);
			windowFrame.getGamePanel().addKeyListener(gamePlayController);
			windowFrame.getGamePanel().requestFocus();
			this.gameStarted = true;

		}

		else if (componentType.equals(ComponentType.SAVE)) {
			save();
		}

		else if (componentType.equals(ComponentType.LOAD)) {
			try {
				String fileName = windowFrame.showOpenDialog();
//				ClassLoader loader = getClass().getClassLoader();
//				System.out.println("keyload press in gamemaker file name = "+file.getName());
				File file = new File(fileName);
				
				load(new FileInputStream(file));
				windowFrame.setFocusForGamePanel();
			} catch (ClassNotFoundException | FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		else if (componentType.equals(ComponentType.ELEMENT)) {
			ObjectPropertiesPanel popUp = new ObjectPropertiesPanel();
			setFormData(popUp.getProperties());
			this.idCounter = 1;
			componentNames.add(formData.getElementName());
			// addComponent();

		} else if (componentType.equals(ComponentType.COLLISION)) 
		{

			CollisionFormPanel popUp = new CollisionFormPanel(componentNames.toArray());

			colliderData = popUp.getProperties();
			addCollider();

		} else if (componentType.equals(ComponentType.PAUSE)) {
			gameTimer.removeObserver(gamePlayController);
			windowFrame.getGamePanel().removeKeyListener(gamePlayController);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		if (!this.gameStarted) {
			int x = arg0.getX();
			int y = arg0.getY();
			
			addComponent(x, y);

			windowFrame.getGamePanel().addComponent(component);
			windowFrame.draw(null);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public String fileExplorer() {
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = chooser.showOpenDialog(windowFrame.getGamePanel());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			String path = file.getAbsolutePath();
			System.out.println("path = " + path);
			return path;
		}
		return null;

	}

	public List<Command> getComponentListForKeys(int key) {
		if (keyActionMap.containsKey(key)) {
			return keyActionMap.get(key);
		}
		return null;
	}

	public AbstractComponent getComponent() {
		return component;
	}

	public void setComponent(AbstractComponent component) {
		this.component = component;
	}

	public ArrayList<AbstractComponent> getAllComponents() {
		return allComponents;
	}

	public void setAllComponents(ArrayList<AbstractComponent> allComponents) {
		this.allComponents = allComponents;
	}

	public List<AbstractComponent> getTimeComponents() {
		return timeComponents;
	}

	public void setTimeComponents(ArrayList<AbstractComponent> timeComponents) {
		this.timeComponents = timeComponents;
	}

	public ArrayList<Collider> getColliders() {
		return colliders;
	}

	public void setColliders(ArrayList<Collider> colliders) {
		this.colliders = colliders;
	}

	public HashMap<String, AbstractComponent> getComponentIdMap() {
		return componentIdMap;
	}

	public void setComponentIdMap(HashMap<String, AbstractComponent> componentIdMap) {
		this.componentIdMap = componentIdMap;
	}

	public HashMap<Integer, List<Command>> getKeyActionMap() {
		return keyActionMap;
	}

	public void setKeyActionMap(HashMap<Integer, List<Command>> keyActionMap) {
		this.keyActionMap = keyActionMap;
	}

	public void setGamePlayController(GamePlayController gamePlayController) {
		this.gamePlayController = gamePlayController;
	}

	public GamePlayController getGamePlayController() {
		return this.gamePlayController;
	}

	public ArrayList<AbstractComponent> getRotatorList() {
		return rotatorList;
	}

	public void setRotatorList(ArrayList<AbstractComponent> rotatorList) {
		this.rotatorList = rotatorList;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<AbstractComponent> getFireComponents() {
		return fireComponents;
	}

	public void setFireComponents(ArrayList<AbstractComponent> fireComponents) {
		this.fireComponents = fireComponents;
	}

	public ArrayList<AbstractComponent> getBullets() {
		return bullets;
	}

	public void setBullets(ArrayList<AbstractComponent> bullets) {
		this.bullets = bullets;
	}

	public ArrayList<AbstractComponent> getCollectibles() {
		return collectibles;
	}

	public void setCollectibles(ArrayList<AbstractComponent> collectibles) {
		this.collectibles = collectibles;
	}

	public int getTotalCollectibles() {
		return totalCollectibles;
	}

	public void setTotalCollectibles(int totalCollectibles) {
		this.totalCollectibles = totalCollectibles;
	}

	public ArrayList<AbstractComponent> getPlayerObjects() {
		return playerObjects;
	}

	public void setPlayerObjects(ArrayList<AbstractComponent> playerObjects) {
		this.playerObjects = playerObjects;
	}
	
	public FormView getFormData() {
		return formData;
	}

	public void setFormData(FormView formData) {
		this.formData = formData;
	}

}
