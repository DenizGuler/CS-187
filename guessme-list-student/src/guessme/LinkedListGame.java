package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary
	private LLIntegerNode priorGuesses, gTail;
	private LLIntegerNode candidates, cTail;
	private boolean isOver;

	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/

	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/

	// LinkedListGame constructor method
	public LinkedListGame() {
		//LLIntegerNode node = new LLIntegerNode(1000);
		//candidates = node;
		for (int i = 1000; i <= 9999; i++) {
			//LLIntegerNode node = new LLIntegerNode(i);
			addCandidates(i);
		}
		// TODO
	}

	// Resets data members and game state so we can play again
	public void reset() {
		isOver = false;
		priorGuesses = null;
		gTail = null;
		candidates = null;
		cTail = null;
		for (int i = 1000; i <= 9999; i++) {
			//LLIntegerNode node = new LLIntegerNode(i);
			addCandidates(i);
		}
		// TODO
	}

	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		// TODO
		LLIntegerNode node = priorGuesses;
		while (node != null){
			if (node.getInfo() == n)
				return true;
			node = node.getLink();
		}
		return false;

	}

	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		int result = 0;
		LLIntegerNode node = priorGuesses;
		if (candidates == null) return  result;
		while (node != null){
			result++;
			node = node.getLink();
		}
		return result;
	}

	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 *
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		// TODO
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result += a % 10 == b % 10 ? 1 : 0;
			a /= 10;
			b /= 10;
		}
		return result;
	}

	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		// TODO
		if (isOver)
			return true;
		return candidates == null;
	}

	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		int guess = candidates.getInfo();
		this.addGuess(guess);

		return guess;
	}

	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 *
	 * Returns true if the update has no error; false if no candidate
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		// TODO
		if (nmatches == 4) {
			isOver = true;
			return true;
		}
		int guess = 0;
		LLIntegerNode node = priorGuesses;
		while (node != null) {
			if (node.getLink() == null)
				guess = node.getInfo();
			node = node.getLink();
		}

		//update candidates
		//for (int i = 9999; i >= 1000; i--) {
		//	//LLIntegerNode node = new LLIntegerNode(i);
		//	if (numMatches(i, guess) == nmatches  && !isPriorGuess(i))
		//		insertCandidates(i);
		//}
		node = candidates;
		candidates = null;
		while (node != null) {
			int i = node.getInfo();
			if (numMatches(i, guess) == nmatches && !isPriorGuess(i))
				addCandidates(i);
			node = node.getLink();
		}
		return candidates != null;
	}

	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		return priorGuesses;
	}

	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		if (priorGuesses == null) { return ""; }
		String result = "" + priorGuesses.getInfo();
		LLIntegerNode node = priorGuesses.getLink();
		//if (node == null) { return result; }
		while (node != null) {
			result += ", " + node.getInfo();
			node = node.getLink();
		}
		return result;
	}

	private void insertCandidates(int info) {
		LLIntegerNode node = new LLIntegerNode(info);
		node.setLink(candidates);
		candidates = node;
	}

	private void addCandidates(int info) {
		LLIntegerNode node = new LLIntegerNode(info);
		if (candidates == null)
			candidates = node;
		else
			cTail.setLink(node);
		cTail = node;
	}

	private void addGuess(int info) {
		LLIntegerNode node = new LLIntegerNode(info);
		if (priorGuesses == null)
			priorGuesses = node;
		else
			gTail.setLink(node);
		gTail = node;
	}


	private void insertGuess(int info) {
		LLIntegerNode node = new LLIntegerNode(info);
		node.setLink(priorGuesses);
		priorGuesses = node;
	}

}
