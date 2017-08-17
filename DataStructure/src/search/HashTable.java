package search;

public class HashTable {

	int M;// 模
	HashNode[] table;

	public HashTable() {

	}

	public HashTable(int M) {
		this.M = M;
		this.table = new HashNode[M];
		for (int i = 0; i < M; i++) {
			table[i] = new HashNode();
			table[i].element = -1;
			table[i].isEmpty = true;
		}
	}

	/**
	 * 定义哈希算法
	 * 
	 * @param x
	 * @return
	 */
	public int hashCode(int x) {
		int pos = x % M;
		while (!table[pos].isEmpty && table[pos].element != x) {
			pos = (pos + 1) % M;
		}
		if (table[pos].isEmpty) {
			return pos;
		} else {
			return -1;// 散列表已满
		}
	}

	/**
	 * 搜索哈希值
	 * 
	 * @param x
	 * @return
	 */
	public int hashSearch(int x) {
		int pos = x % M;
		do {
			if (table[pos].element == x) {
				return pos;
			} else {
				pos = (pos + 1) % M;
			}
		} while (pos < M);
		return -1;
	}

	/**
	 * 散列表插入元素 O(1)
	 * 
	 * @param x
	 */
	public void insert(int x) {
		int pos = hashCode(x);
		if (pos != -1) {
			table[pos].element = x;
			table[pos].isEmpty = false;
		}
	}

	/**
	 * 散列表删除元素 O(1)
	 * 
	 * @param x
	 */
	public void delete(int x) {
		int pos = hashSearch(x);
		if (pos != -1) {
			table[pos].element = -1;
			table[pos].isEmpty = true;
		}
	}

}
