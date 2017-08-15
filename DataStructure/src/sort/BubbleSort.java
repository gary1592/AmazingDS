package sort;

import org.junit.Test;

public class BubbleSort {

	public void sort(int[] array) {
		int i, j, last, temp;
		for (i = array.length - 1; i > 0;) {
			last = 0;
			for (j = 0; j < i; j++) {
				if (array[j] > array[j + 1]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
					last = j;
				}
			}
			i = last;
		}
	}

	@Test
	public void test() {
		int[] array = { 9, 23, 232, 134, 92, 234 };
		sort(array);
		for (int a : array) {
			System.out.println(a);
		}
	}
}
