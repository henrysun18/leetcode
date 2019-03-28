class AddBoldTagInString {
	public String addBoldTag(String s, String[] dict) {
		String openTag = "<b>";
		String closeTag = "</b>";

		//we want an auxiliary array to check if any character belongs within a tag
		//to do so, check if any substring starting at the character's position belongs in dict
		//this cna be done in n^2 time

		//i think this method generates too many intermediate substrings, slowing it down tremendously
        /*
        Set<String> words = new HashSet<>();
        for (String word : dict) {
            words.add(word);
        }

        int nextUnknown = 0;
        boolean[] bold = new boolean[s.length()];
        for (int start = 0; start < s.length(); start++) {
            for (int end = Math.max(start+1, nextUnknown+1); end <= s.length(); end++) {
                String substr = s.substring(start, end);
                if (words.contains(substr)) {
                    for (int i = start; i < end; i++) {
                        bold[i] = true;
                    }
                    nextUnknown = end-1;
                }
            }
        }
        */
		boolean[] bold = new boolean[s.length()];
		int nextUnknown = 0;
		for (int start = 0; start < s.length(); start++) {
			String substring = s.substring(start);
			for (String word : dict) {
				if (substring.startsWith(word)) {
					nextUnknown = Math.max(nextUnknown, start + word.length());
					for (int i = start; i < start+word.length(); i++) {
						bold[i] = true;
					}
				}
			}
		}


		StringBuilder sb = new StringBuilder();
		boolean opened = false;
		for (int i = 0; i < s.length(); i++) {
			if (bold[i] && !opened) {
				sb.append(openTag);
				opened = true; //don't forget to actually set and reset this flag
			} else if (!bold[i] && opened) {
				sb.append(closeTag);
				opened = false;
			}
			sb.append(s.charAt(i));
		}
		if (opened) {
			sb.append(closeTag); //DONT FORGET TO FINISH UP THE CLOSING IF IT'S STILL OPEN PLS
		}
		return sb.toString();
	}
}
