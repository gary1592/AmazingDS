package heap;

import org.junit.Test;

public class PriorityQueueTest {

	@Test
	public void test() {
		PriorityQueue pQueue = new PriorityQueue(50);
		pQueue.add(61);
		pQueue.add(28);
		pQueue.add(81);
		pQueue.add(43);
		pQueue.add(36);
		pQueue.add(47);
		pQueue.add(83);
		pQueue.add(5);

		pQueue.createPriorityQueue();

		pQueue.append(25);

		System.out.println("优先权队列：");
		for (int i = 0; i < pQueue.size; i++) {
			System.out.print(pQueue.elements[i] + " ");
		}
	}
}
