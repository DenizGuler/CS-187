package filesystem;

import structures.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;


/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {

	private Queue<File> fileQueue;
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
		// TODO 1
		fileQueue = new Queue<>();
		Queue<File> temp = new Queue<>();
		// Queue<File> tempTemp = new Queue<>();
		if (!rootNode.exists()) throw new FileNotFoundException();
		fileQueue.enqueue(rootNode);
		//temp.enqueue(rootNode);
		File[] files = rootNode.listFiles();
		if (files == null) return;
		Arrays.sort(files);
		for (File f : files) {
			temp.enqueue(f);
		}
		while (!temp.isEmpty()) {
			if (temp.peek().isDirectory()){
				File[] dir = temp.peek().listFiles();
				if (dir == null) {
					fileQueue.enqueue(temp.dequeue());
					continue;
				}
				Arrays.sort(dir);
				for (File f : dir) {
					temp.enqueue(f);
				}
			}
				fileQueue.enqueue(temp.dequeue());
		}
//		for (File f : files) {
//			fileQueue.enqueue(f);
//			if (f.isDirectory()){
//				File[] filesTemp = f.listFiles();
//				if (filesTemp == null) continue;
//				Arrays.sort(filesTemp);
//				for (File g : filesTemp) {
//					temp.enqueue(g);
//					if (f.isDirectory()){
//						File[] filesTempTemp = f.listFiles();
//						if (filesTempTemp == null) continue;
//						Arrays.sort(filesTempTemp);
//						for (File h : filesTempTemp)
//							tempTemp.enqueue(h);
//					}
//				}
//			}
//		}
//		while(!temp.isEmpty())
//			fileQueue.enqueue(temp.dequeue());
//		while(!tempTemp.isEmpty())
//			fileQueue.enqueue(tempTemp.dequeue());
	}
	
	@Override
	public boolean hasNext() {
		// TODO 2
		return !fileQueue.isEmpty();
	}

	@Override
	public File next() throws NoSuchElementException {
		// TODO 3
		// if (!hasNext()) throw new NoSuchElementException();
		return fileQueue.dequeue();
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}


}
