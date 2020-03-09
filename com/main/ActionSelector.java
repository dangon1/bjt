package com.main;

import static com.main.ActionExecutor.BLACKJACK;
import static com.main.ActionExecutor.CONTINUE;

import java.util.List;

import com.cards.Card;
import com.cards.DeckPack;
import com.parties.Player;
import com.validators.DoubleDownValidator;
import com.validators.SurrenderValidator;

import lombok.Builder;

@Builder
public class ActionSelector {
	
	@Builder.Default
	private ActionExecutor executor = ActionExecutor.builder().build();
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
	public Integer selectAction(Player player, String play, List<Card> cards, List<Card> dealerCards,
			DeckPack deckPack, int cardsBetId) {

		Integer action = CONTINUE;
		switch (play) {
		case "Y":
			action = executor.split(player,cardsBetId);
			break;
		case "Y/N":
			action = executor.split(player,cardsBetId);
			break;
		case "N":
			action = executor.hit(cards);
			break;
		case "S":
			action = executor.stand();
			break;
		case "Ds":
			action = doubleDownValidator.canDoubleDown(cards,player.getCardsBetMap().size()) 
				? executor.doubleDown(cards, player, cardsBetId) 
				: executor.stand();
			break;
		case "D":
			action = doubleDownValidator.canDoubleDown(cards,player.getCardsBetMap().size()) 
				? executor.doubleDown(cards, player, cardsBetId) 
				: executor.hit(cards);
			break;
		case "H":
			action = executor.hit(cards);
			break;
		case "SUR":
			action = surrenderValidator.canSurrender(cards.size(),dealerCards.get(0))
				? executor.surrender(player,cardsBetId) 
				: executor.hit(cards);
			break;
		default:
			action = BLACKJACK;
			break;
		}
		return action;
	}

}
