package com.cards;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString(of = {"bet", "cardsGroup"})
public class CardsBet {
	
	@Builder.Default
	private List<Card> cardsGroup = new ArrayList();
	@Builder.Default
	private Integer bet = 0;

}
