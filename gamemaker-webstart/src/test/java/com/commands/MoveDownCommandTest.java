package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class MoveDownCommandTest {

	@Mock
	AbstractComponent component;
	
	@InjectMocks
	MoveDownCommand moveDownCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testExecute() {
		moveDownCommand.execute();
		
		verify(component).setY(anyInt());
	}
}
