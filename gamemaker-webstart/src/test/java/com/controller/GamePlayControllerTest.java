package com.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.infrastructure.AbstractComponent;
import com.infrastructure.Collider;
import com.infrastructure.Collision;
import com.infrastructure.CollisionType;
import com.infrastructure.Direction;
import com.observable.GameTimer;
import com.view.WindowFrame;

class GamePlayControllerTest {

	GameMakerController gameMakerController;
	
	GamePlayController gamePlayController;
	
	AbstractComponent component;
	@BeforeEach
	void setup() {
		component = mock(AbstractComponent.class);
		gameMakerController = mock(GameMakerController.class);
		WindowFrame windowFrame = new WindowFrame();
		GameTimer timer= new GameTimer();
		
		gamePlayController = new GamePlayController(windowFrame, timer, gameMakerController);
		
	}
	
	@Test
	void checkCollidersTest() {
		Collider collider1 = mock(Collider.class);
		ArrayList<Collider> colliders = new ArrayList<>();
		colliders.add(collider1);
		when(gameMakerController.getColliders()).thenReturn(colliders);
		
		ArrayList<AbstractComponent> rotatorList = new ArrayList<>();
		rotatorList.add(component);
		when(gameMakerController.getRotatorList()).thenReturn(rotatorList);
		
		ArrayList<AbstractComponent> bulletList = new ArrayList<>();
		bulletList.add(component);
		when(component.getY()).thenReturn(1);
		when(gameMakerController.getBullets()).thenReturn(bulletList);
		
		ArrayList<AbstractComponent> timeComponents = new ArrayList<>();
		timeComponents.add(component);
		when(gameMakerController.getTimeComponents()).thenReturn(timeComponents);
		Collision collision = mock(Collision.class);
		when(collision.checkCollisionBetweenAbstractComponentAndBounds(component)).thenReturn(Direction.BOTH);
		
		ArrayList<AbstractComponent> collectibles = new ArrayList<>();
		collectibles.add(component);
		when(gameMakerController.getCollectibles()).thenReturn(collectibles);
		when(component.getVisibility()).thenReturn(true);
		when(gameMakerController.getTotalCollectibles()).thenReturn(1);
		gamePlayController.update();
		
		
		assertEquals(1, gameMakerController.getColliders().size());
		assertEquals(1, gameMakerController.getRotatorList().size());
		assertEquals(1, gameMakerController.getTimeComponents().size());
		assertEquals(1, gameMakerController.getCollectibles().size());
	}
	

}
