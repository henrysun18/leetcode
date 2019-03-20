import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopKFrequentElements {
	public List<Integer> topKFrequent(int[] nums, int k) {
		// MUST BE better than O(nlogn), thus we can't simply use a TreeMap to record sorted frequencies
		// then we need dp to record the k most frequent elements??
		// why not just use a heap with at most k elements? NO, we might lose info if 1,2,3,4,1,1,1 and k = 3

		//how about we populate hashmap of all frequencies first O(n)
		//then how do we get top k in under nlogn???
		//(after looking at related topics for clue I THOUGHT I CLICKED DP QUESTION)
		//how about inserting all frequencies into a heap? the heap should be of size k so time to finish inserting is klogk

		Map<Integer, Integer> freqs = new HashMap<>();
		for (int num : nums) {
			freqs.put(num, freqs.getOrDefault(num, 0)+1);
		}

		List<Integer>[] freqBuckets = new List[nums.length+1];

		for (Integer num : freqs.keySet()) {
			int freq = freqs.get(num);

			if (freqBuckets[freq] == null) {
				freqBuckets[freq] = new ArrayList<>();
			}
			freqBuckets[freq].add(num);
		}

		List<Integer> res = new ArrayList<>();
		for (int i = freqBuckets.length-1; res.size() < k; i--) {
			if (freqBuckets[i] != null) {
				res.addAll(freqBuckets[i]);
			}
		}
		return res;
	}
}
