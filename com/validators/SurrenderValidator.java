package com.validators;

import com.cards.Card;
import com.utils.Constants;

public class SurrenderValidator {
	
	public boolean canSurrender(Integer qtyPlayerCards, Card dealerUpCard) {
		boolean canSurrender = false;
		if (qtyPlayerCards <= 2) {
			if (Constants.EARLY_SURRENDER) {
				canSurrender = true;
			} else if (Constants.LATE_SURRENDER) {
				if (dealerUpCard.getFace().equals("A") || dealerUpCard.getFace().equals("10")) {
					canSurrender = false;
				} else {
					canSurrender = true;
				}
			} else {
				canSurrender = false;
			}
		}
		return canSurrender;
	}

}
