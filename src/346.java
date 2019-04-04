class MovingAverageFromDataStream {
	class MovingAverage {

		private int windowEnd;
		private int total;
		private int[] window;
		private int streamed;

		/** Initialize your data structure here. */
		public MovingAverage(int size) {
			this.windowEnd = -1; //index of last element in window
			this.total = 0;
			this.window = new int[size];
			this.streamed = 0;
		}

		public double next(int val) {
			windowEnd = (windowEnd + 1) % window.length;
			int curr = window[windowEnd];
			total = total - curr + val;
			window[windowEnd] = val;

			//don't forget to account for the case when we haven't yet filled up the window!!!
			return (double)total / Math.min(++streamed, window.length);
		}
	}

	/**
	 * Your MovingAverage object will be instantiated and called as such:
	 * MovingAverage obj = new MovingAverage(size);
	 * double param_1 = obj.next(val);
	 */
}
