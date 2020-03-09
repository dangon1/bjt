package com.phase;

import java.util.HashMap;

import com.cards.CardsBet;
import com.parties.Dealer;
import com.parties.Player;

public class DiscardCardsPhase extends Phase{

	public DiscardCardsPhase(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
	}

	@Override
	public void executePhase() {
		discardHands();
		
	}
	
	public void discardHands() {

		player.setCardsBetMap(new HashMap<Integer, CardsBet>() {
			{
				put(0, CardsBet.builder().build());
			}
		});
		
		dealer.getCards().clear();

	}


}
