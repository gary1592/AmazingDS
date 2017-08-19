package sort;

import org.junit.Test;

public class BubbleSort {

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

	@Test
	public void test() {
		int[] array = { 9, 23, 232, 134, 92, 234 };
		bubbleSort(array);

		System.out.println("冒泡排序：");
		for (int a : array) {
			System.out.println(a + " ");
		}
	}
}
