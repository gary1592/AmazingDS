package heap;

public class PriorityQueue {

	int size = 0;
	int[] elements;

	public PriorityQueue() {

	}

	public PriorityQueue(int maxSize) {
		elements = new int[maxSize];
	}

	/**
	 * 构造初始数据
	 * 
	 * @param x
	 */
	public void add(int x) {
		elements[size++] = x;
	}

	public void createPriorityQueue() {
		for (int i = (size - 1) / 2; i >= 0; i--) {
			adjustDown(i, size);
		}
	}

	/**
	 * 尾部插入之后向上调整 O(log(n))
	 * 
	 * @param size
	 */
	public void adjustUp(int size) {
		int i = size - 1;
		int temp = elements[i];
		System.out.println(temp);
		while (i > 0 && temp < elements[(i - 1) / 2]) {
			elements[i] = elements[(i - 1) / 2];
			i = (i - 1) / 2;
		}
		elements[i] = temp;
	}

	/**
	 * 删除之后向下调整 O(log(n))
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

	/**
	 * 插入一个新元素
	 * 
	 * @param x
	 */
	public void append(int x) {
		// 堆底加入一个元素，堆的大小加一
		elements[size++] = x;
		// System.out.println(size);
		// System.out.println(x);
		// 只需从底部元素开始向上调整
		adjustUp(size);
	}

	/**
	 * 删除头元素并返回
	 * 
	 * @return
	 */
	public int serve() {
		// 头元素赋给x
		int x = elements[0];
		// 底元素赋给头元素，堆的大小减一
		elements[0] = elements[size--];
		// 只需头元素向下调整
		adjustDown(0, size);
		return x;
	}

	/**
	 * 删除头元素但不返回
	 */
	public void peek() {
		elements[0] = elements[size--];
		adjustDown(0, size);
	}

}
