package com.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.commands.Command;

class ColliderTest {

	AbstractComponent primaryComponent;
	AbstractComponent secondaryComponent;
	
	Collider collider;
	
	Collision collision;
	@BeforeEach
	void setup(){
		primaryComponent = mock(AbstractComponent.class);
		secondaryComponent = mock(AbstractComponent.class);
		collision = mock(Collision.class);
		
	}
	
	@Test
	void executeTest(){
		collider = new Collider(primaryComponent, secondaryComponent, CollisionType.BOUNCE, CollisionType.FIXED, collision);
		when(primaryComponent.getVisibility()).thenReturn(true);
		when(secondaryComponent.getVisibility()).thenReturn(true);
		when(collision.checkIntersectionBetweenElements(primaryComponent, secondaryComponent)).thenReturn(true);
		when(collision.checkCollisionBetweenAbstractComponents(primaryComponent, secondaryComponent)).thenReturn(Direction.X);
		collider.execute();
		assertEquals(Direction.X, collision.checkCollisionBetweenAbstractComponents(primaryComponent, secondaryComponent));
	}
	
	@Test
	void executeTest1(){
		collider = new Collider(primaryComponent, secondaryComponent, CollisionType.CHANGE_DIRECTION, CollisionType.FIXED, collision);
		when(primaryComponent.getVisibility()).thenReturn(true);
		when(secondaryComponent.getVisibility()).thenReturn(true);
		when(collision.checkIntersectionBetweenElements(primaryComponent, secondaryComponent)).thenReturn(true);
		when(collision.checkCollisionBetweenAbstractComponents(primaryComponent, secondaryComponent)).thenReturn(Direction.X);
		collider.execute();
		assertEquals(Direction.X, collision.checkCollisionBetweenAbstractComponents(primaryComponent, secondaryComponent));
	}

}
