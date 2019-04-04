import java.util.LinkedList;
import java.util.List;

class ZigzagIterator {

	List<List<Integer>> availableLists;
	int nextListIndex;

	public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
		this.availableLists = new LinkedList<>();
		this.nextListIndex = -1;

		if (v1 != null && !v1.isEmpty()) {
			availableLists.add(v1);
			nextListIndex = 0;
		}
		if (v2 != null && !v2.isEmpty()) {
			if (availableLists.isEmpty()) {
				nextListIndex = 0;
			}
			availableLists.add(v2);
		}
	}

	public int next() {
		List<Integer> nextList = availableLists.get(nextListIndex);
		int next = nextList.remove(0);
		if (nextList.isEmpty()) {
			availableLists.remove(nextList);
			nextListIndex = availableLists.isEmpty() ? -1 : nextListIndex % availableLists.size();
		} else {
			nextListIndex = (nextListIndex+1) % availableLists.size();
		}

		return next;
	}

	public boolean hasNext() {
		return nextListIndex > -1;
	}
}
