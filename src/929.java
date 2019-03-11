import java.util.HashSet;
import java.util.Set;

class UniqueEmailAddresses {
	public int numUniqueEmails(String[] emails) {
		if (emails == null || emails.length == 0) {
			return 0;
		}
		// for every local name, apply the rules and put cleaned full address in hashset
		Set<String> uniqueEmails = new HashSet<>();

		for (String email : emails) {
			String cleaned = getCleanedEmail(email);
			uniqueEmails.add(cleaned);
		}

		return uniqueEmails.size();
	}

	private String getCleanedEmail(String email) {
		String[] parts = email.split("[@]");
		String local = parts[0];

		if (local.contains("+")) {
			local = local.substring(0, local.indexOf('+'));
		}

		return local.replaceAll("[.]", "") + '@' + parts[1];
	}
}
