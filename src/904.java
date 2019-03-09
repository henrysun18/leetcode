class FruitIntoBaskets {
	public int totalFruit(int[] tree) {
		//brute force is to go through all i see how many fruits we can collect from there, n^2

		//try two pointers, left at first basket, right at second basket
		//once we can't move right basket anymore, move left basket such that left basket is empty, then move right again and repeat

		if (tree == null || tree.length == 0) {
			return 0;
		}

		int left = 0, right = 0;
		int fruit1 = -1;
		int fruit2 = -1;
		int count1 = 0, count2 = 0;



		int max = 0;

		while (right < tree.length) {
			// move right over until we can't add the next one
			while (right < tree.length) {
				if (fruit1 == -1 || tree[right] == fruit1) {
					fruit1 = tree[right];
					count1++;
				} else if (fruit2 == -1 || tree[right] == fruit2) {
					fruit2 = tree[right];
					count2++;
				} else {
					break;
				}
				right++;
			}

			int numFruits = count1 + count2;
			if (numFruits > max) {
				max = numFruits;
			}

			// move left over until one of the baskets are empty (not if strictly basket 1 is empty!!!)
			while (count1 > 0 && count2 > 0) {
				if (tree[left] == fruit1) {
					count1--;
				} else {
					count2--;
				}
				left++;
			}

			if (count1 == 0) {
				// swap move fruit2 info into fruit1 so that we consider left to be pointing to a fruit1
				fruit1 = fruit2;
				count1 = count2;
			}
			fruit2 = -1;
			count2 = 0;
		}

		return max;
	}
}
