package com.main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cards.Deck;
import com.cards.DeckPack;
import com.parsers.TableSituationParser;
import com.parties.CardsHolder;
import com.parties.Dealer;
import com.parties.Player;
import com.utils.CardUtils;
import static com.utils.Constants.MINIMUM_BET;
import static com.utils.Constants.NUMBER_OF_PLAYS;;

public class Main {

	public static DeckPack deckPack;
	private static Player player;
	private static Dealer dealer;
	private static TableActionExecutor executor = TableActionExecutor.builder().build();

	public static void main(String[] args) {
		setup();
		play();
	}

	private static void play() throws RuntimeException {
		int numberPlays = 0;
		while (numberPlays < NUMBER_OF_PLAYS) {
			System.out.println("============NEW HAND===============");
			player.getAI().getResultsCounter().addTotalHands();
			Integer finishRound = null;
			player.getProfit();
			player.bet(MINIMUM_BET);
			distributeCards();
			sortAllDealtCards();
			while (player.getTotalCash() > 0 && !gotOver21(player) && !gotOver21(dealer)) {
				String tableSituation = TableSituationParser.parseTableSituation(player.getCardGroups(),
						dealer.getUpCard());

				String playerPlay = player.getAI().decideBestPlay(player, tableSituation);
				finishRound = executor.executeAction(playerPlay, player, dealer.getCards(), deckPack);
				if(finishRound.equals(1) || finishRound.equals(-1)) {
					if(player.getSumCards().stream().anyMatch(s -> s == 21)) {
						finishRound = -1;
					}
					break;
				}
			}
			if (finishRound == -1) {
				payBlackjackToPlayer();
				player.getAI().getResultsCounter().addVictory();
			} else {
				dealer.play();
				analyseResult();
			}
			discardHands();
			numberPlays++;
		}
		showResults();
	}

	private static void payBlackjackToPlayer() {
		
		player.setTotalCash(player.getTotalCash() + getNumberOfBlackjacks(player)*(player.getBet() + (int) Math.round(player.getBet() * 0.5)));
	}

	private static void analyseResult() {
		showHands();
		Integer numberOfLosses = 0;
		Integer numberOfDraws = 0;
		Integer numberOfWins = 0;
		numberOfLosses = getNumberOfPlayerLosses(player, dealer);
		numberOfDraws = getNumberOfDraws(player, dealer);
		numberOfWins = player.getCardGroups().size() - numberOfLosses - numberOfDraws;
		player.setTotalCash(player.getTotalCash() + (numberOfWins * player.getBet()));
		player.setBet(player.getBet() - (numberOfLosses * player.getBet()));
		
		player.getAI().getResultsCounter().setTotalVictories(player.getAI().getResultsCounter().getTotalVictories() + numberOfWins);
		player.getAI().getResultsCounter().setTotalDefeats(player.getAI().getResultsCounter().getTotalDefeats() + numberOfLosses);
		player.getAI().getResultsCounter().setTotalDraws(player.getAI().getResultsCounter().getTotalDraws() + numberOfDraws);
	}

	private static Integer getNumberOfDraws(Player player, Dealer dealer) {
		Integer numberOfDraws = 0;
		for (int i = 0; i < player.getCardGroups().size(); i++) {
			if (!gotOver21(player) && !gotOver21(dealer) && player.getSumCards().get(i).equals(dealer.getSumCards().get(0))) {
				numberOfDraws++;
			}
		}
		return numberOfDraws;
		
	}

	private static void showHands() {
		System.out.println("Sum Player:" + player.getSumCards());
		System.out.println("Sum Dealer:" + dealer.getSumCards());
		
	}

	private static boolean gotOver21(CardsHolder cardsHolder) {
		return cardsHolder.getSumCards().stream().anyMatch(s -> s > 21);

	}
	
	private static Integer getNumberOfBlackjacks(CardsHolder cardsHolder) {
		return cardsHolder.getSumCards().stream().filter(s -> s == 21).collect(Collectors.toList()).size();

	}

	public static Integer getNumberOfPlayerLosses(Player player, Dealer dealer) {
		Integer numberOfLosses = 0;
		for (int i = 0; i < player.getCardGroups().size(); i++) {
			if ((player.getSumCards().get(i) > 21) 
					|| !gotOver21(dealer) && dealer.getSumCards().get(0) > player.getSumCards().get(i)) {
				numberOfLosses++;
			}
		}
		return numberOfLosses;
	}

	private static void showResults() {
		System.out.println("============RESULTS============");
		System.out.println("Total blackjacks:" + player.getAI().getResultsCounter().getTotalBlackjacks());
		System.out.println("Total hands:" + player.getAI().getResultsCounter().getTotalHands());
		System.out.println("Blackjacks/Total: " + player.getAI().getResultsCounter().getPercentageBlackjacks() + "%");
		System.out.println("Total cash:" + player.getTotalCash());
		System.out.println("Victories:" + player.getAI().getResultsCounter().getPercentageVictory()+ "%");
		System.out.println("Draws: " + player.getAI().getResultsCounter().getPercentageDraw() + "%");
		System.out.println("Defeats: " + player.getAI().getResultsCounter().getPercentageDefeats() + "%");

	}

	private static void setup() {
		deckPack = DeckPack.builder().build();
		player = Player.builder().build();
		dealer = Dealer.builder().build();
		player.getAI().getBs().setStrategies();

		setDeckPack(1);
	}

	private static void discardHands() {

		player.getCardGroups().keySet().stream().forEach(setNumber -> {
			player.getCardGroups().get(setNumber).clear();
		});
		dealer.getCards().clear();

	}

	private static void setDeckPack(Integer numberOfDecks) {
		for (int i = 0; i < numberOfDecks; i++) {
			Deck d1 = new Deck();
			d1.createDeck();
			deckPack.getCards().addAll(d1.getCards());
		}
	}

	private static void distributeCards() {

		for (int i = 0; i < 2; i++) {
			player.getCardGroups().keySet().stream().forEach(setNumber -> {
				player.getCardsFromSet(setNumber).add(deckPack.getCard());
			});
			dealer.getCards().add(deckPack.getCard());
		}
	}

	private static void sortAllDealtCards() {
		player.getCardGroups().keySet().stream().forEach(setNumber -> {
			CardUtils.sortCardsAlphabetically(player.getCardsFromSet(setNumber));
		});
		CardUtils.sortCardsAlphabetically(dealer.getCards());
	}

}
