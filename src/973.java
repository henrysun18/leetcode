import java.util.Comparator;
import java.util.PriorityQueue;

class KClosestPointsToOrigin {
	public int[][] kClosest(int[][] points, int K) {
		//can use a heap, comparator is distance
		//keep a max heap, and when we have K points already, see if we need to replace the max element with something smaller
		//runtime O(nlgn), space O(K)

		//negate the comparison get descending order
		Comparator<int[]> comparator = Comparator.comparing(p -> - (p[0]*p[0] + p[1]*p[1]));

		PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);

		for (int[] point : points) {
			minHeap.offer(point);
			if (minHeap.size() > K) {
				minHeap.poll(); //a lot more concise than before
			}
		}

		int[][] res = new int[minHeap.size()][2];
		for (int i = 0; i < res.length; i++) { //NOT i < minHeap.size() since that'll decrease as we poll!!
			res[i] = minHeap.poll();
		}

		return res;
	}

	public static void main(String[] args) {
		KClosestPointsToOrigin sol = new KClosestPointsToOrigin();
		int[][] points = new int[3][2];
		points[0] = new int[]{3,3};
		points[1] = new int[]{5,-1};
		points[2] = new int[]{-2,4};
		sol.kClosest(points, 2);
	}
}
