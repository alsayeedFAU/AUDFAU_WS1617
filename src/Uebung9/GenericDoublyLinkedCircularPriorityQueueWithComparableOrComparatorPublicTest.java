import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparatorPublicTest {
	// ========== define some COMPs... ==========
	private static final Comparator<Integer> COMP_INTEGER = new Comparator<Integer>() {
		@Override
		public int compare(Integer o1, Integer o2) {
			return o1 - o2;
		}
	};
	private static final Comparator<String> COMP_STRING_LEX = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
	};
	private static final Comparator<String> COMP_STRING_ANTI_LEX = new Comparator<String>() {
		@Override
		public int compare(String o1, String o2) {
			return o2.compareTo(o1);
		}
	};
	private static final Comparator<?> COMP_MAGIC = new Comparator<GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<?>>() {
		@Override
		public int compare(GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<?> o1, GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<?> o2) {
			return 0;
		}
	};
	private static final Comparator<Boolean> COMP_BOOL_ONE = new Comparator<Boolean>() {
		@Override
		public int compare(Boolean o1, Boolean o2) {
			return 1;
		}
	};

	// ========== check intestines... ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION! BITTE KEINE REFLECTION IN DER EIGENEN ABGABE VERWENDEN! => "0 Punkte"!
	@Test(timeout = 666)
	public void pubTest_intestines__THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator.class;
		Class<?> superClazz = AbstractGenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator.class;
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal super class.", superClazz, clazz.getSuperclass());
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal interfaces.", 0, clazz.getInterfaces().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner annotations.", 0, clazz.getDeclaredAnnotations().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal inner classes.", 0, clazz.getDeclaredClasses().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of generic types.", 1, clazz.getTypeParameters().length);
		assertEquals("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal number of bounds for your generic type.", 1, clazz.getTypeParameters()[0].getBounds().length);
		assertSame("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal bounds for your generic type.", Object.class, clazz.getTypeParameters()[0].getBounds()[0]);
		for (Field field : clazz.getDeclaredFields()) {
			if (!(field.getName().equals("$assertionsDisabled") && field.getType() == boolean.class)) { // damn java hack
				fail("WARNING: YOU WILL END UP WITH 0 POINTS! Found illegal attribute (field: " + field + ").");
			}
		}
	}

	// ========== PUBLIC MAIN TEST ==========
	@Test(timeout = 666)
	public void pubTest_insert() {
		GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<Integer> q = new GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<>(COMP_INTEGER);
		q.insert(4711);
		DoublyLinkedListNode<Integer> head = q.head;
		assertNotNull("Failed.", head);
		assertNotNull("Failed.", head.get());
		assertEquals("Failed.", 4711, head.get().intValue());
		assertSame("Failed.", head, head.getSuccessor());
		assertSame("Failed.", head, head.getPredecessor());
	}

	@Test(timeout = 666)
	public void pubTest_removeMostSignificant() {
		GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<String> q = new GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<>(COMP_STRING_LEX);
		q.insert("John");
		q.insert("Doe");
		DoublyLinkedListNode<String> head = q.head;
		assertNotNull("Failed.", head);
		assertEquals("Failed.", "John", head.get());
		assertEquals("Failed.", "John", q.removeMostSignificant());
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_removeLeastSignificant() {
		GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<?> q = new GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<>(COMP_MAGIC);
		q.insert(null);
		q.removeLeastSignificant();
		q.removeLeastSignificant();
		fail("Failed.");
	}

	@Test(timeout = 666)
	public void pubTest_removeMostSignificantWithComparator() {
		GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<String> q = new GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<>(COMP_STRING_LEX);
		q.insert("John");
		q.insert("Doe");
		assertEquals("Failed.", "Doe", q.removeMostSignificant(COMP_STRING_ANTI_LEX));
	}

	@Test(timeout = 666, expected = NoSuchElementException.class)
	public void pubTest_removeLeastSignificantWithComparator() {
		GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<Boolean> q = new GenericDoublyLinkedCircularPriorityQueueWithComparableOrComparator<>(COMP_BOOL_ONE);
		q.insert(null);
		q.removeLeastSignificant(COMP_BOOL_ONE);
		q.removeLeastSignificant(COMP_BOOL_ONE);
		fail("Failed.");
	}

	// ========== main ==========
	// nothing to do ;) - please do nothing here:
	public static void main(String args[]) {
		// to compile on command line: javac -cp .:/usr/share/java/junit4.jar *.java
		// to run on command line: java -cp .:/usr/share/java/junit4.jar <nameOfThisClass>

		// starts junit runner - don't try to understand!
		org.junit.runner.JUnitCore.main(new Object() {
		}.getClass().getEnclosingClass().getSimpleName());
	}
}