package com.phase;

import static com.utils.Constants.MINIMUM_BET;

import com.parties.Dealer;
import com.parties.Player;
import com.strategies.CardCountingHiLo;
import com.ui.ConsoleManager;
import com.utils.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BetPhase extends Phase {

	public BetPhase(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
	}

	@Override
	public void executePhase() {
		ConsoleManager.showStartingPhase("BET");
		player.getAI().getResultsCounter().addTotalHands();
		
		ConsoleManager.showTotalCash(player.getTotalCash());
		ConsoleManager.showCount();
		Integer betDeviation;
		if (Constants.WONG_IN) {
			betDeviation = CardCountingHiLo.getTrueCount() > 1 ? CardCountingHiLo.getTrueCount() : 0;
		} else {
			betDeviation = CardCountingHiLo.getTrueCount() > 1 ? CardCountingHiLo.getTrueCount() : 1;
		}
		
		if(CardCountingHiLo.getTrueCount() >= 1) {
			betDeviation = Math.min(Constants.MAXIMUM_BET, betDeviation *= Constants.SPREAD_BET_AGRESSION);
		}
		player.bet(MINIMUM_BET * betDeviation, 0);
		
		
	}

}
