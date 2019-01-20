import java.util.PriorityQueue;

/**
 * Created by Henry on 10/16/2018.
 */
class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int maxArea = 0;
		int left = 0, right = height.length - 1;

		while (left < right) {
			int distance = right - left;
			int area = Math.min(height[left], height[right]) * distance;
			if (area > maxArea) {
				maxArea = area;
			}
			if (height[left] < height[right]) {
				left++;
			} else {
				right--;
			}
		}
		PriorityQueue<String> namePriorityQueue = new PriorityQueue<>((x,y)->x.charAt(0) - y.length());
		char[] chars = new char[20];
		String str = "asd";
		for (int i = 0; ; i++)
		return maxArea;
	}

	public static int largestProductOf3(int[] arr) {
		int min1 = 0, min2 = 0, max3 = 0, max2 = 0, max1 = 0;

		for (int i = 0; i < arr.length; i++) {
			int curr = arr[i];

			if (curr <= min1) {
				min2 = min1;
				min1 = curr;
			} else if (curr < min2) {
				min2 = curr;
			}
			if (curr >= max1) {
				max3 = max2;
				max2 = max1;
				max1 = curr;
			} else if (curr >= max2) {
				max3 = max2;
				max2 = curr;
			} else if (curr > max3) {
				max3 = curr;
			}
		}
		return Math.max(min1 * min2 * max1, max1 * max2 * max3);
	}

	public static void main(String[]args) {
		System.out.println(largestProductOf3(new int[]{0, 2, 3, 4, 2, 5, 5, 22, 1, -11, -12}));
	}
}
