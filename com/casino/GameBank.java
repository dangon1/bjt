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
		player.setTotalCash(player.getTotalCash() + winIncludingBet);
	}
	
	public static void applyResults(Player player, Integer numberOfLosses, Integer numberOfDraws, Integer numberOfWins, Integer cardsBetId) {
		
		System.out.println("TESTDAN" + numberOfLosses + " " + numberOfDraws +" "+ numberOfWins +" "+ cardsBetId);
		int winIncludingBet = 0;
		if (numberOfWins > 0) {
			winIncludingBet = player.getCardsBetMap().get(cardsBetId).getBet() * 2;
		} else if (numberOfDraws > 0) {
			winIncludingBet = player.getCardsBetMap().get(cardsBetId).getBet();
			ConsoleManager.showPush();
		}
		
		if (winIncludingBet > 0) {
			int profit = winIncludingBet - player.getCardsBetMap().get(cardsBetId).getBet();
			ConsoleManager.showWon(winIncludingBet);
		}

		player.setTotalCash(player.getTotalCash() + winIncludingBet);
		
		// todo when split, if 1 lose, both are loosing
		int loss = 0;
		if (numberOfLosses > 0) {
			loss = player.getCardsBetMap().get(cardsBetId).getBet();
			ConsoleManager.showLoss(loss);
		}
		
		player.getAI().getResultsCounter().setTotalVictories(player.getAI().getResultsCounter().getTotalVictories() + numberOfWins);
		player.getAI().getResultsCounter().setTotalDefeats(player.getAI().getResultsCounter().getTotalDefeats() + numberOfLosses);
		player.getAI().getResultsCounter().setTotalDraws(player.getAI().getResultsCounter().getTotalDraws() + numberOfDraws);
		
		player.getCardsBetMap().get(cardsBetId).setBet(0);
	}

}
