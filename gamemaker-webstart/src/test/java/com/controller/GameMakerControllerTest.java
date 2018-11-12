package com.controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.commands.Command;
import com.controller.GameMakerController;
import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.Constants;
import com.infrastructure.ObjectProperties;
import com.observable.GameTimer;
import com.view.FormView;
import com.view.GamePanel;
import com.view.WindowFrame;

class GameMakerControllerTest {

	ObjectProperties properties;
	AbstractComponent component;
	
	GameMakerController gameMakerController;
	
	@BeforeEach
	void setup() {
		component = mock(AbstractComponent.class);
		WindowFrame windowFrame = mock(WindowFrame.class);
		GameTimer timer = mock(GameTimer.class);
		
		gameMakerController = new GameMakerController();
		gameMakerController.setComponent(component);
		ArrayList<AbstractComponent> allComponents = new ArrayList<>();
		ArrayList<AbstractComponent> timeComponents = new ArrayList<>();
		ArrayList<Collider> colliders = new ArrayList<>();
		ArrayList<AbstractComponent> collectibles = new ArrayList<>();
		HashMap<Integer, List<Command>> keyActionMap = new HashMap<>();
		HashMap<String, AbstractComponent> componentIdMap = new HashMap<>();
		ArrayList<AbstractComponent> rotatorList = new ArrayList<>();
		ArrayList<AbstractComponent> fireComponents = new ArrayList<>();
		ArrayList<AbstractComponent> bullets = new ArrayList<>();
		ArrayList<AbstractComponent> playerObjects = new ArrayList<>();
		gameMakerController.setAllComponents(allComponents);
		gameMakerController.setTimeComponents(timeComponents);
		gameMakerController.setColliders(colliders);
		gameMakerController.setCollectibles(collectibles);
		gameMakerController.setKeyActionMap(keyActionMap);
		gameMakerController.setComponentIdMap(componentIdMap);
		gameMakerController.setRotatorList(rotatorList);
		gameMakerController.setFireComponents(fireComponents);
		gameMakerController.setBullets(bullets);
		gameMakerController.setPlayerObjects(playerObjects);
	}
	
	@Test
	void testAddComponent() {
		FormView formData = mock(FormView.class);
		gameMakerController.setFormData(formData);
		HashMap<Integer, String> keyActionMapForm = new HashMap<Integer, String>();
		keyActionMapForm.put(KeyEvent.VK_UP, Constants.MOVE_UP);
		keyActionMapForm.put(KeyEvent.VK_DOWN, Constants.MOVE_DOWN);
		keyActionMapForm.put(KeyEvent.VK_LEFT, Constants.MOVE_LEFT);
		keyActionMapForm.put(KeyEvent.VK_RIGHT, Constants.MOVE_RIGHT);
		when(formData.getKeyActionMap()).thenReturn(keyActionMapForm);
		
		ArrayList<String> timeActionForm = new ArrayList<String>();
		
		timeActionForm.add(Constants.FREE);
		timeActionForm.add(Constants.MOVE_RIGHT);
		timeActionForm.add(Constants.MOVE_LEFT);
		timeActionForm.add(Constants.MOVE_DOWN);
		when(formData.getTimeActionArray()).thenReturn(timeActionForm);
		when(formData.isCollectible()).thenReturn(true);
		when(formData.isRotateable()).thenReturn(true);
		when(component.getComponentName()).thenReturn("name_1");
		gameMakerController.addComponent(100, 100);
		assertEquals(1, gameMakerController.getAllComponents().size());
		assertEquals(1, gameMakerController.getCollectibles().size());
		assertEquals(1, gameMakerController.getPlayerObjects().size());
		assertEquals(1, gameMakerController.getRotatorList().size());
	}
	
	@Test
	void addBulletsToCollidersTest() {
		when(component.isCollectible()).thenReturn(true);
		gameMakerController.getAllComponents().add(component);
		gameMakerController.addBulletstoColliders(component);
		
		assertEquals(1, gameMakerController.getColliders().size());
	}
	
}
