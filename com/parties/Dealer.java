package com.parties;

import java.util.List;

import com.cards.Card;
import com.main.Main;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import static com.utils.Constants.DEALER_LIMIT;

@SuperBuilder
@Getter
public class Dealer extends CardsHolder {

	public List<Card> getCards() {
		return getCardGroups().get(0);
	}

	public Card getUpCard() {
		return getCards().get(0);
	}

	public void play() {
		while (getSumCards().get(0) <= DEALER_LIMIT) {
			// hit
			Card hitCard = Main.deckPack.getCard();
			System.out.println("Dealer Hit:" + hitCard.getFace());
			getCards().add(hitCard);
		}
		System.out.println("Dealer Hand:" + getCards());
	}

}
