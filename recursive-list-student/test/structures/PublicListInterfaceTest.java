package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;
	private String[] strings = new String[]{"0", "1", "2", "3", "4", "5", "6", "7"};

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}

	@Test
	public void testInsertLastRemoveLastSizeAndIsEmpty() {
		for (int i = 0; i < 8; i++) {
			list.insertLast(strings[i]);
			assertEquals(strings[i], list.getLast());
		}
		for (int i = 7; i >= 0; i--) {
			assertEquals(strings[i], list.removeLast());
			assertEquals(i, list.size());
		}
		assertTrue(list.isEmpty());
	}

	@Test
	public void testInsertsRemoveAndIndexOf() {
		for (int i = 0; i < 8; i++) {
			list.insertLast(strings[i]);
		}
		int ins = 2;
		list.insertAt(ins, "ins");
		assertEquals(ins, list.indexOf("ins"));
		assertEquals(9, list.size());
		assertEquals("ins", list.get(ins));
		assertTrue(list.remove("ins"));
		assertEquals(8, list.size());
		assertEquals(ins, list.indexOf(strings[ins]));
	}

	@Test
	public void testInsertAtIsEmptySizeAndGetAt() {
//		list.insertAt(0, "only");
//		assertEquals("only", list.getFirst());
//		assertEquals("only", list.getLast());
//		assertEquals("only", list.get(0));
//		assertEquals(1, list.size());
//		assertFalse(list.isEmpty());
		list = new RecursiveList<String>();
		for (int i = 0; i < 8; i++) {
			list.insertLast(strings[i]);
		}

		list.insertAt(5, "ins");
		assertEquals("ins", list.get(5));
		assertEquals("5", list.get(6));
		assertEquals("7", list.get(8));
		assertEquals(9, list.size());
		assertEquals("ins", list.removeAt(5));
		assertEquals(8, list.size());
		for (int i = 0; i < 8; i++) {
			assertEquals(Integer.toString(i), list.removeAt(0));
		}
		assertTrue(list.isEmpty());
		//assertEquals(ins, list.indexOf(strings[ins]));
	}

	@Test
	public void lastElementRemoveAt() {
		list.insertLast("last");
		list.insertFirst("temp1");
		list.insertLast("temp2");
		list.removeAt(0);
		list.removeAt(1);
		assertEquals("last", list.removeAt(0));
		assertTrue(list.isEmpty());
	}

}
