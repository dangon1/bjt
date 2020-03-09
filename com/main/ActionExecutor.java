package com.main;

import static com.utils.Constants.MINIMUM_BET;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.cards.CardsBet;
import com.parties.Player;
import com.parties.Table;
import com.strategies.CardCountingHiLo;
import com.ui.ConsoleManager;

import lombok.Builder;

@Builder
public class ActionExecutor {

	public static final int BLACKJACK = -1;
	public static final int CONTINUE = 0;
	public static final int STOP = 1;
	public static final int SURRENDER = 2;
	public static final int SPLIT = 5;
	
	public Integer hit(List<Card> cards) {
		hitOneGroup(cards);
		return CONTINUE;
	}

	public void hitOneGroup(List<Card> cards) {
		Card hitCard = Table.deckPack.getCard();
		ConsoleManager.showPlayerHit(hitCard);
		cards.add(hitCard);
		CardCountingHiLo.addRunningCount(hitCard);
	}

	public Integer stand() {
		ConsoleManager.showAction("STAND");
		return STOP;
	}

	public Integer doubleDown(List<Card> cards, Player player, int cardsBetId) {
		ConsoleManager.showAction("DOUBLE DOWN");
		player.bet(player.getCardsBetMap().get(0).getBet(),cardsBetId);
		hitOneGroup(cards);
		return STOP;
	}

	public Integer split(Player player, int cardsBetId) {
		ConsoleManager.showAction("SPLIT");
		removeCardFromOneGroupAndAddToOther(player);
		player.setTotalCash(player.getTotalCash() - player.getCardsBetMap().get(cardsBetId).getBet());
		ConsoleManager.showBet(player.getCardsBetMap().get(cardsBetId).getBet());
		player.getCardsBetMap().get(cardsBetId+1).setBet(player.getCardsBetMap().get(cardsBetId).getBet());
		return SPLIT;
	}

	private void removeCardFromOneGroupAndAddToOther(Player player) {
		player.getCardsBetMap().put(player.getCardsBetMap().size(), CardsBet.builder()
				.cardsGroup(new ArrayList<Card>() {{
					add(player.removeOneCardFromCardGroupWithTwoCards());
				}})
				.build());
	}

	public Integer surrender(Player player, int cardsBetId) {
		ConsoleManager.showAction("SURRENDER");
		player.setTotalCash(player.getTotalCash() + player.getCardsBetMap().get(cardsBetId).getBet()/2);
		ConsoleManager.showLoss(player.getCardsBetMap().get(cardsBetId).getBet()/2);
		player.getCardsBetMap().get(cardsBetId).setBet(0);
		return SURRENDER;
	}

}
