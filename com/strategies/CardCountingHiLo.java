package com.strategies;

import java.util.Map;

import com.cards.Card;
import com.parties.Table;
import com.utils.Constants;

public class CardCountingHiLo {
	
	public static Integer runningCount = 0;
	
	public static Map<String, Integer> hiLoSystem = 
		Map.of(
			"2", 1 , 
			"3" ,1, 
			"4" ,1, 
			"5" ,1, 
			"6" ,1, 
			"7" ,0, 
			"8" ,0, 
			"9" ,0, 
			"10" ,-1, 
			"A" ,-1
		);
	
	public static void addRunningCount(Card hitCard) {
		runningCount += CardCountingHiLo.hiLoSystem.get(hitCard.getFace()).intValue();
	}
	
	public static Integer getTrueCount() {
		int remainingDecksToBeDealt = (Table.deckPack.getCards().size() + (Constants.NUMBER_OF_CARDS_PER_DECK * Constants.DECKS_CUT_OFF)) / Constants.NUMBER_OF_CARDS_PER_DECK;
		if(remainingDecksToBeDealt > 0) {
			return Math.floorDiv(runningCount, remainingDecksToBeDealt);
		} else {
			return runningCount;
		}
	}

	public static void reset() {
		runningCount = 0;
		
	}
	
	

}
