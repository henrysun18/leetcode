package acpc;

class CountingBits {
	public int[] countBits(int num) {
		int[] bits = new int[num+1]; //0 to num inclusive
		//for each num, the number of bits = most significant bit + bits in num-(MSB)

		int MSB = 1;
		for (int i = 1; i <= num; i++) {
			if (i == MSB*2) {
				bits[i] = 1;
				MSB *= 2;
			} else {
				bits[i] = 1 + bits[i - MSB];
			}
		}

		return bits;
	}
}
