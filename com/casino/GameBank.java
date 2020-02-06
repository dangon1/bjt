package com.casino;

import static com.utils.Constants.BLACKJACK_PAYOUT;

import java.util.List;

import com.cards.Card;
import com.parties.Player;
import com.ui.ConsoleManager;

public class GameBank {
	
	public static void payBlackjackToPlayer(Player player, Integer cardsBetId) {
		
		int winIncludingBet = ((int) Math.round(player.getCardsBetMap().get(cardsBetId).getBet() * BLACKJACK_PAYOUT)) + player.getCardsBetMap().get(cardsBetId).getBet();
		ConsoleManager.showWon(winIncludingBet);
		player.getAI().getResultsCounter().addProfit(winIncludingBet);
		player.setTotalCash(player.getTotalCash() + winIncludingBet);
	}
	
	public static void applyResults(Player player, Integer numberOfLosses, Integer numberOfDraws, Integer numberOfWins, Integer cardsBetId) {
		int winIncludingBet = 0;
		if (numberOfWins > 0) {
			winIncludingBet = player.getCardsBetMap().get(cardsBetId).getBet() * 2;
		} else if(numberOfDraws > 0) {
			winIncludingBet = player.getCardsBetMap().get(cardsBetId).getBet();
		}
		
		player.setTotalCash(player.getTotalCash() + winIncludingBet);
		//todo when split, if 1 lose, both are loosing
		int loss = 0;
		if (numberOfLosses > 0) {
			loss = player.getCardsBetMap().get(cardsBetId).getBet();
			player.getCardsBetMap().get(cardsBetId).setBet(player.getCardsBetMap().get(cardsBetId).getBet() - loss);
		}

		if (winIncludingBet>0) {
			int profit = winIncludingBet - player.getCardsBetMap().get(cardsBetId).getBet();
			player.getAI().getResultsCounter().addProfit(profit);
			ConsoleManager.showWon(winIncludingBet);
		}
		if(loss>0) {
			ConsoleManager.showLoss(loss);
			player.getAI().getResultsCounter().addProfit(-loss);
		} if(numberOfDraws > 0) {
			ConsoleManager.showPush();
		}
		
		player.getAI().getResultsCounter().setTotalVictories(player.getAI().getResultsCounter().getTotalVictories() + numberOfWins);
		player.getAI().getResultsCounter().setTotalDefeats(player.getAI().getResultsCounter().getTotalDefeats() + numberOfLosses);
		player.getAI().getResultsCounter().setTotalDraws(player.getAI().getResultsCounter().getTotalDraws() + numberOfDraws);
	}

}
