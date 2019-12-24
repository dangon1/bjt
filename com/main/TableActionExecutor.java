package com.main;

import static com.utils.Constants.MINIMUM_BET;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.cards.DeckPack;
import com.parties.Player;

import lombok.Builder;

@Builder
public class TableActionExecutor {

	/**
	 * 
	 * @param play
	 * @param player
	 * @param dealerCards
	 * @param deckPack
	 * @return if true, finish the round and go to analysis, else continue to play
	 */
	public Integer executeAction(Player player, String play, List<Card> cards, List<Card> dealerCards,
			DeckPack deckPack) {

		Integer finishRound = 0;
		switch (play) {
		case "Y":
			// TODO: SPLIT
			// finishRound = splitHit(player);
			finishRound = hit(cards);
			break;
		case "Y/N":
			// TODO: SPLIT
			// finishRound = splitHit(player);
			finishRound = hit(cards);
			break;
		case "N":
			// TODO: SPLIT
			// finishRound = splitHit(player);
			finishRound = hit(cards);
			break;
		case "S":
			finishRound = stand();
			break;
		case "Ds":
			if (cards.size() <= 2) {
				finishRound = doubleDown(cards, player);
			} else {
				finishRound = stand();
			}

			break;
		case "D":
			if (cards.size() <= 2) {
				finishRound = doubleDown(cards, player);
			} else {
				finishRound = hit(cards);
			}
			break;
		case "H":
			finishRound = hit(cards);
			break;
		case "SUR":
			finishRound = surrender(player);
			break;
		default:
			System.out.println("BLACKJACK! NO DECISION TAKEN!");
			finishRound = -1;
			break;
		}
		return finishRound;
	}

	private Integer hit(List<Card> cards) {
		hitOneGroup(cards);
		return 0;
	}

	public void hitOneGroup(List<Card> cards) {
		Card hitCard = Main.deckPack.getCard();
		System.out.println("Player hit:" + hitCard.getFace());
		cards.add(hitCard);
	}

	public Integer stand() {
		System.out.println("Decided to STAND");
		return 1;
	}

	public Integer doubleDown(List<Card> cards, Player player) {
		System.out.println("Decided to DOUBLE DOWN");
		player.bet(MINIMUM_BET);
		hitOneGroup(cards);
		return 1;
	}

	public Integer splitHit(Player player) {
		System.out.println("Decided to SPLIT");
		player.setTotalCash(player.getTotalCash() - player.getBet());
		System.out.println("Bet + " + player.getBet());
		player.setBet(player.getBet() * 2);
		removeCardFromOneGroupAndAddToOther(player);
		return 0;
	}

	private void removeCardFromOneGroupAndAddToOther(Player player) {
		player.getCardGroups().put(player.getCardGroups().size(), new ArrayList<Card>() {
			{
				add(player.removeOneCardFromLastCardGroup());
			}
		});
	}

	public Integer surrender(Player player) {
		// TODO: LAST SURRENDER
		System.out.println("Decided to SURRENDER");
		player.setTotalCash(player.getTotalCash() + player.getBet()/2);
		System.out.println("LOSS:" + player.getBet()/2);
		player.getAI().getResultsCounter().addProfit(-player.getBet()/2);
		player.setBet(0);
		return 1;
	}

}
