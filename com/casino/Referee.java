package com.casino;

import java.util.List;

import com.cards.Card;
import com.parties.Dealer;
import com.parties.Player;
import com.statistics.CounterManager;
import com.utils.CardUtils;
import com.utils.Constants;

public class Referee {
	
	public static Integer madeBlackjack(List<Card> cards) {
		return  CardUtils.getSumOfCards(cards) == 21 && cards.size() == 2 ? 1 : 0;

	}
	
	public static boolean busted(List<Card> cards) {
		return CardUtils.getSumOfCards(cards) > 21;

	}
	
	public static void analyseResult(Player player, Dealer dealer, int cardsBetId) {
		Integer numberOfLosses = 0;
		Integer numberOfDraws = 0;
		Integer numberOfSimpleWins = 0;
		Integer numberOfBlackjacks = 0;

		numberOfLosses += CounterManager.getNumberOfPlayerLosses(player.getCardsBetMap().get(cardsBetId).getCardsGroup(), dealer.getCards());
		numberOfDraws += CounterManager.getNumberOfDraws(player.getCardsBetMap().get(cardsBetId).getCardsGroup(), dealer.getCards());
		numberOfBlackjacks += madeBlackjack(player.getCardsBetMap().get(cardsBetId).getCardsGroup());
		if(numberOfBlackjacks == 0) {
			numberOfSimpleWins = CounterManager.getNumberOfPlayerWins(player.getCardsBetMap().get(cardsBetId).getCardsGroup(), dealer.getCards());
		}

		if (!Constants.PLAYER_BJ_BEATS_DEALER_BJ) {
			numberOfBlackjacks -= madeBlackjack(dealer.getCards());
			numberOfBlackjacks = numberOfBlackjacks < 0 ? 0 : numberOfBlackjacks;
		} else {
			numberOfDraws -= madeBlackjack(dealer.getCards());
		}
		
		if(numberOfBlackjacks > 0) {
			GameBank.payBlackjackToPlayer(player, cardsBetId);
			CounterManager.addVictory(player);
		}

		GameBank.applyResults(player, numberOfLosses, numberOfDraws, numberOfSimpleWins, cardsBetId);
	}

}
