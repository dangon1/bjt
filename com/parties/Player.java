package com.parties;

import static com.utils.Constants.INITIAL_CASH;

import java.util.List;

import com.cards.Card;

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
	@Setter
	@Builder.Default
	private Integer bet = 0;

	public List<Card> getCardsFromSet(Integer setNumber) {
		return getCardGroups().get(setNumber);
	}

	public Card removeOneCardFromLastCardGroup() {
		return getCardGroups().get(getCardGroups().size() - 1).remove(0);
	}

	public void bet(Integer betAmount) throws RuntimeException {
		totalCash -= betAmount;
		bet += betAmount;
		if(totalCash<=0) {
			throw new RuntimeException("LOST ALL MONEY, END OF GAME!");
		}
		System.out.println("BET + " + betAmount);
		System.out.println("TOTAL CASH = " + totalCash);
	}

	public void getProfit() {
		setTotalCash(totalCash + bet);
		bet =0;
	}

}
