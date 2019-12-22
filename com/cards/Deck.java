package com.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.utils.CardUtils;

import lombok.Getter;

@Getter
public class Deck {

	List<Card> cards = new ArrayList<Card>();

	public void createDeck() {

		List<String> cardsFaces = CardUtils.getCardsFaces();
		List<String> cardsSuits = CardUtils.getCardsSuits();

		for (String suit : cardsSuits) {
			for (String face : cardsFaces) {
				cards.add(Card.builder().face(face).suit(suit).build());
			}
		}
		CardUtils.replaceJQKFor10(cards);
		shuffle();

	}
	
	

	public static List<Card> shuffle(List<Card> deck) {
		Collections.shuffle(deck);
		return deck;
	}
	
	public void shuffle() {
		Collections.shuffle(cards);
	}

}
