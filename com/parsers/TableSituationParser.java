package com.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cards.Card;

public class TableSituationParser {

	public static final String CARDS_SEPARATOR = ",";
	public static final String SET_SEPARATOR = "/";
	public static final String PLAYER_SEPARATOR = ";";

	public static String getDealerUpCard(String fullTableSituation) {
		Pattern p = Pattern.compile("\\;(\\d*)");
		Matcher m = p.matcher(fullTableSituation);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}

	public static String parseTableSituation(Map<Integer, List<Card>> cardsSetPlayer, Card dealerUpCard) {
		StringBuilder tableSituation = new StringBuilder();

		cardsSetPlayer.keySet().stream().forEach(setNumber -> {
			for (Card card : cardsSetPlayer.get(setNumber)) {
				tableSituation.append(card.getFace()).append(CARDS_SEPARATOR);
			}
			tableSituation.append(SET_SEPARATOR);
		});

		tableSituation.deleteCharAt(tableSituation.lastIndexOf(CARDS_SEPARATOR));
		tableSituation.append(PLAYER_SEPARATOR).append(dealerUpCard.getFace());
		return tableSituation.toString();
	}

	public static List<String> getTableSituationPerGroupOfCards(String fullTableSituation) {
		Pattern p = Pattern.compile("[0-A]+,+[0-A]");

		List<String> matches = new ArrayList<>();
		List<String> situations = new ArrayList<>();
		Matcher m = p.matcher(fullTableSituation);
		while (m.find()) {
	        matches.add(m.group(0));
	    }

		for (String match : matches) {
			match += (PLAYER_SEPARATOR + getDealerUpCard(fullTableSituation));
			situations.add(match);
		}
		

		return situations;
	}

	public static String parseTableSituation(Integer sumPlayerCards, String dealerUpCardFace) {
		StringBuilder tableSituation = new StringBuilder();
		tableSituation.append(sumPlayerCards);
		tableSituation.append(";").append(dealerUpCardFace);
		return tableSituation.toString();
	}

}