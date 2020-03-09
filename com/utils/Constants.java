package com.utils;

import java.util.Arrays;
import java.util.List;

public class Constants {
	
	public static final int NUMBER_OF_CARDS_PER_DECK = 52;
	public static final Integer INITIAL_CASH = 3000;
	public static final Integer MINIMUM_BET = 3;
	public static final int MAXIMUM_BET = 100;
	public static final Integer MAX_NUMBER_OF_PLAYS = 10000;
	public static final Integer DEALER_DRAW_UNTIL = 17;
	public static final boolean CAN_ALWAYS_DOUBLE_DOWN = false;
	public static final Integer NUMBER_OF_DECKS = 6;
	
	
	public static final boolean DOUBLE_DOWN_AFTER_SPLIT = true;
	public static final Integer DECKS_CUT_OFF = 1;
	public static final Double BLACKJACK_PAYOUT = 3.0/2.0;
	public static final boolean PLAYER_BJ_BEATS_DEALER_BJ = true;
	public static final boolean STANDS_SOFT_17 = true;
	public static final boolean EARLY_SURRENDER = true;
	public static final boolean LATE_SURRENDER = true;
	public static final List<Integer> DOUBLE_DOWN_HARD_TOTALS = Arrays.asList(9,10,11);
	public static final boolean WONG_IN = true;
	
	public static final Integer SPREAD_BET_AGRESSION = 3;


}
