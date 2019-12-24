package com.statistics;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ResultsCounter {
	@Builder.Default
	private Double totalBlackjacks = new Double(0);
	
	@Builder.Default
	private Double totalVictories = new Double(0);
	
	@Builder.Default
	private Double totalDraws = new Double(0);
	
	@Builder.Default
	private Double totalDefeats = new Double(0);
	
	@Builder.Default
	private Integer totalProfit = new Integer(0);
	
	@Builder.Default
	private Double totalHands = new Double(0);

	public void addBlackjack() {
		totalBlackjacks++;
	}
	
	public void addVictory() {
		totalVictories++;
	}

	public void addTotalHands() {
		totalHands++;
	}
	
	public void addProfit(Integer value) {
		totalProfit += value;
	}

	public Double getPercentageBlackjacks() {
		return (totalBlackjacks / totalHands) * 100;
	}
	
	public Double getPercentageVictory() {
		return (totalVictories / totalHands) * 100;
	}
	
	public Double getPercentageDraw() {
		return (totalDraws / totalHands) * 100;
	}
	
	public Double getPercentageDefeats() {
		return (totalDefeats / totalHands) * 100;
	}

}
