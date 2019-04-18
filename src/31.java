class NextPermutation {
	public void nextPermutation(int[] nums) {
		//if nums are always non-increasing, then simply reverse
		//otherwise, if we get [1,5,4,3,2,1], we want to return [2,1,1,3,4,5]

		//the pattern is that we look for chains of non-increasing sequences
		// [1,2,3,6,5,4,3] -> [1,2,3,  3,4,5,6] -> [1,2,4,3,3,5,6] (get the next greater element and insert it so that new nums > old nums)

		//[1,2,3,4,5,5,5,6] -> [1,2,3,4,5,5,5,6] -> [1,2,3,4,5,6,5] (last chain of non-increasing seq starts at last element)

		//keep a pointer to the index right before the chain of non-increasing sequences, as we'll want to insert the next highest value there

		//[1,2,3,4,5,5,5] -> [1,2,3,4,5,5,5] where pointer to the right before is at 4. we'll look for the first element greater than 4 to insert inside

		//[1,1,1,2,3,5,1] -> [1,1,1,2,   3,1,5], then move the 5 into where 3 was [1,1,1,2,5,  3,1], then move the 3 into where it belongs

		int indexToInsert = 0;
		boolean isGreatestPermutation = true;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] > nums[i-1]) {
				//only update to-replace index once we know nums[i-1] is where our next permutation deviates from nums array
				indexToInsert = i-1;
				isGreatestPermutation = false;
			}
		}
		//swap everything to the right of indexToInsert
		if (isGreatestPermutation) {
			indexToInsert = -1;
		}

		int left = indexToInsert+1;
		int right = nums.length-1;
		while (left < right) {
			int tmp = nums[left];
			nums[left] = nums[right];
			nums[right] = tmp;

			left++;
			right--;
		}

		if (isGreatestPermutation) return; //swapping everything is enough, no insertion needed

		//find next greatest element for insertion into indexToInsert
		int nextGreatest = indexToInsert+1;
		while (nextGreatest < nums.length-1 && nums[nextGreatest] <= nums[indexToInsert]) {
			nextGreatest++;
		}

		//insert
		int numToMove = nums[nextGreatest];
		int curr = nextGreatest;
		while (curr > indexToInsert) {
			nums[curr] = nums[curr-1];

			curr--;
		}
		nums[indexToInsert] = numToMove;

		//nums[indexToInsert+1] now might be unsorted, so shift it and insert into the best place
		numToMove = nums[indexToInsert+1];
		curr = indexToInsert+2;
		while (curr < nums.length && nums[curr] < numToMove) {
			nums[curr-1] = nums[curr];
			curr++;
		}
		nums[curr-1] = numToMove;
	}
}
