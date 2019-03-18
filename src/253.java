import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class MeetingRoomsII {
	public int minMeetingRooms(Interval[] intervals) {
		// sort starts and ends into 2 separate arrays
		// logic is to go through all sorted starting times, incrementing rooms needed until there's an end time earlier,
		int[] starts = new int[intervals.length];
		int[] ends = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts[i] = intervals[i].start;
			ends[i] = intervals[i].end;
		}

		Arrays.sort(starts);
		Arrays.sort(ends);

		int rooms = 0;
		int endIndex = 0;
		for (int start : starts) {
			if (start < ends[endIndex]) {
				rooms++;
			} else {
				endIndex++;
			}
		}

		return rooms;
	}

	public int minMeetingRoomsWithoutLookingAtSolution(Interval[] intervals) {
		// greedy approach is for all intervals, take the first available room
		// O(n^2) time since every interval has to check at most all other intervals to find free room?
		//  assuming we store availableRooms as a list of interval lists
		// doesn't exactly work if we don't sort, since [[7,10], [2,4], [2,5], [4,5]] would result in [7,10][2,4], [2,5], [4,5]
		// when the ideal solution is [2,5][7,10], [2,4][4,5]

		// how about we sort intervals by start time asc then end time desc
		// start with [2,5][2,4][4,5][7,10]
		// for each interval, put it in the meeting room with the greatest end time that's still smaller than this start time
		// can use a heap to keep track of room end times so we don't have to look up all rooms to find best one
		Arrays.sort(intervals, new Comparator<Interval>() {
			@Override
			public int compare(Interval a, Interval b) {
				if (a.start < b.start || a.start == b.start && a.end > b.end) {
					return -1;
				} else {
					return 1;
				}
			}
		});

		Arrays.sort(intervals, (a, b) -> a.start - b.start);
		List<Integer> roomEndTimes = new ArrayList<>();
		for (Interval interval : intervals) {
			int roomWithBestEndTime = -1;
			int bestEndTime = -1;
			for (int i = 0; i < roomEndTimes.size(); i++) {
				int endTime = roomEndTimes.get(i);
				if (endTime <= interval.start && endTime > bestEndTime) {
					roomWithBestEndTime = i;
					bestEndTime = endTime;
				}
			}

			if (roomWithBestEndTime >= 0) {
				roomEndTimes.remove(roomWithBestEndTime);
				roomEndTimes.add(roomWithBestEndTime, interval.end);
			} else {
				roomEndTimes.add(interval.end);
			}
		}

		return roomEndTimes.size();
	}
}