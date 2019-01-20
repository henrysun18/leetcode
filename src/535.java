import java.util.ArrayList;
import java.util.List;

/**
 * Created by Henry in 2017.
 * Problem 535
 */
class EncodeAndDecodeTinyURL {

	List<String> savedUrls = new ArrayList<String>();

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		savedUrls.add(longUrl);
		return "http://tinyurl.com/" + savedUrls.size();
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		return savedUrls.get(Integer.parseInt(shortUrl.substring(19)) - 1);
	}
}

// Your EncodeAndDecodeTinyURL object will be instantiated and called as such:
// EncodeAndDecodeTinyURL codec = new EncodeAndDecodeTinyURL();
// codec.decode(codec.encode(url));
