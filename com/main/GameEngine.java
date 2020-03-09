package com.main;

import static com.utils.Constants.MAX_NUMBER_OF_PLAYS;

import com.parties.Table;
import com.phase.PhaseFactory;
import com.statistics.CounterManager;
import com.ui.ConsoleManager;

import lombok.Getter;

@Getter
public class GameEngine {

	private CounterManager counter;
	private Table table;

	public void setup() {
		counter = new CounterManager();
		table = new Table();
		table.setup();

	}

	public void play() {
		PhaseFactory phaseFactory = new PhaseFactory(table.getPlayer(), table.getDealer());
		while (counter.getNumberOfPlays() < MAX_NUMBER_OF_PLAYS) {
			phaseFactory.getPhasesOrder().forEach(phase -> phaseFactory.getPhase(phase).executePhase());
			counter.addNumberOfPlays();
		}
		ConsoleManager.showResults(table.getPlayer());
	}

}
