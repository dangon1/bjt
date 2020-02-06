package com.main;

import static com.utils.Constants.MINIMUM_BET;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.cards.CardsBet;
import com.cards.DeckPack;
import com.parties.Player;
import com.ui.ConsoleManager;
import com.utils.CardUtils;
import com.utils.Constants;
import com.validators.DoubleDownValidator;
import com.validators.SurrenderValidator;

import lombok.Builder;

@Builder
public class TableActionExecutor {

	public static final int BLACKJACK_CODE = -1;
	public static final int CONTINUE_CODE = 0;
	public static final int STOP_CODE = 1;
	public static final int SURRENDER_CODE = 2;
	public static final int SPLIT_CODE = 5;
	
	@Builder.Default
	private DoubleDownValidator doubleDownValidator = new DoubleDownValidator();
	@Builder.Default
	private SurrenderValidator surrenderValidator = new SurrenderValidator();
	/**
	 * 
	 * @param play
	 * @param player
	 * @param dealerCards
	 * @param deckPack
	 * @param cardsBetId 
	 * @return if true, finish the round and go to analysis, else continue to play
	 */
	public Integer executeAction(Player player, String play, List<Card> cards, List<Card> dealerCards,
			DeckPack deckPack, int cardsBetId) {

		Integer finishRound = CONTINUE_CODE;
		switch (play) {
		case "Y":
			finishRound = split(player,cardsBetId);
			break;
		case "Y/N":
			finishRound = split(player,cardsBetId);
			break;
		case "N":
			finishRound = hit(cards);
			break;
		case "S":
			finishRound = stand();
			break;
		case "Ds":
			if (doubleDownValidator.canDoubleDown(cards,player.getCardsBetMap().size())) {
				finishRound = doubleDown(cards, player, cardsBetId);
			} else {
				finishRound = stand();
			}

			break;
		case "D":
			if (doubleDownValidator.canDoubleDown(cards,player.getCardsBetMap().size())) {
				finishRound = doubleDown(cards, player,cardsBetId);
			} else {
				finishRound = hit(cards);
			}
			break;
		case "H":
			finishRound = hit(cards);
			break;
		case "SUR":
			if(surrenderValidator.canSurrender(cards.size(),dealerCards.get(0))) {
				finishRound = surrender(player,cardsBetId);
			}
			 else {
				hit(cards);
			}
			break;
		default:
			finishRound = BLACKJACK_CODE;
			break;
		}
		return finishRound;
	}

	private Integer hit(List<Card> cards) {
		hitOneGroup(cards);
		return CONTINUE_CODE;
	}

	public void hitOneGroup(List<Card> cards) {
		Card hitCard = GameEngine.deckPack.getCard();
		ConsoleManager.showPlayerHit(hitCard);
		cards.add(hitCard);
	}

	public Integer stand() {
		
		return STOP_CODE;
	}

	public Integer doubleDown(List<Card> cards, Player player, int cardsBetId) {
		ConsoleManager.showAction("DOUBLE DOWN");
		player.bet(MINIMUM_BET,cardsBetId);
		hitOneGroup(cards);
		return STOP_CODE;
	}

	public Integer split(Player player, int cardsBetId) {
		ConsoleManager.showAction("SPLIT");
		player.setTotalCash(player.getTotalCash() - player.getCardsBetMap().get(cardsBetId).getBet());
		ConsoleManager.showBet(player.getCardsBetMap().get(cardsBetId).getBet());
		player.getCardsBetMap().get(cardsBetId).setBet(player.getCardsBetMap().get(cardsBetId).getBet() * 2);
		removeCardFromOneGroupAndAddToOther(player);
		return SPLIT_CODE;
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
		player.getAI().getResultsCounter().addProfit(-player.getCardsBetMap().get(cardsBetId).getBet()/2);
		player.getCardsBetMap().get(cardsBetId).setBet(0);
		return SURRENDER_CODE;
	}

}
