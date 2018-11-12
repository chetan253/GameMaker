package com.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CollisionTest {

	@Mock
	AbstractComponent component1;
	
	@Mock
	AbstractComponent component2;
	
	@InjectMocks
	Collision collision;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		
		
	}
	
	@Test
	void testCollision() {
		when(component1.getShape()).thenReturn(ComponentShape.RECTANGLE);
		when(component1.getX()).thenReturn(10);
		when(component1.getY()).thenReturn(40);
		when(component1.getWidth()).thenReturn(100);
		when(component1.getHeight()).thenReturn(30);
		when(component2.getX()).thenReturn(1100);
		when(component2.getY()).thenReturn(50);
		when(component2.getWidth()).thenReturn(10);
		when(component2.getHeight()).thenReturn(10);
		Collision collision = new Collision();
		assertEquals(Direction.X, collision.checkCollisionBetweenAbstractComponents(component1, component2));
	}
	
	@Test
	void checkBoundsTest() {
		AbstractComponent component = mock(AbstractComponent.class);
		when(component.getX()).thenReturn(1);
		when(component.getVelX()).thenReturn(-2);
		assertEquals(Direction.X, collision.checkCollisionBetweenAbstractComponentAndBounds(component));
	}

	@Test
	void checkBoundsTest1() {
		AbstractComponent component = mock(AbstractComponent.class);
		when(component.getX()).thenReturn(1);
		when(component.getY()).thenReturn(0);
		when(component.getVelY()).thenReturn(-2);
		assertEquals(Direction.Y, collision.checkCollisionBetweenAbstractComponentAndBounds(component));
	}
	
	@Test
	void checkCollisionBetweenAbstractComponentsTest() {
		when(component1.getX()).thenReturn(10);
		when(component1.getY()).thenReturn(40);
		when(component1.getWidth()).thenReturn(100);
		when(component1.getHeight()).thenReturn(30);
		when(component2.getX()).thenReturn(1100);
		when(component2.getY()).thenReturn(50);
		when(component2.getWidth()).thenReturn(10);
		when(component2.getHeight()).thenReturn(10);
		when(component1.getShape()).thenReturn(ComponentShape.CIRCLE);
		
		assertEquals(Direction.Y, collision.checkCollisionBetweenAbstractComponents(component1, component2));
	}
}
