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
	
	public static Integer getSumOfCards(List<Card> cards) {
		Integer sum =0;
		if(cards.stream().anyMatch(c -> c.getFace().equals("A"))) {
			sum = sumWhenHaveAces(cards);
		} else {
			sum = simpleSum(cards);
		}
		
		return sum;
	}
	
	private static Integer sumWhenHaveAces(List<Card> cards) {
		Integer sum = sumWithAces(cards,11);
		if(sum > 21) {
			sum = sumWithAces(cards,1);
		}
		return sum;
	}
	
	
	private static Integer simpleSum(List<Card> cards) {
		Integer sum = 0;
		for (Card card : cards) {
			sum += Integer.parseInt(card.getFace());
		}
		return sum;
	}
	
	private static Integer sumWithAces(List<Card> cards, Integer aceValue) {
		Integer sum = 0;
		for (Card card : cards) {
			if(card.getFace().equals("A")) {
				sum += aceValue;
			} else {
				sum += Integer.parseInt(card.getFace());
			}
		}
		return sum;
		
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
