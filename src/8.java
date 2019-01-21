/**
 * Created by Henry on 5/26/2018.
 * Problem 8
 */
class StringToIntegerAtio {
	public int myAtoi(String str) {

		String strWithoutLeadingSpaces = str.trim();
		int strWithoutLeadingSpacesLength = strWithoutLeadingSpaces.length();
		if (strWithoutLeadingSpaces == "") return 0;

		boolean isNegative = false;
		int charIndex = 0;
		if (strWithoutLeadingSpaces.charAt(charIndex) == '-') {
			isNegative = true;
			charIndex++;
		} else if (strWithoutLeadingSpaces.charAt(charIndex) == '+') {
			charIndex++;
		}

		if (charIndex >= strWithoutLeadingSpacesLength) return 0;
		char nextChar = strWithoutLeadingSpaces.charAt(charIndex);
		long convertedNum = 0;

		while (nextChar >= '0' && nextChar <= '9') {
			convertedNum = convertedNum * 10 + Character.getNumericValue(nextChar);

			if (isNegative && -convertedNum <= Integer.MIN_VALUE) return Integer.MIN_VALUE;
			if (!isNegative && convertedNum >= Integer.MAX_VALUE) return Integer.MAX_VALUE;

			charIndex++;
			if (charIndex >= strWithoutLeadingSpacesLength) break;
			nextChar = strWithoutLeadingSpaces.charAt(charIndex);
		}
		return isNegative ? -(int)convertedNum : (int)convertedNum;
	}
}
