package com.commands;

import java.io.Serializable;

/**
 * Command interface customized to support the application
 */
public interface Command extends Serializable{
		
	public void execute();

	public void undo();
	
}
