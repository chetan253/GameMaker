package com.commands;

import com.components.Clock;
import com.infrastructure.Constants;

public class ClockTickCommand implements Command {

	private Clock clock;

	/**
	 * Store the current state then update
	 */
	public ClockTickCommand(Clock clock) {
		this.clock = clock;		
	}

	/**
	 * This command was created when the timer ticked, so we update the time accordingly
	 */
	@Override
	public void execute() {
		clock.setMilisecondsElapsed(clock.getMilisecondsElapsed() + Constants.TIMER_COUNT);
	}

	/**
	 * This command was created when the timer was un-ticked, so we return to previous state
	 */
	@Override
	public void undo() {
		clock.setMilisecondsElapsed(clock.getMilisecondsElapsed() - Constants.TIMER_COUNT);
	}
}
