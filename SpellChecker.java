
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String head(String str) {
		return str.substring(0, 1);
	}

	public static String tail(String str) {
		if (str.length() == 1) {
			return "";
		}

		return str.substring(1, str.length());
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();

		if (word2.length() == 0) {
			return word1.length();
		}
		else if (word1.length() == 0) {
			return word2.length();
		}
		else if (head(word1).equals(head(word2)) == true) {
			return levenshtein(tail(word1), tail(word2));
		}
		else {
			// Take the minimum of these 3 values
			int min1 = levenshtein(tail(word1), word2);
			int min2 = levenshtein(word1, tail(word2));
			int min3 = levenshtein(tail(word1), tail(word2));
			int min = Math.min(min1, Math.min(min2, min3));

			return (1 + min);
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		// Your code here
		for (int i = 0; i < dictionary.length; i++) {
			dictionary[i] = in.readString();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		// Your code goes here
		String minWord = "";
		for (var i = 0; i < dictionary.length; i++) {
			if (levenshtein(word, dictionary[i]) <= levenshtein(word, minWord)) {
				if (levenshtein(word, minWord) > threshold) {
					minWord = dictionary[i];
				}
			}
			System.out.println(dictionary[i] + " " + minWord + " " + levenshtein(word, minWord));
		}

		if (levenshtein(word, minWord) > threshold) {
			return word;
		}
		return minWord;
	}
	//
}
