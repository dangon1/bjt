package com.strategies;

import java.util.HashMap;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BasicStrategyMap {

	@Builder.Default
	private Map<String, String> basicStrategy = new HashMap<String, String>();

	public void setStrategies() {
		splittingStrategy();
		softTotals();
		hardTotals();

	}

	private void hardTotals() {

		// sum 17
		basicStrategy.put("17;2", "S");
		basicStrategy.put("17;3", "S");
		basicStrategy.put("17;4", "S");
		basicStrategy.put("17;5", "S");
		basicStrategy.put("17;6", "S");
		basicStrategy.put("17;7", "S");
		basicStrategy.put("17;8", "S");
		basicStrategy.put("17;9", "S");
		basicStrategy.put("17;10", "S");
		basicStrategy.put("17;A", "S");

		// sum 16
		basicStrategy.put("16;2", "S");
		basicStrategy.put("16;3", "S");
		basicStrategy.put("16;4", "S");
		basicStrategy.put("16;5", "S");
		basicStrategy.put("16;6", "S");
		basicStrategy.put("16;7", "H");
		basicStrategy.put("16;8", "H");
		basicStrategy.put("16;9", "SUR");
		basicStrategy.put("16;10", "SUR");
		basicStrategy.put("16;A", "SUR");

		// sum 15
		basicStrategy.put("15;2", "S");
		basicStrategy.put("15;3", "S");
		basicStrategy.put("15;4", "S");
		basicStrategy.put("15;5", "S");
		basicStrategy.put("15;6", "S");
		basicStrategy.put("15;7", "H");
		basicStrategy.put("15;8", "H");
		basicStrategy.put("15;9", "H");
		basicStrategy.put("15;10", "SUR");
		basicStrategy.put("15;A", "H");

		// sum 14
		basicStrategy.put("14;2", "S");
		basicStrategy.put("14;3", "S");
		basicStrategy.put("14;4", "S");
		basicStrategy.put("14;5", "S");
		basicStrategy.put("14;6", "S");
		basicStrategy.put("14;7", "H");
		basicStrategy.put("14;8", "H");
		basicStrategy.put("14;9", "H");
		basicStrategy.put("14;10", "H");
		basicStrategy.put("14;A", "H");

		// sum 13
		basicStrategy.put("13;2", "S");
		basicStrategy.put("13;3", "S");
		basicStrategy.put("13;4", "S");
		basicStrategy.put("13;5", "S");
		basicStrategy.put("13;6", "S");
		basicStrategy.put("13;7", "H");
		basicStrategy.put("13;8", "H");
		basicStrategy.put("13;9", "H");
		basicStrategy.put("13;10", "H");
		basicStrategy.put("13;A", "H");

		// sum 12
		basicStrategy.put("12;2", "H");
		basicStrategy.put("12;3", "H");
		basicStrategy.put("12;4", "S");
		basicStrategy.put("12;5", "S");
		basicStrategy.put("12;6", "S");
		basicStrategy.put("12;7", "H");
		basicStrategy.put("12;8", "H");
		basicStrategy.put("12;9", "H");
		basicStrategy.put("12;10", "H");
		basicStrategy.put("12;A", "H");

		// sum 11
		basicStrategy.put("11;2", "D");
		basicStrategy.put("11;3", "D");
		basicStrategy.put("11;4", "D");
		basicStrategy.put("11;5", "D");
		basicStrategy.put("11;6", "D");
		basicStrategy.put("11;7", "D");
		basicStrategy.put("11;8", "D");
		basicStrategy.put("11;9", "D");
		basicStrategy.put("11;10", "D");
		basicStrategy.put("11;A", "D");

		// sum 10
		basicStrategy.put("10;2", "D");
		basicStrategy.put("10;3", "D");
		basicStrategy.put("10;4", "D");
		basicStrategy.put("10;5", "D");
		basicStrategy.put("10;6", "D");
		basicStrategy.put("10;7", "D");
		basicStrategy.put("10;8", "D");
		basicStrategy.put("10;9", "D");
		basicStrategy.put("10;10", "H");
		basicStrategy.put("10;A", "H");

		// sum 9
		basicStrategy.put("9;2", "H");
		basicStrategy.put("9;3", "D");
		basicStrategy.put("9;4", "D");
		basicStrategy.put("9;5", "D");
		basicStrategy.put("9;6", "D");
		basicStrategy.put("9;7", "H");
		basicStrategy.put("9;8", "H");
		basicStrategy.put("9;9", "H");
		basicStrategy.put("9;10", "H");
		basicStrategy.put("9;A", "H");

		// sum 8
		basicStrategy.put("8;2", "H");
		basicStrategy.put("8;3", "H");
		basicStrategy.put("8;4", "H");
		basicStrategy.put("8;5", "H");
		basicStrategy.put("8;6", "H");
		basicStrategy.put("8;7", "H");
		basicStrategy.put("8;8", "H");
		basicStrategy.put("8;8", "H");
		basicStrategy.put("8;10", "H");
		basicStrategy.put("8;A", "H");

	}

	public String nextPlay(String tableSituation) {

		return basicStrategy.get(tableSituation);
	}

	private void splittingStrategy() {

		// A.A
		basicStrategy.put("A,A;2", "Y");
		basicStrategy.put("A,A;3", "Y");
		basicStrategy.put("A,A;4", "Y");
		basicStrategy.put("A,A;5", "Y");
		basicStrategy.put("A,A;6", "Y");
		basicStrategy.put("A,A;7", "Y");
		basicStrategy.put("A,A;8", "Y");
		basicStrategy.put("A,A;9", "Y");
		basicStrategy.put("A,A;10", "Y");
		basicStrategy.put("A,A;A", "Y");

		// 10,10
		basicStrategy.put("10,10;2", "N");
		basicStrategy.put("10,10;3", "N");
		basicStrategy.put("10,10;4", "N");
		basicStrategy.put("10,10;5", "N");
		basicStrategy.put("10,10;6", "N");
		basicStrategy.put("10,10;7", "N");
		basicStrategy.put("10,10;8", "N");
		basicStrategy.put("10,10;9", "N");
		basicStrategy.put("10,10;10", "N");
		basicStrategy.put("10,10;A", "N");

		// 9,9
		basicStrategy.put("9,9;2", "Y");
		basicStrategy.put("9,9;3", "Y");
		basicStrategy.put("9,9;4", "Y");
		basicStrategy.put("9,9;5", "Y");
		basicStrategy.put("9,9;6", "Y");
		basicStrategy.put("9,9;7", "N");
		basicStrategy.put("9,9;8", "Y");
		basicStrategy.put("9,9;9", "Y");
		basicStrategy.put("9,9;10", "N");
		basicStrategy.put("9,9;A", "N");

		// 8,8
		basicStrategy.put("8,8;2", "Y");
		basicStrategy.put("8,8;3", "Y");
		basicStrategy.put("8,8;4", "Y");
		basicStrategy.put("8,8;5", "Y");
		basicStrategy.put("8,8;6", "Y");
		basicStrategy.put("8,8;7", "Y");
		basicStrategy.put("8,8;8", "Y");
		basicStrategy.put("8,8;9", "Y");
		basicStrategy.put("8,8;10", "Y");
		basicStrategy.put("8,8;A", "Y");

		// 7,7
		basicStrategy.put("7,7;2", "Y");
		basicStrategy.put("7,7;3", "Y");
		basicStrategy.put("7,7;4", "Y");
		basicStrategy.put("7,7;5", "Y");
		basicStrategy.put("7,7;6", "Y");
		basicStrategy.put("7,7;7", "Y");
		basicStrategy.put("7,7;8", "N");
		basicStrategy.put("7,7;9", "N");
		basicStrategy.put("7,7;10", "N");
		basicStrategy.put("7,7;A", "N");

		// 6,6
		basicStrategy.put("6,6;2", "Y/N");
		basicStrategy.put("6,6;3", "Y");
		basicStrategy.put("6,6;4", "Y");
		basicStrategy.put("6,6;5", "Y");
		basicStrategy.put("6,6;6", "Y");
		basicStrategy.put("6,6;7", "N");
		basicStrategy.put("6,6;8", "N");
		basicStrategy.put("6,6;9", "N");
		basicStrategy.put("6,6;10", "N");
		basicStrategy.put("6,6;A", "N");

		// 5,5
		basicStrategy.put("5,5;2", "N");
		basicStrategy.put("5,5;3", "N");
		basicStrategy.put("5,5;4", "N");
		basicStrategy.put("5,5;5", "N");
		basicStrategy.put("5,5;6", "N");
		basicStrategy.put("5,5;7", "N");
		basicStrategy.put("5,5;8", "N");
		basicStrategy.put("5,5;9", "N");
		basicStrategy.put("5,5;10", "N");
		basicStrategy.put("5,5;A", "N");

		// 4,4
		basicStrategy.put("4,4;2", "N");
		basicStrategy.put("4,4;3", "N");
		basicStrategy.put("4,4;4", "N");
		basicStrategy.put("4,4;5", "Y/N");
		basicStrategy.put("4,4;6", "Y/N");
		basicStrategy.put("4,4;7", "N");
		basicStrategy.put("4,4;8", "N");
		basicStrategy.put("4,4;9", "N");
		basicStrategy.put("4,4;10", "N");
		basicStrategy.put("4,4;A", "N");

		// 3,3
		basicStrategy.put("3,3;2", "Y/N");
		basicStrategy.put("3,3;3", "Y/N");
		basicStrategy.put("3,3;4", "Y");
		basicStrategy.put("3,3;5", "Y");
		basicStrategy.put("3,3;6", "Y");
		basicStrategy.put("3,3;7", "Y");
		basicStrategy.put("3,3;8", "N");
		basicStrategy.put("3,3;9", "N");
		basicStrategy.put("3,3;10", "N");
		basicStrategy.put("3,3;A", "N");

		// 2,2
		basicStrategy.put("2,2;2", "Y/N");
		basicStrategy.put("2,2;3", "Y/N");
		basicStrategy.put("2,2;4", "Y");
		basicStrategy.put("2,2;5", "Y");
		basicStrategy.put("2,2;6", "Y");
		basicStrategy.put("2,2;7", "Y");
		basicStrategy.put("2,2;8", "N");
		basicStrategy.put("2,2;9", "N");
		basicStrategy.put("2,2;10", "N");
		basicStrategy.put("2,2;A", "N");

	}

	public void softTotals() {

		// A,9
		basicStrategy.put("9,A;2", "S");
		basicStrategy.put("9,A;3", "S");
		basicStrategy.put("9,A;4", "S");
		basicStrategy.put("9,A;5", "S");
		basicStrategy.put("9,A;6", "S");
		basicStrategy.put("9,A;7", "S");
		basicStrategy.put("9,A;8", "S");
		basicStrategy.put("9,A;9", "S");
		basicStrategy.put("9,A;10", "S");
		basicStrategy.put("9,A;A", "S");

		// A,8
		basicStrategy.put("8,A;2", "S");
		basicStrategy.put("8,A;3", "S");
		basicStrategy.put("8,A;4", "S");
		basicStrategy.put("8,A;5", "S");
		basicStrategy.put("8,A;6", "Ds");
		basicStrategy.put("8,A;7", "S");
		basicStrategy.put("8,A;8", "S");
		basicStrategy.put("8,A;9", "S");
		basicStrategy.put("8,A;10", "S");
		basicStrategy.put("8,A;A", "S");

		// A,7
		basicStrategy.put("7,A;2", "Ds");
		basicStrategy.put("7,A;3", "Ds");
		basicStrategy.put("7,A;4", "Ds");
		basicStrategy.put("7,A;5", "Ds");
		basicStrategy.put("7,A;6", "Ds");
		basicStrategy.put("7,A;7", "S");
		basicStrategy.put("7,A;8", "S");
		basicStrategy.put("7,A;9", "H");
		basicStrategy.put("7,A;10", "H");
		basicStrategy.put("7,A;A", "H");

		// A,6
		basicStrategy.put("6,A;2", "H");
		basicStrategy.put("6,A;3", "D");
		basicStrategy.put("6,A;4", "D");
		basicStrategy.put("6,A;5", "D");
		basicStrategy.put("6,A;6", "D");
		basicStrategy.put("6,A;7", "H");
		basicStrategy.put("6,A;8", "H");
		basicStrategy.put("6,A;9", "H");
		basicStrategy.put("6,A;10", "H");
		basicStrategy.put("6,A;A", "H");

		// A,5
		basicStrategy.put("5,A;2", "H");
		basicStrategy.put("5,A;3", "H");
		basicStrategy.put("5,A;4", "D");
		basicStrategy.put("5,A;5", "D");
		basicStrategy.put("5,A;6", "D");
		basicStrategy.put("5,A;7", "H");
		basicStrategy.put("5,A;8", "H");
		basicStrategy.put("5,A;9", "H");
		basicStrategy.put("5,A;10", "H");
		basicStrategy.put("5,A;A", "H");

		// A,4
		basicStrategy.put("4,A;2", "H");
		basicStrategy.put("4,A;3", "H");
		basicStrategy.put("4,A;4", "D");
		basicStrategy.put("4,A;5", "D");
		basicStrategy.put("4,A;6", "D");
		basicStrategy.put("4,A;7", "H");
		basicStrategy.put("4,A;8", "H");
		basicStrategy.put("4,A;9", "H");
		basicStrategy.put("4,A;10", "H");
		basicStrategy.put("4,A;A", "H");

		// A,3
		basicStrategy.put("3,A;2", "H");
		basicStrategy.put("3,A;3", "H");
		basicStrategy.put("3,A;4", "H");
		basicStrategy.put("3,A;5", "D");
		basicStrategy.put("3,A;6", "D");
		basicStrategy.put("3,A;7", "H");
		basicStrategy.put("3,A;8", "H");
		basicStrategy.put("3,A;9", "H");
		basicStrategy.put("3,A;10", "H");
		basicStrategy.put("3,A;A", "H");

		// A,2
		basicStrategy.put("2,A;2", "H");
		basicStrategy.put("2,A;3", "H");
		basicStrategy.put("2,A;4", "D");
		basicStrategy.put("2,A;5", "D");
		basicStrategy.put("2,A;6", "H");
		basicStrategy.put("2,A;7", "H");
		basicStrategy.put("2,A;8", "H");
		basicStrategy.put("2,A;9", "H");
		basicStrategy.put("2,A;10", "H");
		basicStrategy.put("2,A;A", "H");

	}

}
