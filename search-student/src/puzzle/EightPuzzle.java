package puzzle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import search.SearchProblem;
import search.Solver;

/**
 * A class to represent an instance of the eight-puzzle.
 * 
 * The spaces in an 8-puzzle are indexed as follows:
 * 
 * 0 | 1 | 2
 * --+---+---
 * 3 | 4 | 5
 * --+---+---
 * 6 | 7 | 8
 * 
 * The puzzle contains the eight numbers 1-8, and an empty space.
 * If we represent the empty space as 0, then the puzzle is solved
 * when the values in the puzzle are as follows:
 * 
 * 1 | 2 | 3
 * --+---+---
 * 4 | 5 | 6
 * --+---+---
 * 7 | 8 | 0
 * 
 * That is, when the space at index 0 contains value 1, the space 
 * at index 1 contains value 2, and so on.
 * 
 * From any given state, you can swap the empty space with a space 
 * adjacent to it (that is, above, below, left, or right of it,
 * without wrapping around).
 * 
 * For example, if the empty space is at index 2, you may swap
 * it with the value at index 1 or 5, but not any other index.
 * 
 * Only half of all possible puzzle states are solvable! See:
 * https://en.wikipedia.org/wiki/15_puzzle
 * for details.
 * 

 * @author liberato
 *
 */
public class EightPuzzle implements SearchProblem<List<Integer>> {

	/**
	 * Creates a new instance of the 8 puzzle with the given starting values.
	 * 
	 * The values are indexed as described above, and should contain exactly the
	 * nine integers from 0 to 8.
	 * 
	 * @param startingValues
	 *            the starting values, 0 -- 8
	 * @throws IllegalArgumentException
	 *             if startingValues is invalid
	 */

	private final List<Integer> startingValues;
	private final List<Integer> goal = Arrays.asList(1, 2, 3,
													 4, 5, 6,
													 7, 8, 0);
	private List<List<Integer>> successors;


	public EightPuzzle(List<Integer> startingValues) throws IllegalArgumentException {
		if (!startingValues.containsAll(goal) || startingValues.size() > 9) throw new IllegalArgumentException();
		this.startingValues = startingValues;
	}

	@Override
	public List<Integer> getInitialState() {
		return startingValues;
	}

	@Override
	public List<List<Integer>> getSuccessors(List<Integer> currentState) {
		successors = new ArrayList<List<Integer>>();
		int blankIndex = currentState.indexOf(0);
		int left  = Arrays.asList(1, 2, 4, 5, 7, 8).contains(blankIndex)? blankIndex - 1 : -1;
		int right = Arrays.asList(0, 1, 3, 4, 6, 7).contains(blankIndex)? blankIndex + 1 : -1;
		int above = Arrays.asList(3, 4, 5, 6, 7, 8).contains(blankIndex)? blankIndex - 3 : -1;
		int below = Arrays.asList(0, 1, 2, 3, 4, 5).contains(blankIndex)? blankIndex + 3 : -1;
		addToSuccessors(left , currentState);
		addToSuccessors(right, currentState);
		addToSuccessors(above, currentState);
		addToSuccessors(below, currentState);
		return successors;
	}

	private void addToSuccessors(int index, List<Integer> state) {
		if (index == -1) return;
		List<Integer> copy = new ArrayList<Integer>(state);
		copy.set(state.indexOf(0), state.get(index));
		copy.set(index, 0);
		successors.add(copy);
	}

	@Override
	public boolean isGoal(List<Integer> state) {
		return goal.equals(state);
	}

	public static void main(String[] args) {
		EightPuzzle e = new EightPuzzle(Arrays.asList(new Integer[] { 1, 2, 3,
				4, 0, 6, 7, 5, 8 }));

		List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithBFS();
		//List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithIterativeDFS();
		//List<List<Integer>> r = new Solver<List<Integer>>(e).solveWithRecursiveDFS();
		for (List<Integer> l : r) {
			System.out.println(l);
		}
	}
}
