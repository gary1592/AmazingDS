package sort;

import org.junit.Test;

public class FastSort {

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
	public void startSort(int[] array) {
		quickSort(array, 0, array.length - 1);
	}

	@Test
	public void test() {
		int[] array = { 0, 21, 32, 4, 1, 2, 23, 546 };
		startSort(array);

		System.out.println("快速排序：");
		for (int a : array) {
			System.out.println(a);
		}
	}
}
