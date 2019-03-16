import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class IntegerToEnglishWords {
	public String numberToWords(int num) {
		if (num == 0) return "Zero";

		List<String> words = new LinkedList<>();

		String[] decimations = {"", "Thousand", "Million", "Billion"};
		int currentDecimation = 0;

		Map<Integer, String> under20 = new HashMap<>();
		under20.put(1, "One");
		under20.put(2, "Two");
		under20.put(3, "Three");
		under20.put(4, "Four");
		under20.put(5, "Five");
		under20.put(6, "Six");
		under20.put(7, "Seven");
		under20.put(8, "Eight");
		under20.put(9, "Nine");
		under20.put(10, "Ten");
		under20.put(11, "Eleven");
		under20.put(12, "Twelve");
		under20.put(13, "Thirteen");
		under20.put(14, "Fourteen");
		under20.put(15, "Fifteen");
		under20.put(16, "Sixteen");
		under20.put(17, "Seventeen");
		under20.put(18, "Eighteen");
		under20.put(19, "Nineteen");

		Map<Integer, String> tens = new HashMap<>();
		tens.put(10, "Ten");
		tens.put(20, "Twenty");
		tens.put(30, "Thirty");
		tens.put(40, "Forty");
		tens.put(50, "Fifty");
		tens.put(60, "Sixty");
		tens.put(70, "Seventy");
		tens.put(80, "Eighty");
		tens.put(90, "Ninety");


		while (num > 0) {
			int last3 = num % 1000;

			// need this check otherwise 1000000 -> one million thousand
			if (last3 != 0) {
				// add "million", "billion", etc at the end first
				words.add(0, decimations[currentDecimation]);


				//insert 2nd and 3rd digit representation in front of list
				int last2 = last3 % 100;
				if (last2 > 0 && last2 < 20) {
					words.add(0, under20.get(last2));
				} else if (last2 > 0) { //at this point, last2 is either 0 or between 20 and 99
					int last1 = last2 % 10;
					if (last1 > 0) {
						words.add(0, under20.get(last1));
					}
					words.add(0, tens.get(last2 / 10 * 10)); //either multiply key by 10 or change keys to 1,2,3... instead of 10,20,30...!!!
				}
				//insert 1st digit (hundreds)
				int first1 = last3 / 100;
				if (first1 > 0) {
					words.add(0, under20.get(first1) + " Hundred");
				}
			}

			num = num / 1000;
			currentDecimation++;
		}

		return String.join(" ", words).trim();
	}
}
