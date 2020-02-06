package com.parties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cards.Card;
import com.cards.CardsBet;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class CardsHolder {

	@Builder.Default
	@Setter
	private Map<Integer, CardsBet> cardsBetMap = new HashMap<Integer, CardsBet>() {
		{
			put(0, CardsBet.builder().build());
		}
	};

	public List<Integer> getSumCards() {
		List<Integer> sumCardsPerSet = new ArrayList<>();
		Integer sum = 0;
		for (Integer setNumber : cardsBetMap.keySet()) {
			if(cardsBetMap.get(setNumber).getCardsGroup().stream().anyMatch(c -> c.getFace().equals("A"))) {
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
		for (Card card : cardsBetMap.get(setNumber).getCardsGroup()) {
			sum += Integer.parseInt(card.getFace());
		}
		return sum;
	}
	
	private Integer sumWithAces(Integer setNumber, Integer aceValue) {
		Integer sum = 0;
		for (Card card : cardsBetMap.get(setNumber).getCardsGroup()) {
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
