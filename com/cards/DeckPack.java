package com.cards;

import java.util.ArrayList;
import java.util.List;

import com.parties.Table;
import com.utils.Constants;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class DeckPack {
	
	@Builder.Default
	private List<Card> cards = new ArrayList<Card>();
	
	public Card getCard() {
		if(cards.isEmpty()) {
			Table.createShoe(this, Constants.NUMBER_OF_DECKS);
		}
		return cards.remove(0);
	}

}
