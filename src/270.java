class ClosestBinarySearchTreeValue {
	public int closestValue(TreeNode root, double target) {
		TreeNode curr = root;
		double smallestDiff = Math.abs(root.val - target);
		int closest = root.val;

		while (curr != null) {
			double currDiff = Math.abs(curr.val - target); //don't forget to abs this diff too
			if (currDiff < smallestDiff) {
				closest = curr.val;
				smallestDiff = currDiff; //don't forget to update smallestDiff
			}

			if (curr.val > target) {
				curr = curr.left;
			} else {
				curr = curr.right;
			}
		}

		return closest;
	}
}
