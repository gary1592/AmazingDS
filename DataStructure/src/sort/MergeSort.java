package sort;

import org.junit.Test;

public class MergeSort {

	int k;// 无法指针传参，只能设为全局变量

	/**
	 * 两路合并函数 O(n)
	 * 
	 * @param list
	 * @param temp
	 * @param i1
	 * @param j1
	 * @param i2
	 * @param j2
	 * @param k
	 */
	public void merge(int[] list, int[] temp, int i1, int j1, int i2, int j2) {
		int x1 = i1, x2 = i2;
		while ((x1 <= j1) && (x2 <= j2)) {
			if (list[x1] <= list[x2]) {
				temp[k++] = list[x1++];
			} else {
				temp[k++] = list[x2++];
			}
		}
		while (x1 <= j1)
			temp[k++] = list[x1++];
		while (x2 <= j2)
			temp[k++] = list[x2++];
	}

	/**
	 * 合并排序 O(n*log(n))
	 * 
	 * @param list
	 */
	public void sort(int[] list) {
		int[] temp = new int[list.length];
		int i1, j1, i2, j2, i, size = 1;
		while (size < list.length) {
			i1 = 0;
			k = 0;
			while (i1 + size < list.length) {// 存在两个子序列
				i2 = i1 + size;// 子序列2的下界
				j1 = i2 - 1;// 子序列1的上界
				if (i2 + size - 1 > list.length - 1)// 子序列2的上界
					j2 = list.length - 1;
				else
					j2 = i2 + size - 1;
				merge(list, temp, i1, j1, i2, j2);
				i1 = j2 + 1;// 下次合并的子序列1的上界
			}
			for (i = 0; i < k; i++) {
				list[i] = temp[i];
			}
			size *= 2; // 子序列长度扩大一倍
		}

	}

	@Test
	public void test() {
		int[] list = { 0, 2, 3, 4, 52, 4, 2, 42 };
		sort(list);

		System.out.println("合并排序：");
		for (int a : list)
			System.out.print(a + " ");
	}
}
