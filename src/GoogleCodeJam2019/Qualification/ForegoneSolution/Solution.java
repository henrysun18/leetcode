package GoogleCodeJam2019.Qualification.ForegoneSolution;

import java.util.*;
import java.io.*;

class Solution {
	//4 -> 3 + 1
	//111111114 -> 111111113 + 1
	//basically just replace all 4's with 3 and supplement with B
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int numTests = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc

		for (int i = 1; i <= numTests; i++) {
			String N = in.next();
			StringBuilder A = new StringBuilder();
			StringBuilder B = new StringBuilder();
			while (N.length() > 0) {
				int lastDigit = N.charAt(N.length()-1) - '0';
				if (lastDigit == 4) {
					A.insert(0, 3);
					B.insert(0, 1);
				} else {
					A.insert(0, lastDigit);
					B.insert(0, 0);
				}
				N = N.substring(0, N.length()-1);
			}

			//remove leading zeros in B
			while (B.length() > 0 && B.charAt(0) == '0') {
				B.deleteCharAt(0);
			}
			//at least one digit is 4, so don't worry about setting B = 0 if empty

			System.out.println("Case #" + i + ": " + A + " " + B);
		}
	}
}
