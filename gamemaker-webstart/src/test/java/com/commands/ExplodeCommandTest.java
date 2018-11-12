package com.commands;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class ExplodeCommandTest {

	@Mock
	AbstractComponent component;
	
	@InjectMocks
	ExplodeCommand explodeCommand;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	void testExecute() {
		explodeCommand.execute();
		
		verify(component).setVisbility(anyBoolean());
	}

}
