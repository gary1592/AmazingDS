package sort;

import org.junit.Test;

import heap.Heap;

public class SelectSort {

	/**
	 * 简单选择排序 O(n^2)
	 * 
	 * @param list
	 */
	public void simpleSelectSort(int[] list) {
		int min, i, j;
		for (i = 0; i < list.length - 1; i++) {
			min = i;
			for (j = i + 1; j < list.length; j++) {
				if (list[min] > list[j]) {
					min = j;
				}
			}
			swap(i, min, list);
		}
	}

	public void swap(int i, int j, int[] array) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 堆排序 O(n*log(n))
	 * 
	 * @param list
	 */
	public void heapSort(int[] list) {
		Heap heap = new Heap();
		int i;
		heap.elements = list;
		// 建堆
		for (i = list.length / 2; i > 0; i--) {
			heap.adjustDown(i, list.length);
		}
		// 堆排序
		for (i = list.length; i > 1; i--) {
			swap(0, i - 1, list);// 交换顶、底元素
			heap.adjustDown(0, i - 1);// 顶点向下调整，长度减一
		}
	}

	@Test
	public void test() {
		int[] list1 = { 0, 2, 3, 4, 52, 4, 2, 42 };
		int[] list2 = { 0, 2, 3, 4, 52, 4, 2, 42 };

		simpleSelectSort(list1);
		System.out.println("简单选择排序：");
		for (int a : list1)
			System.out.print(a + " ");
		System.out.println();

		heapSort(list2);
		System.out.println("堆排序：");
		for (int a : list2)
			System.out.print(a + " ");
	}

}
