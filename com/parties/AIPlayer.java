package com.parties;

import static com.parsers.TableSituationParser.getTableSituationPerGroupOfCards;

import java.util.ArrayList;
import java.util.List;

import com.cards.Card;
import com.parsers.TableSituationParser;
import com.statistics.ResultsCounter;
import com.strategies.BasicStrategyMap;
import com.strategies.CardCountingHiLo;
import com.ui.ConsoleManager;
import com.utils.CardUtils;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AIPlayer {

	@Builder.Default
	private BasicStrategyMap bs = BasicStrategyMap.builder().build();
	@Builder.Default
	private ResultsCounter resultsCounter = ResultsCounter.builder().build();

	public String decideBestPlay(List<Card> groupCards, String tableSituation) {

		String bestPlay = "";
		ConsoleManager.showTableSituation(tableSituation);

		if (groupCards.get(0).getFace().equals("10") && groupCards.get(1).getFace().equals("A")) {
			ConsoleManager.showBlackjack();
			resultsCounter.addBlackjack();
		} else {
			List<String> cardsGroups = getTableSituationPerGroupOfCards(tableSituation);
			for (String cardGroup : cardsGroups) {
				bestPlay = bs.nextPlay(cardGroup);
				if (bestPlay == null) {
					Integer sumCardsPlayer = CardUtils.getSumOfCards(groupCards);
					bestPlay = takeDecisionBasedOnSum(sumCardsPlayer, groupCards, tableSituation.split(";")[1]);
				}
			}
		}
		return bestPlay;

	}

	private String takeDecisionBasedOnSum(Integer sumCardsPlayer, List<Card> playerCards, String dealerUpCard) {
		String bestPlay;
		if (sumCardsPlayer >= 17) {
			bestPlay = "S";
		} else if (sumCardsPlayer <= 8) {
			bestPlay = "H";
		} else {
			String tableSituation = TableSituationParser.parseTableSituation(sumCardsPlayer, dealerUpCard);
			bestPlay = bs.getBasicStrategy().get(tableSituation);
		}
		return bestPlay;
	}


	public void countCards(List<Card> playerCardsFromCurrentGroup, Card upCard) {
		List<Card> allCardsDealt = new ArrayList<Card>();
		allCardsDealt.addAll(playerCardsFromCurrentGroup);
		allCardsDealt.add(upCard);
		
	}

}
