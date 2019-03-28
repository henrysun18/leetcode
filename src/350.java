import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IntersectionOfTwoArraysII {
	public int[] intersect(int[] nums1, int[] nums2) {
		Map<Integer, Integer> intCounts = new HashMap<>();

		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return new int[0];

		int[] smallerArray = nums1.length > nums2.length ? nums2 : nums1;
		int[] largerArray = smallerArray == nums1 ? nums2 : nums1;

		for (int i : smallerArray) {
			intCounts.put(i, intCounts.getOrDefault(i, 0) + 1);
		}

		List<Integer> intersects = new ArrayList<>();
		for (int i : largerArray) {
			Integer count = intCounts.get(i);
			if (count != null && count > 0) {
				intersects.add(i);
				intCounts.put(i, count-1);
			}
			if (intersects.size() == smallerArray.length) break;
		}

		int[] res = new int[intersects.size()];
		for (int i = 0; i < intersects.size(); i++) {
			res[i] = intersects.get(i);
		}
		return res;
	}
}
