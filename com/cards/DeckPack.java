package com.cards;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DeckPack {
	
	@Builder.Default
	private List<Card> cards = new ArrayList<Card>();
	
	public Card getCard() {
		if(cards.isEmpty()) {
			Deck d1 = new Deck();
			d1.createDeck();
			this.getCards().addAll(d1.getCards());
		}
		return cards.remove(0);
	}

}
