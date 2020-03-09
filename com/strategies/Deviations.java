package com.strategies;

import com.parties.Dealer;

public class Deviations {
	
	private static final String BUY_INSURANCE = "B";

	public String applyDeviations(String actualAction, Dealer dealer) {
		
		if(dealer.getUpCard().equals("A") && CardCountingHiLo.getTrueCount() > 3) {
			return BUY_INSURANCE;
		}
		return actualAction;
	}

}
