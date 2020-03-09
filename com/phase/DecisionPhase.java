package com.phase;

import static com.main.ActionExecutor.BLACKJACK;
import static com.main.ActionExecutor.SPLIT;
import static com.main.ActionExecutor.STOP;
import static com.main.ActionExecutor.SURRENDER;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.cards.CardsBet;
import com.casino.Referee;
import com.main.ActionSelector;
import com.parsers.TableSituationParser;
import com.parties.Dealer;
import com.parties.Player;
import com.parties.Table;
import com.ui.ConsoleManager;

public class DecisionPhase extends Phase {
	
	public DecisionPhase(Player player, Dealer dealer) {
		this.player = player;
		this.dealer = dealer;
	}

	private ActionSelector actionSelector = ActionSelector.builder().build();

	@Override
	public void executePhase() {
		//TODO
//		player.getCardsBetMap().clear();
//		List<Card> cards0 = new ArrayList<Card>();
//		cards0.add(Card.builder().face("A").build());
//		cards0.add(Card.builder().face("A").build());
//		player.getCardsBetMap().put(0, CardsBet.builder().bet(10).cardsGroup(cards0).build());
		
		Integer action = 0;
		int i = 0;
		List<Card> playerCardsFromCurrentGroup = player.getCardsBetMap().get(i).getCardsGroup();

		while (i < player.getCardsBetMap().size()) {
			playerCardsFromCurrentGroup = player.getCardsBetMap().get(i).getCardsGroup();
			action = chooseAction(playerCardsFromCurrentGroup,i);
			i = action == SPLIT ? 0 : ++i; 
		}
		if(action != SURRENDER && action != BLACKJACK) {
			dealer.play();
		}
		ConsoleManager.showHands(player.getSumCards(), dealer.getSumCards());
		if(action != SURRENDER) {
			for (int j = 0; j < player.getCardsBetMap().size(); j++) {
				Referee.analyseResult(player, dealer,j);
			}
		}
		
	}
	
	/**
	 * -1 -> backjack, 0 -> continue playing, 1 -> finished laying, 5 -> decided to split, 6 -> busted
	 * @param cardsBetId 
	 */

	private Integer chooseAction(List<Card> playerCardsFromCurrentGroup, int cardsBetId) {

		Integer action = 0;
		while (player.hasMoney() && !Referee.busted(playerCardsFromCurrentGroup) && !Referee.busted(dealer.getCards())) {

			String tableSituation = TableSituationParser.parseTableSituation(playerCardsFromCurrentGroup,dealer.getUpCard());
			String playerPlay = player.getAI().decideBestPlay(playerCardsFromCurrentGroup, tableSituation);
			action = actionSelector.selectAction(player, playerPlay, playerCardsFromCurrentGroup, dealer.getCards(), Table.deckPack, cardsBetId);
			
			if(action == SPLIT || action == SURRENDER || action == STOP || action == BLACKJACK){
				break;
			}
		}
		if(Referee.busted(playerCardsFromCurrentGroup) || player.getTotalCash() < 0){
			action = STOP;
		}
		return action;

	}

}
