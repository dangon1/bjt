package com.main;

import static com.utils.Constants.MINIMUM_BET;
import static com.utils.Constants.NUMBER_OF_PLAYS;

import java.util.List;

import com.cards.Card;
import com.cards.Deck;
import com.cards.DeckPack;
import com.parsers.TableSituationParser;
import com.parties.Dealer;
import com.parties.Player;
import com.utils.CardUtils;
import com.utils.Constants;;

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
			player.getProfit();
			player.bet(MINIMUM_BET);
			distributeCards();
			sortAllDealtCards();
			
			for (Integer groupCardsNumber : player.getCardGroups().keySet()) {
				Integer finishRound = null;
				List<Card> playerCardsFromCurrentGroup = player.getCardGroups().get(groupCardsNumber);
				while (player.getTotalCash() > 0 && !gotOver21(playerCardsFromCurrentGroup) && !gotOver21(dealer.getCards())) {

					String tableSituation = TableSituationParser.parseTableSituation(playerCardsFromCurrentGroup,
							dealer.getUpCard());

					String playerPlay = player.getAI().decideBestPlay(playerCardsFromCurrentGroup, tableSituation);
					finishRound = executor.executeAction(player, playerPlay, playerCardsFromCurrentGroup, dealer.getCards(),
							deckPack);
					if (finishRound.equals(1) || finishRound.equals(-1)) {
						if (player.getSumCards().stream().anyMatch(s -> s == 21)) {
							finishRound = -1;
						}
						break;
					}
				}
				
				if (finishRound == -1) {
					showHands();
					payBlackjackToPlayer(playerCardsFromCurrentGroup);
					player.getAI().getResultsCounter().addVictory();
					break;
				} else {
					dealer.play();
					showHands();
					analyseResult(playerCardsFromCurrentGroup);
					break;
				}
			}
			

			discardHands();
			numberPlays++;
		}
		showResults();
	}

	private static void payBlackjackToPlayer(List<Card> playerCardsFromCurrentGroup) {
		
		int profit = madeBlackjack(playerCardsFromCurrentGroup)*(player.getBet() + (int) Math.round(player.getBet() * 0.5));
		System.out.println("WON: " + profit);
		player.getAI().getResultsCounter().addProfit(profit);
		player.setTotalCash(player.getTotalCash() + profit);
	}

	private static void analyseResult(List<Card> cardsFromCurrentGroup) {
		Integer numberOfLosses = 0;
		Integer numberOfDraws = 0;
		Integer numberOfWins = 0;
		numberOfLosses = getNumberOfPlayerLosses(cardsFromCurrentGroup, dealer.getCards());
		numberOfDraws = getNumberOfDraws(cardsFromCurrentGroup, dealer.getCards());
		numberOfWins = player.getCardGroups().size() - numberOfLosses - numberOfDraws;
		
		int profit = numberOfWins * player.getBet();
		player.setTotalCash(player.getTotalCash() + profit);
		int loss = numberOfLosses * player.getBet();
		player.setBet(player.getBet() - loss);
		
		if(profit>0) {
			player.getAI().getResultsCounter().addProfit(profit);
			System.out.println("WON: " + profit);
		}
		if(loss>0) {
			System.out.println("LOSS: " + loss);
			player.getAI().getResultsCounter().addProfit(-loss);
		} if(numberOfDraws > 0) {
			System.out.println("DRAW!");
		}
		
		player.getAI().getResultsCounter().setTotalVictories(player.getAI().getResultsCounter().getTotalVictories() + numberOfWins);
		player.getAI().getResultsCounter().setTotalDefeats(player.getAI().getResultsCounter().getTotalDefeats() + numberOfLosses);
		player.getAI().getResultsCounter().setTotalDraws(player.getAI().getResultsCounter().getTotalDraws() + numberOfDraws);
	}

	private static Integer getNumberOfDraws(List<Card> cardsFromCurrentGroup, List<Card> dealerCard) {
		Integer numberOfDraws = 0;
		if (!gotOver21(cardsFromCurrentGroup) && !gotOver21(dealerCard)
				&& CardUtils.getSumOfCards(cardsFromCurrentGroup).equals(CardUtils.getSumOfCards(dealerCard))) {
			numberOfDraws++;
		}
		return numberOfDraws;

	}

	private static void showHands() {
		System.out.println("Sum Player:" + player.getSumCards());
		System.out.println("Sum Dealer:" + dealer.getSumCards());
		
	}

	private static boolean gotOver21(List<Card> cards) {
		return CardUtils.getSumOfCards(cards) > 21;

	}
	
	private static Integer madeBlackjack(List<Card> cards) {
		return  CardUtils.getSumOfCards(cards) == 21 ? 1 : 0;

	}

	public static Integer getNumberOfPlayerLosses(List<Card> playerCards, List<Card> dealerCards) {
		Integer numberOfLosses = 0;
		if ((CardUtils.getSumOfCards(playerCards) > 21)
				|| !gotOver21(dealerCards) && CardUtils.getSumOfCards(dealerCards) > CardUtils.getSumOfCards(playerCards)) {
			numberOfLosses++;
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
		System.out.println("Total Profit: " + player.getAI().getResultsCounter().getTotalProfit());

	}

	private static void setup() {
		deckPack = DeckPack.builder().build();
		player = Player.builder().build();
		dealer = Dealer.builder().build();
		player.getAI().getBs().setStrategies();

		setDeckPack(Constants.NUMBER_OF_DECKS);
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
