class SolveTheEquation {
	public String solveEquation(String equation) {
		String cleaned = equation.replaceAll("[-]", "+-");
		System.out.println(cleaned);
		String[] sides = cleaned.split("[=]");
		String lhs = sides[0];
		String rhs = sides[1];

		String[] lhsExprs = lhs.split("[+]");
		String[] rhsExprs = rhs.split("[+]");

		int lhsCoeff = 0;
		int lhsConst = 0;
		int rhsCoeff = 0;
		int rhsConst = 0;

		// split "+-5" by + results in "" first element, so ignore empty
		for (String expr : lhsExprs) {
			if (expr.equals("")) continue;
			if (expr.contains("x")) {
				if (expr.length() == 1) {
					lhsCoeff++;
				} else if (expr.length() == 2 && expr.contains("-")) {
					lhsCoeff--; //can't parse just "-" in "-x" so handle this case pls
				} else {
					lhsCoeff += Integer.parseInt(expr.substring(0, expr.length()-1));
				}
			} else {
				lhsConst += Integer.parseInt(expr);
			}
		}
		for (String expr : rhsExprs) {
			if (expr.equals("")) continue;
			if (expr.contains("x")) {
				if (expr.length() == 1) {
					rhsCoeff++;
				} else if (expr.length() == 2 && expr.contains("-")) {
					rhsCoeff--;
				} else {
					rhsCoeff += Integer.parseInt(expr.substring(0, expr.length()-1));
				}
			} else {
				rhsConst += Integer.parseInt(expr);
			}
		}

		// simplify using the 4 int counters
		lhsCoeff -= rhsCoeff;
		rhsCoeff = 0;
		rhsConst -= lhsConst;
		lhsConst = 0;

		if (lhsCoeff == 0 && rhsConst == 0) {
			return "Infinite solutions";
		} else if (lhsCoeff == 0 && rhsConst != 0) {
			return "No solution";
		} else {
			return "x=" + (rhsConst / lhsCoeff);
		}
	}
}
