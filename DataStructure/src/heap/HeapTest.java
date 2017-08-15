package heap;

import org.junit.Test;

public class HeapTest {
	@Test
	public void test() {
		// 父节点为x，则左孩子：2x＋1，右孩子：2x＋2
		Heap heap = new Heap(50);
		heap.add(61);
		heap.add(28);
		heap.add(81);
		heap.add(43);
		heap.add(36);
		heap.add(47);
		heap.add(83);
		heap.add(5);

		heap.createHeap();

		for (int i = 0; i < heap.size; i++) {
			System.out.print(heap.elements[i] + " ");
		}
	}
}
