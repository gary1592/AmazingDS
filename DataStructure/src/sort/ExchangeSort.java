package sort;

import org.junit.Test;

public class ExchangeSort {

	/**
	 * 冒泡排序 O(n^2)
	 * 
	 * @param array
	 */
	public void bubbleSort(int[] array) {
		int i, j, last, temp;
		// 外循环定义序列末位置
		for (i = array.length - 1; i > 0;) {
			last = 0;
			// 内循环从头至末交换比较
			for (j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					last = j;// 记录最后一次交换的位置
				}
			}
			i = last;
		}
	}

	/**
	 * 划分函数 O(log(n))
	 * 
	 * @param array
	 * @param left
	 * @param right
	 * @return
	 */
	public int position(int[] array, int left, int right) {
		int i = left, j = right + 1;
		int pivot = array[left];
		do {
			do {
				i++;
			} while (array[i] < pivot);
			do {
				j--;
			} while (array[j] > pivot);
			if (i < j)
				swap(i, j, array);
		} while (i < j);
		swap(left, j, array);
		return j;
	}

	public void swap(int i, int j, int[] array) {
		int temp;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**
	 * 快速排序 O(log(n))
	 * 
	 * @param array
	 * @param left
	 * @param right
	 */
	public void quickSort(int[] array, int left, int right) {
		int k;
		if (left < right) {
			k = position(array, left, right);
			quickSort(array, left, k - 1);
			quickSort(array, k + 1, right);
		}
	}

	/**
	 * 以第一个元素为主元
	 * 
	 * @param array
	 */
	public void quickSortStart(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	@Test
	public void test() {
		int[] list = { 9, 23, 232, 134, 92, 234 };
		bubbleSort(list);

		System.out.println("冒泡排序：");
		for (int a : list) {
			System.out.print(a + " ");
		}
		System.out.println();

		int[] array = { 0, 21, 32, 4, 1, 2, 23, 546 };
		quickSortStart(array);

		System.out.println("快速排序：");
		for (int a : array) {
			System.out.print(a + " ");
		}

	}
}
