package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Stack. This results in a
 * depth-first search.
 * 
 */
public class StackBasedDepthFirstSearcher<T> extends Searcher<T> {
	
	public StackBasedDepthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
	}

	@Override
	public List<T> findSolution() {
		Stack<T> stack = new Stack<T>();
		stack.push(searchProblem.getInitialState());
		visited.add(searchProblem.getInitialState());
		T t = null;
		while (!stack.isEmpty()) {
			t = getNextUnvisitedNeighbor(stack.peek());
			if (searchProblem.isGoal(t)){
				stack.push(t);
				return stack;
			}
			if (t == null) stack.pop();
			else {
				visited.add(t);
				stack.push(t);
			}
		}
		return stack;
	}

	private T getNextUnvisitedNeighbor(T t) {
		for (T node : searchProblem.getSuccessors(t)) {
			if (!visited.contains(node)) {
				return node;
			}
		}
		return null;
	}
}


