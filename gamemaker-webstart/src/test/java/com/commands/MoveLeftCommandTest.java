package com.commands;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.infrastructure.AbstractComponent;

class MoveLeftCommandTest {

	@Mock
	AbstractComponent component;

	@InjectMocks
	MoveLeftCommand moveLeftCommand;

	@BeforeEach
	void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void test() {
		moveLeftCommand.execute();
		verify(component).setX(anyInt());
	}

}
