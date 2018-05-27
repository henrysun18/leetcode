package ReverseInteger;

/**
 * Created by Henry on 5/26/2018.
 * Problem 7
 */
public class Solution {
	public int reverse(int x) {
		int absX = Math.abs(x);
		String reversedAsString = x < 1 ? "-" : "";

		while (absX > 0) {
			reversedAsString += absX % 10;
			absX /= 10;
		}

		try  {
			return Integer.parseInt(reversedAsString);
		} catch (NumberFormatException e) {
			return 0;
		}
	}
}
