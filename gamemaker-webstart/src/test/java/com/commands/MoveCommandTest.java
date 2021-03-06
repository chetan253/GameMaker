package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class MoveCommandTest {

	@Mock
	AbstractComponent component;
	
	@InjectMocks
	MoveCommand moveCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testExecute() {
		moveCommand.execute();
		verify(component).setX(anyInt());
		verify(component).setY(anyInt());
	}
	
}
