package com.parties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cards.Card;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CardsHolder {

	@Builder.Default
	private Map<Integer, List<Card>> cardGroups = new HashMap<Integer, List<Card>>() {
		{
			put(0, new ArrayList());
		}
	};

	public List<Integer> getSumCards() {
		List<Integer> sumCardsPerSet = new ArrayList<>();
		Integer sum = 0;
		for (Integer setNumber : cardGroups.keySet()) {
			if(cardGroups.get(setNumber).stream().anyMatch(c -> c.getFace().equals("A"))) {
				sum = sumWhenHaveAces(setNumber);
			} else {
				sum = simpleSum(setNumber);
			}
			
			sumCardsPerSet.add(0);
			sumCardsPerSet.set(setNumber, sum);
			
		}
		return sumCardsPerSet;
	}

	private Integer simpleSum(Integer setNumber) {
		Integer sum = 0;
		for (Card card : cardGroups.get(setNumber)) {
			sum += Integer.parseInt(card.getFace());
		}
		return sum;
	}
	
	private Integer sumWithAces(Integer setNumber, Integer aceValue) {
		Integer sum = 0;
		for (Card card : cardGroups.get(setNumber)) {
			if(card.getFace().equals("A")) {
				sum += aceValue;
			} else {
				sum += Integer.parseInt(card.getFace());
			}
		}
		return sum;
		
	}

	private Integer sumWhenHaveAces(Integer setNumber) {
		Integer sum = sumWithAces(setNumber,11);
		if(sum > 21) {
			sum = sumWithAces(setNumber,1);
		}
		return sum;
	}

}
