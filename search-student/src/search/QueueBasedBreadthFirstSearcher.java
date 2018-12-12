package search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * An implementation of a Searcher that performs an iterative search,
 * storing the list of next states in a Queue. This results in a
 * breadth-first search.
 * 
 */
public class QueueBasedBreadthFirstSearcher<T> extends Searcher<T> {

	private final List<T> states;
	private final List<T> predecessors;

	public QueueBasedBreadthFirstSearcher(SearchProblem<T> searchProblem) {
		super(searchProblem);
		states = new ArrayList<T>();
		predecessors = new ArrayList<T>();
	}

	@Override
	public List<T> findSolution() {
		Queue<T> queue = new LinkedList<>();
		ArrayList<T> path = new ArrayList<>();
		final T initState = searchProblem.getInitialState();
		visited.add(initState);
		queue.add(initState);
		//states.add(initState);
		predecessors.add(initState);
		T t;
		while (!queue.isEmpty()) {
			T curr = queue.remove();
			if (searchProblem.isGoal(curr)) {
				path.add(curr);
				while (!curr.equals(initState)) {
					T temp = predecessors.get(states.indexOf(curr));
					path.add(temp);
					curr = temp;
				}
				Collections.reverse(path);
				break;
			}
			while ((t = getNextUnvisitedNeighbor(curr)) != null) {
				visited.add(t);
				queue.add(t);
			}
		}
		return path;
	}

	private T getNextUnvisitedNeighbor(T t) {
		for (T node : searchProblem.getSuccessors(t)) {
			if (!visited.contains(node)) {
				if (!states.contains(node)) {
					states.add(node);
					predecessors.add(node);
				}
				predecessors.set(states.indexOf(node), t);
				return node;
			}
		}
		return null;
	}

}
