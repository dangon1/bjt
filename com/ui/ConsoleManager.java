package com.ui;

import java.util.List;

import com.cards.Card;
import com.parties.Player;
import com.utils.Constants;

public class ConsoleManager {
	
	private boolean showLog = true;
	
	public static void showNewHand() {
		System.out.println("============NEW HAND===============");
	}
	
	public static void showHands(List<Integer> playerSumPerSet, List<Integer> dealerSum) {
		System.out.println("Sum Player:" + playerSumPerSet);
		System.out.println("Sum Dealer:" + dealerSum);
		
	}

	public static void showResults(Player player) {
		System.out.println("============RESULTS============");
		System.out.println("Total blackjacks:" + player.getAI().getResultsCounter().getTotalBlackjacks());
		System.out.println("Total hands:" + player.getAI().getResultsCounter().getTotalHands());
		System.out.println("Blackjacks/Total: " + player.getAI().getResultsCounter().getPercentageBlackjacks() + "%");
		System.out.println("Total cash:" + player.getTotalCash());
		System.out.println("Victories:" + player.getAI().getResultsCounter().getPercentageVictory()+ "%");
		System.out.println("Draws: " + player.getAI().getResultsCounter().getPercentageDraw() + "%");
		System.out.println("Defeats: " + player.getAI().getResultsCounter().getPercentageDefeats() + "%");
		System.out.println("Total Profit: " + player.getAI().getResultsCounter().getTotalProfit());
		System.out.println("% Profit: " + player.getAI().getResultsCounter().getTotalProfit() / Double.parseDouble(Constants.INITIAL_CASH.toString()) * 100 + "%");

	}
	
	public static void showWon(Integer profit) {
		System.out.println("WON: " + profit);
	}
	
	public static void showLoss(Integer loss) {
		System.out.println("LOSS: " + loss);
	}
	
	public static void showPush() {
		System.out.println("PUSH! DRAW!");
	}
	
	public static void showBlackjack() {
		System.out.println("BLACKJACK!");
	}
	
	public static void showPlayerHit(Card card) {
		System.out.println("Player hit:" + card.getFace());
	}
	
	public static void showAction(String action) {
		System.out.println("Decided to " + action.toUpperCase());
	}
	
	public static void showBet(Integer bet) {
		System.out.println("Bet + " + bet);
	}
	
	public static void showTableSituation(String fullTableSituation) {
		System.out.println("HAND -->" + fullTableSituation);
	}
	
	public static void showDealerHand(List<Card> cards ) {
		System.out.println("Dealer Hand:" + cards);
	}

	public static void showDealerHit(Card hitCard) {
		System.out.println("Dealer Hit:" + hitCard.getFace());
		
	}
	public static void showTotalCash(Integer totalCash) {
		System.out.println("TOTAL CASH = " + totalCash);
		
	}
	

}
