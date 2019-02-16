class BasicCalculatorII {
	public int calculate(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		s = s.replace(" ", "");
		int sum = 0;
		String[] exprsToAdd = s.split("[+]");

		for (String expr : exprsToAdd) {
			sum += parseExpr(expr);
		}

		return sum;
	}

	private int parseExpr(String expr) {
		String[] exprsToSub = expr.split("[-]");

		int total = parseProductsAndQuotients(exprsToSub[0]);

		for (int i = 1; i < exprsToSub.length; i++) {
			total -= parseProductsAndQuotients(exprsToSub[i]);
		}

		return total;
	}

	private int parseProductsAndQuotients(String expr) {
		if (Math.max(expr.indexOf('/'), expr.indexOf('*')) == -1) {
			return Integer.parseInt(expr);
		}

		String[] exprs = expr.split("[*/]");

		int operatorIndex = exprs[0].length();
		int total = Integer.parseInt(exprs[0]);

		for (int i = 1; i < exprs.length; i++) {
			if (expr.charAt(operatorIndex) == '*') {
				total = total * Integer.parseInt(exprs[i]);
			} else {
				total = total / Integer.parseInt(exprs[i]);
			}
			operatorIndex += exprs[i].length()+1;
		}

		return total;
	}
}
