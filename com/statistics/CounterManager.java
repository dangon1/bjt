package com.statistics;

import java.util.List;

import com.cards.Card;
import com.casino.Referee;
import com.parties.Player;
import com.utils.CardUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounterManager {
	
	private Integer numberOfPlays = 0;
	
	public static void addVictory(Player player) {
		player.getAI().getResultsCounter().addVictory();
	}

	public void addNumberOfPlays() {
		setNumberOfPlays(numberOfPlays+1);
	}

	
	public static Integer getNumberOfPlayerLosses(List<Card> playerCards, List<Card> dealerCards) {
		Integer numberOfLosses = 0;
		if ((CardUtils.getSumOfCards(playerCards) > 21)
				|| !Referee.busted(dealerCards) && CardUtils.getSumOfCards(dealerCards) > CardUtils.getSumOfCards(playerCards)) {
			numberOfLosses++;
		}
		return numberOfLosses;
	}
	
	public static Integer getNumberOfPlayerWins(List<Card> playerCards, List<Card> dealerCards) {
		Integer numberOfWins = 0;
		if ((CardUtils.getSumOfCards(playerCards) <= 21)
				&& (Referee.busted(dealerCards) || CardUtils.getSumOfCards(playerCards) > CardUtils.getSumOfCards(dealerCards))) {
			numberOfWins++;
		}
		return numberOfWins;
	}
	

	public static Integer getNumberOfDraws(List<Card> cardsFromCurrentGroup, List<Card> dealerCard) {
		Integer numberOfDraws = 0;
		if (!Referee.busted(cardsFromCurrentGroup) && !Referee.busted(dealerCard)
				&& CardUtils.getSumOfCards(cardsFromCurrentGroup).equals(CardUtils.getSumOfCards(dealerCard))) {
			numberOfDraws++;
		}
		return numberOfDraws;

	}
}
