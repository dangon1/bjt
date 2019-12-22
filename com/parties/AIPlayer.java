package com.parties;

import java.util.List;
import java.util.Map;

import com.cards.Card;
import com.parsers.TableSituationParser;
import com.statistics.ResultsCounter;
import com.strategies.BasicStrategyMap;

import lombok.Builder;
import lombok.Getter;
import static com.parsers.TableSituationParser.SET_SEPARATOR;
import static com.parsers.TableSituationParser.PLAYER_SEPARATOR;
import static com.parsers.TableSituationParser.getDealerUpCard;
import static com.parsers.TableSituationParser.getTableSituationPerGroupOfCards;

@Getter
@Builder
public class AIPlayer {

	@Builder.Default
	private BasicStrategyMap bs = BasicStrategyMap.builder().build();
	@Builder.Default
	private ResultsCounter resultsCounter = ResultsCounter.builder().build();

	public String decideBestPlay(Player player, String fullTableSituation) {
		Map<Integer, List<Card>> playerCardGroups = player.getCardGroups();
		String bestPlay = "";
		System.out.println("HAND -->" + fullTableSituation);

		for (Integer groupNumber : playerCardGroups.keySet()) {
			List<Card> cardsFromActualGroup = playerCardGroups.get(groupNumber);
			if (cardsFromActualGroup.get(0).getFace().equals("10") && cardsFromActualGroup.get(1).getFace().equals("A")) {
				System.out.println("BLACKJACK!");
				resultsCounter.addBlackjack();
			} else {
				List<String> cardsGroups = getTableSituationPerGroupOfCards(fullTableSituation);
				for (String cardGroup : cardsGroups) {
					bestPlay = bs.nextPlay(cardGroup);
					if (bestPlay == null || !bestPlay.equals("Y")) {
						Integer sumCardsPlayer = player.getSumCards().get(groupNumber);
						bestPlay = takeDecisionBasedOnSum(sumCardsPlayer, cardsFromActualGroup, fullTableSituation.split(";")[1]);
					} else {
						System.out.println("Decision --> " + bestPlay);
					}

				}
			}
		}
		return bestPlay;

	}

	private String takeDecisionBasedOnSum(Integer sumCardsPlayer, List<Card> playerCards, String dealerUpCard) {
		String bestPlay;
		if (sumCardsPlayer >= 18) {
			bestPlay = "S";
		} else if (sumCardsPlayer <= 8) {
			bestPlay = "H";
		} else {
			String tableSituation = TableSituationParser.parseTableSituation(sumCardsPlayer, dealerUpCard);
			bestPlay = bs.getBasicStrategy().get(tableSituation);
		}
		return bestPlay;
	}

}
