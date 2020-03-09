package com.phase;

import com.cards.Card;
import com.parties.Dealer;
import com.parties.Player;
import com.parties.Table;
import com.strategies.CardCountingHiLo;
import com.utils.CardUtils;

public class DealCardsPhase extends Phase {

	public DealCardsPhase(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
	}

	@Override
	public void executePhase() {
		distributeCards();
		sortAllDealtCards();
		
	}
	
	private void distributeCards() {

		for (int i = 0; i < 2; i++) {
			player.getCardsBetMap().keySet().stream().forEach(setNumber -> {
				Card hitCard = Table.deckPack.getCard();
				player.getCardsFromSet(setNumber).add(hitCard);
				CardCountingHiLo.addRunningCount(hitCard);
			});
			dealer.getCards().add(Table.deckPack.getCard());
		}
		CardCountingHiLo.addRunningCount(dealer.getUpCard());
	}

	private void sortAllDealtCards() {
		player.getCardsBetMap().keySet().stream().forEach(setNumber -> {
			CardUtils.sortCardsAlphabetically(player.getCardsFromSet(setNumber));
		});
		CardUtils.sortCardsAlphabetically(dealer.getCards());
	}

}
