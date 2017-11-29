
public class PriorityQueueTest {
	
	
	@Test
	public void testTop(){
		PriorityQueue test = new PriorityQueue();
		test.addItem("I", 1);
		test.addItem(1, 2);
		test.addItem(2, 3);
		test.addItem(3, 4);
		test.addItem("E", 4);
		Assert.assertEquals("nicht genuegend Eintraege", 5, test.length());
		
	}
}
