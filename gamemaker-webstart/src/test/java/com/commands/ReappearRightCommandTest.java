package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class ReappearRightCommandTest {

	@Mock
	AbstractComponent component;
	
	@InjectMocks
	ReappearRightCommand reappearRightCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testExecute() {
		reappearRightCommand.execute();
		verify(component).setX(anyInt());
	}

}
