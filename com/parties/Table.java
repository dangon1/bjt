package com.parties;

import static com.utils.Constants.NUMBER_OF_CARDS_PER_DECK;

import java.util.List;

import com.cards.Card;
import com.cards.Deck;
import com.cards.DeckPack;
import com.strategies.CardCountingHiLo;
import com.utils.Constants;

import lombok.Getter;

@Getter
public class Table {
	
	public static DeckPack deckPack;
	private Player player;
	private Dealer dealer;
	
	public void setup() {
		
		Table.deckPack = DeckPack.builder().build();
		player = Player.builder().build();
		dealer = Dealer.builder().build();
		player.getAI().getBs().setStrategies();

		createShoe(deckPack, Constants.NUMBER_OF_DECKS);
	}
	
	public static void createShoe(DeckPack deckPack, Integer numberOfDecks) {
		
		for (int i = 0; i < numberOfDecks; i++) {
			Deck d1 = new Deck();
			d1.createDeck();
			deckPack.getCards().addAll(d1.getCards());
		}
		
		List<Card> deckPackAfterCut = deckPack.getCards().subList(0, deckPack.getCards().size() - Constants.DECKS_CUT_OFF * NUMBER_OF_CARDS_PER_DECK);
		deckPack.setCards(deckPackAfterCut);
		CardCountingHiLo.reset();
		
	}
	

}
