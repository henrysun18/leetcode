import java.util.ArrayList;
import java.util.List;

class ExpressionAddOperators {
	public List<String> addOperators(String num, int target) {
		List<String> res = new ArrayList<>();
		if (num == null || num.length() == 0) {
			return res;
		}
		if (Long.parseLong(num) > Integer.MAX_VALUE) {
			return res;
		}
		int firstDigit = num.charAt(0) - '0';
		dfs(num, target, 1, firstDigit, firstDigit, firstDigit, num.substring(0,1), res);
		return res;
	}

	private void dfs(String num, int target, int currIndex, int runningSum, int currProduct, int currNum, String expr, List<String> res) {
		if (currIndex == num.length()) {
			if (runningSum == target) {
				res.add(expr);
			}
			return;
		}
		int digit = num.charAt(currIndex) - '0';

		//multiply most recent product with curr
		dfs(num, target, currIndex+1, runningSum - currProduct + currProduct * digit, currProduct * digit, digit, expr + "*" + digit, res);

		//add whatever we have to curr
		dfs(num, target, currIndex+1, runningSum + digit, digit, digit, expr + "+" + digit, res);

		//minus curr from whatever we have
		dfs(num, target, currIndex+1, runningSum - digit, -digit, -digit, expr + "-" + digit, res);



		//this really isn't needed if we just have a for loop around the top 3 dfs calls....
		if (currNum != 0) {
			//try to make the current num longer
			int extended = currNum < 0 ? currNum * 10 - digit : currNum * 10 + digit;
			int newProduct = currProduct/currNum*extended;
			dfs(num, target, currIndex+1, runningSum-currProduct+newProduct, newProduct, extended, expr + digit, res);
		}
	}
}
