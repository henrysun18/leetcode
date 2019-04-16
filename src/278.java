class FirstBadVersion {
	public int firstBadVersion(int n) {
		//binary search until we only have one false isBadVersion left

		int left = 1; //inclusive
		int right = n; //inclusive also
		int mid = (left + right) / 2;

		while (left < right) {
			if (isBadVersion(mid)) {
				right = mid-1;
			} else {
				left = mid;
			}
			mid = left/2 + right/2 + 1;  //make sure to divide first!! very important for binary search
		}

		//right is now either at the last good version or the first bad version
		if (isBadVersion(right)) {
			return right;
		}
		return right+1;
	}

	private boolean isBadVersion(int blackhole) {
		return true;
	}
}
