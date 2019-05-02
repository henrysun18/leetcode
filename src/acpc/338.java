package acpc;

class CountingBits {
	public int[] countBits(int num) {
		int[] bits = new int[num+1]; //0 to num inclusive
		//for each num, the number of bits = most significant bit + bits in num-(MSB)

		for (int i = 1; i <= num; i++) {
			// add 1 if we find that we shifted
			bits[i] = bits[i >> 1] + (i & 1); //111100 = 011110 + 0; 111111 = 011111 + 1;

			//previously the logic was 111100 = 1 + 011100; 111111 = 1 + 011111
			//both cases work since they both depend on earlier solutions!
			//this current method is easier to implement since it involves just a bitshift!
		}

		return bits;
	}

	/*
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
	}*/
}
