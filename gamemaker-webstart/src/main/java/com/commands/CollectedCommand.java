package com.commands;

import com.infrastructure.AbstractComponent;

public class CollectedCommand implements Command {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3848015843967223091L;
	private AbstractComponent abstractComponent;

	public CollectedCommand(AbstractComponent abstractComponent) {
		this.abstractComponent = abstractComponent;
		execute();
	}

	@Override
	public void execute() {
//		for(int i=0;i<ScreenController.brickList.size();i++) {
//			Brick b = ScreenController.brickList.get(i);
//			if (brick.getBrickId() == b.getBrickId() ) {
//				ScreenController.brickList.remove(i);
//			}
//		}
		//this.abstractComponent.performAction();
	}

	@Override
	public void undo() {
		//this.abstractComponent.performAction();
	}
	
//	public String save() {
//		return "Brick BrickId= " + brick.getBrickId();
//	}

}
