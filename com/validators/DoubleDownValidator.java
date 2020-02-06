package com.validators;

import java.util.List;

import com.cards.Card;
import com.utils.CardUtils;
import com.utils.Constants;

public class DoubleDownValidator {
	
	public boolean canDoubleDown(List<Card> cards, int qtyCardGroups) {
		boolean canDoubleDown = false;
		if(qtyCardGroups > 1 && !Constants.DOUBLE_DOWN_AFTER_SPLIT) {
			canDoubleDown = false;
		}
		else if (!Constants.CAN_ALWAYS_DOUBLE_DOWN) {
			if (Constants.DOUBLE_DOWN_HARD_TOTALS.contains(CardUtils.getSumOfCards(cards))) {
				canDoubleDown = true;
			}
		} else {
			canDoubleDown = true;
		}
		return canDoubleDown;
	}

}
