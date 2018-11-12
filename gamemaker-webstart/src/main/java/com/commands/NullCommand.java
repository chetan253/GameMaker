package com.commands;

import com.infrastructure.AbstractComponent;

public class NullCommand implements Command{
	
	private AbstractComponent component;
	
	public NullCommand(AbstractComponent component) {
		this.component = component;
	}
	
	@Override
	public void execute() {
		
	}

	@Override
	public void undo() {
		
	}

}
