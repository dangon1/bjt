package com.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.cards.Card;

public class CardUtils {

	public static List<String> getCardsFaces() {
		return Arrays.asList(new String[] { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" });
	}

	public static List<String> getCardsSuits() {
		return Arrays.asList(new String[] { "Diammonds", "Spades", "Hearts", "Clubs" });
	}

	public static void sortCardsAlphabetically(List<Card> cards) {

		cards.sort(Comparator.comparing(Card::getFace));
		Collections.reverse(cards.stream().map(Card::getFace).collect(Collectors.toList()));
	}
	
	public static void replaceJQKFor10(List<Card> cards) {
		for (Card card : cards) {
			
			if(card.getFace().equals("J")) {
				card.setFace("10");
			}
			
			else if(card.getFace().equals("Q")) {
				card.setFace("10");
			}
			
			else if(card.getFace().equals("K")) {
				card.setFace("10");
			}
		}
	}

}
