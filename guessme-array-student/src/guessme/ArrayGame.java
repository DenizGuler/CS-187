package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;

	// prior guesses, eliminated candidates etc.
	private int[] guesses = new int[0];
	private boolean[] isEliminated = new boolean[9000];
	private boolean fourMatched;

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		this.guess = 1000;
		this.isEliminated[0] = true;
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		this.guess = 1000;
		this.guesses = new int[0];
		this.isEliminated = new boolean[9000];
		fourMatched = false;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		for (int i:guesses) {
			if (i==n)
				return true;
		}
		return false;
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		return guesses.length;
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
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
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
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		if (fourMatched)
			return true;
		for (boolean b : isEliminated){
			if (!b)
				return false;
		}
		return true;
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		int[] temp = new int[guesses.length];
		System.arraycopy(guesses, 0, temp, 0,guesses.length);
		//for (int i = 0; i < guesses.length; i++)
		//	temp[i] = guesses[i];
		guesses = new int[guesses.length+1];
		System.arraycopy(temp, 0, guesses, 0,temp.length);
		//for (int i = 0; i < guesses.length-1; i++)
		//	guesses[i] = temp[i];
		guesses[guesses.length-1] = guess;

		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		//game over?
		if (nmatches >= 4) {
			fourMatched = true;
			isOver();
			return true;
		}
		//eliminate
		for (int i = guess-1000; i < isEliminated.length; i++){
			if (numMatches(guess, i+1000) != nmatches)
				isEliminated[i] = true;
		}
		//guess
		for (int i = guess-1000; i < isEliminated.length; i++){
			if (!isEliminated[i] && !isPriorGuess(i+1000)) {
				guess = i + 1000;
				isOver();
				return true;
			}
		}

		//while (numMatches(guess, a) != nmatches) {
		//	isEliminated[a - 1000] = true;
		//	a++;
		//	//System.out.printf("tested number: %d \nMatches: %d\n\n", a, numMatches(guess, a));
		//}
		//guess = a;
		isOver();
		return false;
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		return guesses.length==0?null:guesses;
	}
}
