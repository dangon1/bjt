package com.phase;

import com.parties.Dealer;
import com.parties.Player;

import lombok.Setter;

@Setter
public abstract class Phase {
	
	protected Player player;
	protected Dealer dealer;
	
	public abstract void executePhase();

}
