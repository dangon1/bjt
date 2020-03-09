package com.parties;

import static com.utils.Constants.DEALER_DRAW_UNTIL;

import java.util.List;

import com.cards.Card;
import com.strategies.CardCountingHiLo;
import com.ui.ConsoleManager;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Dealer extends CardsHolder {

	public List<Card> getCards() {
		return getCardsBetMap().get(0).getCardsGroup();
	}

	public Card getUpCard() {
		return getCards().get(0);
	}

	public void play() {
		while (getSumCards().get(0) < DEALER_DRAW_UNTIL) {
			// hit
			Card hitCard = Table.deckPack.getCard();
			ConsoleManager.showDealerHit(hitCard);
			getCards().add(hitCard);
			CardCountingHiLo.addRunningCount(hitCard);
		}
		ConsoleManager.showDealerHand(getCards());
	}

}
