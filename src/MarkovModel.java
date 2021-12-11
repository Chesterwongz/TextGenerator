import java.util.*;

/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();

	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;

	private HashMap<String, HashMap<Character, Integer>> model;
	private HashMap<String, Integer> kgramFreq;

	private final int order;
	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.model = new HashMap<>();
		this.kgramFreq = new HashMap<>();
		this.order = order;
		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model
		int len = text.length();
		for (int i = order; i < len; i++) {
			String kgram = text.substring(i - order, i);
			Character curr = text.charAt(i);
			HashMap<Character, Integer> kgramValue;
			if (!model.containsKey(kgram)) { // Insert kgram if not already inside
				kgramValue = new HashMap<>();
				kgramValue.put(curr, 1);
				model.put(kgram, kgramValue);
				kgramFreq.put(kgram, 1);
			} else {
				kgramValue = model.get(kgram);
				if (!kgramValue.containsKey(curr)) { // Insert char if not already inside
					kgramValue.put(curr, 1);
				} else {
					kgramValue.replace(curr, kgramValue.get(curr) + 1);
				}
				kgramFreq.replace(kgram, kgramFreq.get(kgram) + 1); // increment freq if already inside
			}
		}
	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		return kgramFreq.getOrDefault(kgram, 0);
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		if (kgram.length() != order || !model.containsKey(kgram)) return 0;
		// Returns value of c, or 0 if c is null
		return model.get(kgram).getOrDefault(c, 0);
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		char nextChar = NOCHARACTER;
		if (!model.containsKey(kgram)) return nextChar;

		int rand = generator.nextInt(getFrequency(kgram));
		HashMap<Character, Integer> acsiiMap = model.get(kgram);
		for (int i = 0; i < 256; i++) {
			rand -= acsiiMap.getOrDefault((char) i, 0);
			if (rand < 0) {
				nextChar = (char) i;
				break;
			}
		}
		return nextChar;
	}
}
