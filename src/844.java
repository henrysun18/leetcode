class BackspaceStringCompare {
	public boolean backspaceCompare(String S, String T) {
		int pointerS = S.length()-1;
		int pointerT = T.length()-1;

		int backspacesS = 0; //don't put this INSIDE the while loop, otherwise when pointerS or pointerT < 0 it'll get prematurely reset
		int backspacesT = 0;
		while (pointerS >= 0 || pointerT >= 0) {
			if (pointerS < 0) {
				if (T.charAt(pointerT) == '#') {
					backspacesT++;
				} else {
					if (backspacesT == 0) {
						return false;
					}
					backspacesT--;
				}
				pointerT--;
			} else if (pointerT < 0) {
				if (S.charAt(pointerS) == '#') {
					backspacesS++;
				} else {
					if (backspacesS == 0) {
						return false;
					}
					backspacesS--;
				}
				pointerS--;
			} else {
				//move to next s
				while (pointerS >= 0) {
					char s = S.charAt(pointerS);
					if (s == '#') {
						backspacesS++;
					} else if (backspacesS == 0) {
						break;
					} else {
						backspacesS--;
					}
					pointerS--;
				}
				//move to next t
				while(pointerT >= 0) {
					char t = T.charAt(pointerT);
					if (t == '#') {
						backspacesT++;
					} else if (backspacesT == 0) {
						break;
					} else {
						backspacesT--;
					}
					pointerT--;
				}
				if (pointerS >= 0 && pointerT >= 0) {
					char s = S.charAt(pointerS);
					char t = T.charAt(pointerT);
					if (s != t) {
						return false;
					}
					pointerS--;
					pointerT--;
				}
			}
		}

		return true;
	}

	public static void main(String... args) {
		BackspaceStringCompare app = new BackspaceStringCompare();
		app.backspaceCompare("nzp#", "b#nzp#");
	}
}
