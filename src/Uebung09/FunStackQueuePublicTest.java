import static org.junit.Assert.*;
import org.junit.*;
import java.lang.reflect.*;

public class FunStackQueuePublicTest {

	// -------------------- Intestines --------------------
	@Test(timeout = 555)
	public void pubTest_Stack_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Stack.class;
			assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
			assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Klassen.", 0, clazz.getDeclaredClasses().length);
			assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", Object.class, clazz.getSuperclass());
			assertEquals("Hat falsche Anzahl an Attributen.", 2, clazz.getDeclaredFields().length);
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				assertTrue(field + " - Feld: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(field.getModifiers()));
				assertFalse(field + " - Feld: Ist kein Instanzattribut", Modifier.isStatic(field.getModifiers()));
			}
			assertTrue("Felder haben falschen Typ: eines muss Stack sein.", fields[0].getType().equals(Stack.class) || fields[1].getType().equals(Stack.class));
			assertTrue("Felder haben falschen Typ: eines muss generisch (wg. type erasure dann Object) sein.", fields[0].getType().equals(Object.class) || fields[1].getType().equals(Object.class));
			assertEquals(clazz.getName() + "hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
				assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(constructor.getModifiers()));
			}
			assertEquals(clazz.getName() + "hat falsche Anzahl an Methoden.", 6, clazz.getDeclaredMethods().length);
			for (String methodName : new String[] { "create", "push", "pop", "top", "isEmpty", "reverse" }) {
				Method method;
				if (methodName.equals("create")) {
					method = clazz.getDeclaredMethod(methodName);
				} else if (methodName.equals("push")) {
					method = clazz.getDeclaredMethod(methodName, Stack.class, Object.class); // generisch => nur wg. type erasure Object!
				} else {
					method = clazz.getDeclaredMethod(methodName, Stack.class);
				}
				assertTrue(methodName + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertTrue(methodName + " - Methode: Ist keine Klassenmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(methodName + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				if (methodName.equals("top")) {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht generisch (wg. type erasure dann >Object<)", Object.class, method.getReturnType());
				} else if (methodName.equals("isEmpty")) {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht >boolean<", boolean.class, method.getReturnType());
				} else {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht >Stack<", Stack.class, method.getReturnType());
				}
			}
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	@Test(timeout = 555)
	public void pubTest_Queue_intestines_THIS_TEST_IS_VERY_IMPORTANT_IF_IT_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		try {
			Class<?> clazz = Queue.class;
			assertFalse(clazz.getName() + " ist faelschlicherweise eine Annotation.", clazz.isAnnotation());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Enum.", clazz.isEnum());
			assertFalse(clazz.getName() + " ist faelschlicherweise ein Interface.", clazz.isInterface());
			assertFalse(clazz.getName() + " ist faelschlicherweise >abstract<", Modifier.isAbstract(clazz.getModifiers()));
			assertTrue(clazz.getName() + " ist faelschlicherweise nicht >public<", Modifier.isPublic(clazz.getModifiers()));
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Annotationen.", 0, clazz.getDeclaredAnnotations().length);
			assertEquals(clazz.getName() + " hat faelschlicherweise innere Klassen.", 0, clazz.getDeclaredClasses().length);
			assertEquals(clazz.getName() + " darf keine Schnittstelle implementieren.", 0, clazz.getInterfaces().length);
			assertSame(clazz.getName() + " ist nicht von der richtigen Superklasse abgeleitet.", Object.class, clazz.getSuperclass());
			assertEquals(clazz.getName() + "hat falsche Anzahl an Attributen.", 1, clazz.getDeclaredFields().length);
			for (Field field : clazz.getDeclaredFields()) {
				assertTrue(field + " - Feld: Sichtbarkeit ist nicht >private<", Modifier.isPrivate(field.getModifiers()));
				assertFalse(field + " - Feld: Ist kein Instanzattribut", Modifier.isStatic(field.getModifiers()));
				assertSame(field + " - Feld: hat falschen Typ.", Stack.class, field.getType());
			}
			assertEquals(clazz.getName() + "hat falsche Anzahl an Konstruktoren.", 1, clazz.getDeclaredConstructors().length);
			Constructor<?> constructor = clazz.getDeclaredConstructor(); // default cons!
			assertTrue("Konstruktor inkl. -parameter: Sichtbarkeit ist nicht >public<", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(clazz.getName() + "hat falsche Anzahl an Methoden.", 4, clazz.getDeclaredMethods().length);
			for (String methodName : new String[] { "front", "enqueue", "dequeue", "isEmpty" }) {
				Method method;
				if (methodName.equals("enqueue")) {
					method = clazz.getDeclaredMethod(methodName, Object.class); // generisch => nur wg. type erasure Object!
				} else {
					method = clazz.getDeclaredMethod(methodName);
				}
				assertTrue(methodName + " - Methode: Sichtbarkeit ist nicht >public<", Modifier.isPublic(method.getModifiers()));
				assertFalse(methodName + " - Methode: Ist keine Instanzmethode", Modifier.isStatic(method.getModifiers()));
				assertFalse(methodName + " - Methode: Ist faelschlicherweise >abstract<", Modifier.isAbstract(method.getModifiers()));
				if (methodName.equals("front")) {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht generisch (wg. type erasure dann >Object<)", Object.class, method.getReturnType());
				} else if (methodName.equals("isEmpty")) {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht >boolean<", boolean.class, method.getReturnType());
				} else {
					assertEquals(methodName + " - Methode: Rueckgabetyp ist nicht >Queue<", Queue.class, method.getReturnType());
				}
			}
		} catch (NoSuchMethodException e) {
			fail("Konstruktor oder Methoden nicht gefunden oder inkorrekt: " + e.getMessage());
		}
	}

	// -------------------- Stack --------------------
	@Test(timeout = 555)
	public void pubTest_Stack_String() {
		Stack<String> s = Stack.<String> create();
		Stack<String> s_pushAuD = Stack.push(s, "AuD");
		Stack<String> s_pushAuD_pushPFP = Stack.push(s_pushAuD, "PFP");
		Stack<String> s_pushAuD_pushPFP_pop = Stack.pop(s_pushAuD_pushPFP);
		Stack<String> s_pushAuD_pushPFP_pop_pop = Stack.pop(s_pushAuD_pushPFP_pop);
		Stack<String> s_pushAuD_pushJohn = Stack.push(s_pushAuD, "John");
		Stack<String> s_pushAuD_pushJohn_pushDoe = Stack.push(s_pushAuD_pushJohn, "Doe");
		Stack<String> s_pushAuD_pushJohn_pushDoe_pop = Stack.pop(s_pushAuD_pushJohn_pushDoe);
		Stack<String> s_pushAuD_pushJohn_pushDoe_pop_pop = Stack.pop(s_pushAuD_pushJohn_pushDoe_pop);
		Stack<String> s_pushAuD_pushJohn_pushDoe_pop_pop_pop = Stack.pop(s_pushAuD_pushJohn_pushDoe_pop_pop);
		Stack<String> s_pushAuD_pushJohn_pushDoe_reverse = Stack.reverse(s_pushAuD_pushJohn_pushDoe);

		for (int pass = 0; pass <= 3; pass++) {
			assertTrue(Stack.isEmpty(s));
			assertFalse(Stack.isEmpty(s_pushAuD));
			assertFalse(Stack.isEmpty(s_pushAuD_pushPFP));
			assertFalse(Stack.isEmpty(s_pushAuD_pushPFP_pop));
			assertTrue(Stack.isEmpty(s_pushAuD_pushPFP_pop_pop));
			assertFalse(Stack.isEmpty(s_pushAuD_pushJohn));
			assertFalse(Stack.isEmpty(s_pushAuD_pushJohn_pushDoe));
			assertFalse(Stack.isEmpty(s_pushAuD_pushJohn_pushDoe_pop));
			assertFalse(Stack.isEmpty(s_pushAuD_pushJohn_pushDoe_pop_pop));
			assertTrue(Stack.isEmpty(s_pushAuD_pushJohn_pushDoe_pop_pop_pop));
			assertEquals("AuD", Stack.top(s_pushAuD));
			assertEquals("PFP", Stack.top(s_pushAuD_pushPFP));
			assertEquals("AuD", Stack.top(s_pushAuD_pushPFP_pop));
			String john = Stack.top(s_pushAuD_pushJohn);
			assertEquals("John", john);
			assertEquals("Doe", Stack.top(s_pushAuD_pushJohn_pushDoe));
			assertEquals("John", Stack.top(s_pushAuD_pushJohn_pushDoe_pop));
			assertEquals("AuD", Stack.top(s_pushAuD_pushJohn_pushDoe_pop_pop));
			assertEquals("AuD", Stack.top(s_pushAuD_pushJohn_pushDoe_reverse));
			assertEquals("John", Stack.top(Stack.pop(s_pushAuD_pushJohn_pushDoe_reverse)));
			assertEquals("Doe", Stack.top(Stack.pop(Stack.pop(s_pushAuD_pushJohn_pushDoe_reverse))));
		}
	}

	@Test(timeout = 555)
	public void pubTest_Stack_Long() {
		Stack<Long> s = Stack.<Long> create();
		Stack<Long> s_push4711 = Stack.push(s, 4711L);
		Stack<Long> s_push4711_pop = Stack.pop(s_push4711);
		Stack<Long> s_push4711_push666 = Stack.push(s_push4711, 666L);
		Stack<Long> s_push4711_push666_pop = Stack.pop(s_push4711_push666);
		Stack<Long> s_push4711_push666_pop_pop = Stack.pop(Stack.pop(s_push4711_push666));
		Stack<Long> s_push4711_push0815 = Stack.push(s_push4711, 0x815L);
		for (int pass = 0; pass <= 3; pass++) {
			assertFalse(Stack.isEmpty(s_push4711));
			Long l_4711 = Stack.top(s_push4711);
			assertEquals(Long.valueOf(4711L), l_4711);
			assertTrue(Stack.isEmpty(s_push4711_pop));
			assertFalse(Stack.isEmpty(s_push4711_push666));
			assertEquals(Long.valueOf(666L), Stack.top(s_push4711_push666));
			assertFalse(Stack.isEmpty(s_push4711_push666_pop));
			assertEquals(Long.valueOf(4711L), Stack.top(s_push4711_push666_pop));
			assertTrue(Stack.isEmpty(s_push4711_push666_pop_pop));
			assertFalse(Stack.isEmpty(s_push4711_push0815));
			assertFalse(Stack.isEmpty(Stack.pop(s_push4711_push0815)));
			assertTrue(Stack.isEmpty(Stack.pop(Stack.pop(s_push4711_push0815))));
			assertEquals(Long.valueOf(0x815L), Stack.top(s_push4711_push0815));
			assertEquals(Long.valueOf(4711L), Stack.top(Stack.pop(s_push4711_push0815)));
		}
	}

	@Test(timeout = 555)
	public void pubTest_Stack_2_null() {
		for (int pass = 0; pass <= 3; pass++) {
			Stack<String> s = Stack.push(null, "JohnDoe");
			assertFalse("Stack(<null+push+isEmpty>) sollte false sein.", Stack.isEmpty(s));
			assertEquals("Stack(<null+push+top>)", "JohnDoe", Stack.top(s));
			assertNotNull("Stack(<null+push+pop>) darf nicht null sein.", Stack.pop(s));
			assertTrue("Stack(<null+push+pop+isEmpty>) sollte true ergeben.", Stack.isEmpty(Stack.pop(s)));
			assertNull("Stack(<null+push+pop+top>) muss null ergeben.", Stack.top(Stack.pop(s)));
			assertNotNull("Stack(<null+pop>) darf nicht null sein.", Stack.pop(null));
			assertNull("Stack(<null+top>) muss null ergeben.", Stack.top(null));
			assertTrue("Stack.isEmpty(null) sollte true ergeben.", Stack.isEmpty(null));
		}
	}

	// -------------------- Queue --------------------
	@Test(timeout = 555)
	public void pubTest_Queue_String() {
		Queue<String> q0 = new Queue<>();
		Queue<String> q1 = q0.enqueue("AuD");
		Queue<String> q2 = q1.enqueue("PFP");
		Queue<String> q3 = q2.dequeue();
		Queue<String> q4 = q3.dequeue();
		Queue<String> q5 = q1.enqueue("John");
		Queue<String> q6 = q5.enqueue("Doe");
		Queue<String> q7 = q6.dequeue();
		Queue<String> q8 = q7.dequeue();
		Queue<String> q9 = q8.dequeue();
		assertTrue(q0.isEmpty());
		assertFalse(q1.isEmpty());
		assertFalse(q2.isEmpty());
		assertFalse(q3.isEmpty());
		assertTrue(q4.isEmpty());
		assertFalse(q5.isEmpty());
		assertFalse(q6.isEmpty());
		assertFalse(q7.isEmpty());
		assertFalse(q8.isEmpty());
		assertTrue(q9.isEmpty());
		assertEquals("AuD", q1.front());
		assertEquals("AuD", q2.front());
		assertEquals("PFP", q3.front());
		assertEquals("AuD", q5.front());
		assertEquals("AuD", q6.front());
		assertEquals("John", q7.front());
		String doe = q8.front();
		assertEquals("Doe", doe);
	}

	@Test(timeout = 555)
	public void pubTest_Queue_Long() {
		Queue<Long> q0 = new Queue<>();
		Queue<Long> q1 = q0.enqueue(4711L);
		Queue<Long> q1deq1 = q1.dequeue();
		Queue<Long> q2 = q1.enqueue(666L);
		Queue<Long> q2deq1 = q2.dequeue();
		Queue<Long> q2deq2 = q2.dequeue().dequeue();
		Queue<Long> q3 = q1.enqueue(0x815L);
		Long l1 = q1.front();
		assertFalse(q1.isEmpty());
		assertEquals(Long.valueOf(4711L), l1);
		assertTrue(q1deq1.isEmpty());
		assertFalse(q2deq1.isEmpty());
		assertTrue(q2deq2.isEmpty());
		assertFalse(q3.isEmpty());
		assertFalse(q3.dequeue().isEmpty());
		assertTrue(q3.dequeue().dequeue().isEmpty());
		assertEquals(Long.valueOf(4711L), q3.front());
		assertEquals(Long.valueOf(0x815L), q3.dequeue().front());
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