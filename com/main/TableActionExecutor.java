package com.main;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.cards.DeckPack;
import com.parties.Player;
import static com.utils.Constants.MINIMUM_BET;

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
	public Integer executeAction(String play, Player player, List<Card> dealerCards, DeckPack deckPack) {

		Integer finishRound = 0;
		switch (play) {
		case "Y":
			//TODO: SPLIT
			//finishRound = splitHit(player);
			finishRound = hit(player);
			break;
		case "Y/N":
			//TODO: SPLIT
			//finishRound = splitHit(player);
			finishRound = hit(player);
			break;
		case "S":
			finishRound = stand();
			break;
		case "Ds":
			finishRound = doubleDown(player);
			break;
		case "D":
			finishRound = doubleDown(player);
			break;
		case "H":
			finishRound = hit(player);
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

	private Integer hit(Player player) {
		for (Integer numberOfGroup : player.getCardGroups().keySet()) {
			hitOneGroup(player.getCardGroups().get(numberOfGroup));
		}
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

	public Integer doubleDown(Player player) {
		System.out.println("Decided to DOUBLE DOWN");
		player.bet(MINIMUM_BET);
		for (Integer numberOfGroup : player.getCardGroups().keySet()) {
			hitOneGroup(player.getCardGroups().get(numberOfGroup));
		}
		return 1;
	}

	public Integer splitHit(Player player) {
		player.setTotalCash(player.getTotalCash() - player.getBet());
		player.setBet(player.getBet()*2);
		removeCardFromOneGroupAndAddToOther(player);
		hit(player);
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
		//TODO: LAST SURRENDER
		System.out.println("Decided to HIT");
		return hit(player);
	}

}
