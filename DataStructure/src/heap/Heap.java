package heap;

public class Heap {

	public int size = 0;
	public int[] elements;

	public Heap() {

	}

	public Heap(int maxSize) {
		elements = new int[maxSize];
	}

	public void add(int x) {
		elements[size++] = x;
	}

	public void createHeap() {
		// 从中间节点开始，直至0节点
		for (int i = (size - 1) / 2; i >= 0; i--) {
			adjustDown(i, size);
		}
	}

	/**
	 * 向下调整 O(log(n))
	 * 
	 * @param start
	 * @param size
	 */
	public void adjustDown(int start, int size) {
		int n = size;
		int child = 2 * start + 1;
		int temp = elements[start];
		// 循环比较左、右孩子
		while (child < n) {
			// 取左、右孩子中的较小者，保证下标小于n
			if (child + 1 < n && elements[child] > elements[child + 1])
				child++;
			if (temp <= elements[child])
				break;
			if (child % 2 == 0) {
				elements[child / 2 - 1] = elements[child];
			} else {
				elements[child / 2] = elements[child];
			}
			// 指向左孩子
			child = 2 * child + 1;
		}
		if (child % 2 == 0) {
			elements[child / 2 - 1] = temp;
		} else {
			elements[child / 2] = temp;
		}
	}

}
