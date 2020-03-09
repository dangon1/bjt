package com.phase;

import java.util.Arrays;
import java.util.List;

import com.parties.Dealer;
import com.parties.Player;

public class PhaseFactory {

	private static final String BET_PHASE = "BET";
	private static final String DEAL_PHASE = "DEAL";
	private static final String DECISION_PHASE = "DECISION";
	private static final String DISCARD_PHASE = "DISCARD";

	private Player player;
	private Dealer dealer;

	public PhaseFactory(Player player, Dealer dealer) {
		super();
		this.player = player;
		this.dealer = dealer;
	}

	public Phase getPhase(String phaseType) {

		if (phaseType == null) {
			return null;
		}

		if (phaseType == BET_PHASE) {
			return new BetPhase(player, dealer);

		} else if (phaseType == DEAL_PHASE) {
			return new DealCardsPhase(player, dealer);

		} else if (phaseType == DECISION_PHASE) {
			return new DecisionPhase(player, dealer);

		} else if (phaseType == DISCARD_PHASE) {
			return new DiscardCardsPhase(player, dealer);

		}
		return null;

	}

	public List<String> getPhasesOrder() {
		return Arrays.asList(BET_PHASE, DEAL_PHASE, DECISION_PHASE, DISCARD_PHASE);
	}

}
