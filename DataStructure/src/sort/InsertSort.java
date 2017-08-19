package sort;

import org.junit.Test;

public class InsertSort {

	/**
	 * 简单插入排序 O(n^2)
	 * 
	 * @param list
	 */
	public void simpleInsertSort(int[] list) {
		int x, i, j;
		for (i = 1; i < list.length; i++) {
			x = list[i];// 待插入元素
			// 在有序子序列中寻找插入位置，从后往前找
			for (j = i - 1; j >= 0 && x < list[j]; j--) {
				list[j + 1] = list[j];// x若小于当前元素，则此元素后移一位
			}
			list[j + 1] = x;// x插入当前元素的后面一位
		}
	}

	/**
	 * 增量插入排序，用于希尔排序
	 * 
	 * @param list
	 * @param s
	 */
	public void simpleInsertSort(int[] list, int s) {
		int x, i, j;
		for (i = 1; i < list.length; i += s) {
			x = list[i];
			for (j = i - s; j >= 0 && x < list[j]; j -= s) {
				list[j + s] = list[j];
			}
			list[j + s] = x;
		}
	}

	/**
	 * 希尔排序 O(n*(log(n))^2)
	 * 
	 * @param list
	 */
	public void shellSort(int[] list) {
		int step = list.length;
		do {
			step = step / 3 + 1;
			simpleInsertSort(list);
		} while (step > 1);
	}

	@Test
	public void test() {
		int[] list1 = { 0, 2, 3, 4, 52, 4, 2, 42 };
		int[] list2 = { 0, 2, 3, 4, 52, 4, 2, 42 };

		simpleInsertSort(list1);
		System.out.println("简单插入排序：");
		for (int a : list1)
			System.out.print(a + " ");
		System.out.println();

		shellSort(list2);
		System.out.println("希尔排序：");
		for (int a : list2)
			System.out.print(a + " ");
	}

}
