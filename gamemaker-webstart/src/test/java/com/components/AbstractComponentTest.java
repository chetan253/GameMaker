package com.components;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;
import com.infrastructure.ComponentType;
import com.infrastructure.Direction;
import com.infrastructure.Drawable;
import com.infrastructure.ObjectProperties;
import com.strategy.DrawRectColor;

class AbstractComponentTest {
	
	@Mock
	ObjectProperties objectProperties;
	
	@InjectMocks
	AbstractComponent component;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
		when(objectProperties.getX()).thenReturn(100);
		when(objectProperties.getY()).thenReturn(100);
		when(objectProperties.getVelX()).thenReturn(5);
		when(objectProperties.getVelY()).thenReturn(5);
		when(objectProperties.getWidth()).thenReturn(20);
		when(objectProperties.getHeight()).thenReturn(20);
		component.setObjectProperties(objectProperties);
	}

	@Test
	void coordinateTest() {
		assertEquals(100, component.getX());
		assertEquals(100, component.getY());
	}

	@Test
	void dimensionsTest() {
		assertEquals(20, component.getWidth());
		assertEquals(20, component.getHeight());
	}

	@Test
	void velocityTest() {		
		assertEquals(5, component.getVelX());
		assertEquals(5, component.getVelY());
	}

	@Test
	void visibilityTest() {
		component.setVisbility(false);
		assertEquals(false, component.getVisibility());
	}

	@Test
	void directionTest() {
		component.setDirection(Direction.FREE);
		assertEquals(Direction.FREE, component.getDirection());
	}

	@Test
	void imageTest() {
		component.setImage("image path");
		assertEquals("image path", component.getImage());
	}

	@Test
	void componentNameTest() {
		component.setComponentName("component_name");
		assertEquals("component_name", component.getComponentName());
	}
	
	@Test
	void objectPropertiesTest() {
		assertEquals(objectProperties, component.getObjectProperties());
	}
	
	@Test
	void colorTest() {
		component.setColor(Color.BLACK);
		assertEquals(Color.BLACK, component.getColor());
	}
	
	void collectibleTest() {
		component.setCollectible(true);
		assertEquals(true, component.isCollectible());
	}
	
}