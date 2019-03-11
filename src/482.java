class LicenseKeyFormatting {
	public String licenseKeyFormatting(String S, int K) {
		String key = S.replaceAll("[-]", "");
		int firstGroupSize = key.length() % K;
		if (firstGroupSize == 0) {
			firstGroupSize = K;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < key.length(); i++) {
			if (i >= firstGroupSize && (i - firstGroupSize) % K == 0) {
				sb.append('-');
			}
			sb.append(key.charAt(i));
		}

		return sb.toString().toUpperCase();
	}
}
