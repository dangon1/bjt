package com.main;

import static com.main.TableActionExecutor.BLACKJACK_CODE;
import static com.main.TableActionExecutor.SPLIT_CODE;
import static com.main.TableActionExecutor.STOP_CODE;
import static com.main.TableActionExecutor.SURRENDER_CODE;
import static com.utils.Constants.MAX_NUMBER_OF_PLAYS;
import static com.utils.Constants.MINIMUM_BET;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cards.Card;
import com.cards.CardsBet;
import com.cards.Deck;
import com.cards.DeckPack;
import com.casino.Referee;
import com.parsers.TableSituationParser;
import com.parties.Dealer;
import com.parties.Player;
import com.statistics.CounterManager;
import com.ui.ConsoleManager;
import com.utils.CardUtils;
import com.utils.Constants;

public class GameEngine {
	private static final int NUMBER_OF_CARDS_PER_DECK = 52;
	public static DeckPack deckPack;
	private static Player player;
	private static Dealer dealer;
	private static TableActionExecutor executor = TableActionExecutor.builder().build();
	private CounterManager counter;
	
	public void setup() {
		counter = new CounterManager();
		deckPack = DeckPack.builder().build();
		player = Player.builder().build();
		dealer = Dealer.builder().build();
		player.getAI().getBs().setStrategies();

		createShoe(deckPack, Constants.NUMBER_OF_DECKS);
	}
	
	public static void createShoe(DeckPack deckPack, Integer numberOfDecks) {
		for (int i = 0; i < numberOfDecks; i++) {
			Deck d1 = new Deck();
			d1.createDeck();
			deckPack.getCards().addAll(d1.getCards());
		}
		
		List<Card> deckPackAfterCut = deckPack.getCards().subList(0, deckPack.getCards().size() - Constants.DECKS_CUT_OFF * NUMBER_OF_CARDS_PER_DECK);
		deckPack.setCards(deckPackAfterCut);
		
	}
	
	public void play() throws RuntimeException {
		while (counter.getNumberOfPlays() < MAX_NUMBER_OF_PLAYS) {
			ConsoleManager.showNewHand();
			player.getAI().getResultsCounter().addTotalHands();
			
			player.bet(MINIMUM_BET,0);
			
			distributeCards();
			sortAllDealtCards();

			Integer finishRound = 0;
			int i = 0;
			List<Card> playerCardsFromCurrentGroup = player.getCardsBetMap().get(i).getCardsGroup();

			while (i < player.getCardsBetMap().size()) {
				playerCardsFromCurrentGroup = player.getCardsBetMap().get(i).getCardsGroup();
				finishRound = choosePlay(finishRound, playerCardsFromCurrentGroup,i);
				if (finishRound == SPLIT_CODE) {
					i = 0;
				} else{
					i++;
				}
			}
			if(finishRound != SURRENDER_CODE && finishRound != BLACKJACK_CODE) {
				dealer.play();
			}
			ConsoleManager.showHands(player.getSumCards(), dealer.getSumCards());
			for (int j = 0; j < player.getCardsBetMap().size(); j++) {
				Referee.analyseResult(player, dealer,j);
			}
			counter.addNumberOfPlays();
			discardHands();
		}
		ConsoleManager.showResults(player);
	}

	/**
	 * -1 -> backjack, 0 -> continue playing, 1 -> finished laying, 5 -> decided to split, 6 -> busted
	 * @param cardsBetId 
	 */

	public Integer choosePlay(Integer finishRound, List<Card> playerCardsFromCurrentGroup, int cardsBetId) {

		while (player.hasMoney() && !Referee.busted(playerCardsFromCurrentGroup) && !Referee.busted(dealer.getCards())) {

			String tableSituation = TableSituationParser.parseTableSituation(playerCardsFromCurrentGroup,dealer.getUpCard());
			String playerPlay = player.getAI().decideBestPlay(playerCardsFromCurrentGroup, tableSituation);
			finishRound = executor.executeAction(player, playerPlay, playerCardsFromCurrentGroup, dealer.getCards(), deckPack, cardsBetId);
			
			if(finishRound == SPLIT_CODE || finishRound == SURRENDER_CODE || finishRound == STOP_CODE || finishRound == BLACKJACK_CODE){
				break;
			}
		}
		if(Referee.busted(playerCardsFromCurrentGroup) || player.getTotalCash() < 0){
			finishRound = STOP_CODE;
		}
		return finishRound;

	}

	private void discardHands() {

		player.setCardsBetMap(new HashMap<Integer, CardsBet>() {
			{
				put(0, CardsBet.builder().build());
			}
		});
		
		dealer.getCards().clear();

	}

	

	private void distributeCards() {

		for (int i = 0; i < 2; i++) {
			player.getCardsBetMap().keySet().stream().forEach(setNumber -> {
				player.getCardsFromSet(setNumber).add(deckPack.getCard());
			});
			dealer.getCards().add(deckPack.getCard());
		}
	}

	private void sortAllDealtCards() {
		player.getCardsBetMap().keySet().stream().forEach(setNumber -> {
			CardUtils.sortCardsAlphabetically(player.getCardsFromSet(setNumber));
		});
		CardUtils.sortCardsAlphabetically(dealer.getCards());
	}



}
