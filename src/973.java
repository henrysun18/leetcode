import java.util.Comparator;
import java.util.PriorityQueue;

class KClosestPointsToOrigin {
	public int[][] kClosest(int[][] points, int K) {
		//can use a heap, comparator is distance
		//keep a max heap, and when we have K points already, see if we need to replace the max element with something smaller
		//runtime O(nlgn), space O(K)

		Comparator<int[]> comparator = (point1, point2) ->
				(point2[0]*point2[0] + point2[1]*point2[1]) - (point1[0]*point1[0] + point1[1]*point1[1]);

		PriorityQueue<int[]> minHeap = new PriorityQueue<>(comparator);

		for (int[] point : points) {
			if (minHeap.size() == K) {
				if (comparator.compare(point, minHeap.peek()) < 0) continue; //ignore point if it's even farther than the farthest we have now
				minHeap.poll();
			}
			minHeap.add(point);
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
