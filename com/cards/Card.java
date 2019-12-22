package com.cards;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Builder
@Setter
@EqualsAndHashCode(of = {"face","suit"})
@ToString(of = {"face","suit"})
public class Card {
	
	private String face;
	private String suit;
	

}
