package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class ReappearLeftCommandTest {

	@Mock
	AbstractComponent component;
	
	@InjectMocks
	ReappearLeftCommand reappearLeftCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void leftRepeatComponentTest() {
		reappearLeftCommand.execute();
		verify(component).setX(anyInt());
	}
}
