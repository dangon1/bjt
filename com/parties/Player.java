package com.parties;

import static com.utils.Constants.INITIAL_CASH;

import java.util.List;

import com.cards.Card;
import com.cards.CardsBet;
import com.ui.ConsoleManager;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class Player extends CardsHolder {

	@Builder.Default
	private AIPlayer AI = AIPlayer.builder().build();
	@Setter
	@Builder.Default
	private Integer totalCash = INITIAL_CASH;

	public List<Card> getCardsFromSet(Integer setNumber) {
		return getCardsBetMap().get(setNumber).getCardsGroup();
	}
	
	public Card removeOneCardFromCardGroupWithTwoCards() {
		for (CardsBet cardGroup : getCardsBetMap().values()) {
			if (cardGroup.getCardsGroup().size() > 1 && cardGroup.getCardsGroup().get(0).getFace().equals(cardGroup.getCardsGroup().get(1).getFace())) {
				return cardGroup.getCardsGroup().remove(0);
			}
		}
		return null;

	}

	public void bet(Integer betAmount, Integer cardsGroupId) throws RuntimeException {
		totalCash -= betAmount;
		getCardsBetMap().get(cardsGroupId).setBet(getCardsBetMap().get(cardsGroupId).getBet() + betAmount);
		if (totalCash <= 0) {
			throw new RuntimeException("LOST ALL MONEY, END OF GAME!");
		}
		ConsoleManager.showBet(betAmount);
		ConsoleManager.showTotalCash(totalCash);
	}


	public boolean hasMoney() {
		return totalCash > 0;
	}

}
