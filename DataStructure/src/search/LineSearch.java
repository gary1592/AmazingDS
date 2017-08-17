package search;

public class LineSearch {

	/**
	 * 搜索无序表 O(n)
	 * 
	 * @param list
	 * @param x
	 * @return
	 */
	public int seqSearch(int[] list, int x) {
		for (int i = 0; i < list.length; i++) {
			if (list[i] == x)
				return i;
		}
		return -1;
	}

	/**
	 * 搜索有序表 O(n)
	 * 
	 * @param list
	 * @param x
	 * @return
	 */
	public int seqOrderedSearch(int[] list, int x) {
		int i;
		// 顺序搜索直至大于等于x
		for (i = 0; list[i] < x; i++)
			;
		if (list[i] == x)
			return i;
		else
			return -1;
	}

	/**
	 * 二分搜索 O(log(n)),前提是有序表
	 * 
	 * @param list
	 * @param x
	 * @return
	 */
	public int BinarySearch(int[] list, int x) {
		int mid, low = 0, high = list.length - 1;
		while (low <= high) {
			mid = (low + high) / 2;
			if (x > list[mid]) {
				low = mid + 1;
			} else if (x < list[mid]) {
				high = mid - 1;
			} else {
				return mid;
			}

		}
		return -1;
	}

}
