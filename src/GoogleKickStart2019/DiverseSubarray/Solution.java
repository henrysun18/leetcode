package GoogleKickStart2019.DiverseSubarray;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
		int numTests = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc


		for (int test = 1; test <= numTests; test++) {
			int numTrinkets = in.nextInt();
			int limit = in.nextInt();
			int[] trinkets = new int[numTrinkets];
			for (int t = 0; t < numTrinkets; t++) {
				trinkets[t] = in.nextInt();
			}
			printSolution(trinkets, limit, test);
		}
	}

	private static void printSolution(int[] trinkets, int limit, int test) {
		//just try all possible combinations of l and r
		int max = 0;
		for (int l = 0; l < trinkets.length; l++) {
			if (trinkets.length - l <= max) break;
			//check how many we can carry if sweeping from l to r inclusive
			for (int r = trinkets.length-1; r >= l; r--) {
				if (r - l < max) break;

				int taken = 0;
				Map<Integer, Integer> brought = new HashMap<>();
				for (int i = l; i <= r; i++) {
					int trinket = trinkets[i];
					brought.putIfAbsent(trinket, 0);

					if (brought.get(trinket) == limit) {
						taken -= limit;
					} else if (brought.get(trinket) < limit) {
						taken++;
					}
					brought.put(trinket, brought.get(trinket) + 1);
				}
				if (taken > max) {
					max = taken;
				}
			}
		}
		System.out.println("Case #" + test + ": " + max);
	}
/*
	private static void printSolution(int[] trinkets, int limit, int test) {
		//have a two pointers approach
		//always try to increment left pointer
		//if it's safe to do so, then increment left pointer
		//otherwise, update state and increment right pointer
		int max = 0;
		int curr = 0;
		Map<Integer, Integer> brought = new HashMap<>();
		int l = 0;
		int r = 0;
		while (r < trinkets.length || l < trinkets.length) {
			//try to increment l, but only if it doesn't hurt our curr sum
			int left = trinkets[l];
			if (brought.containsKey(left) && brought.get(left) > limit) {
				l++;
				//update state to correspond to this
				brought.put(left, brought.get(left) - 1);
				if (brought.get(left) == limit) {
					curr += limit;
				}
			} else if (r < trinkets.length){
				int right = trinkets[r];
				brought.putIfAbsent(right, 0);
				r++;
				//update state now that we brought another trinket = right
				if (brought.get(right) < limit) {
					curr++;
				}
				if (brought.get(right) == limit) {
					curr -= limit;
				}
				brought.put(right, brought.get(right) + 1);
			} else {
				l++;
				//update state to correspond to this
				brought.put(left, brought.get(left) - 1);
				if (brought.get(left) < limit) {
					curr--;
				}
				if (brought.get(left) == limit) {
					curr += limit;
				}
			}
			if (curr > max) {
				max = curr;
			}
		}

		System.out.println("Case #" + test + ": " + max);
	}*/
}

