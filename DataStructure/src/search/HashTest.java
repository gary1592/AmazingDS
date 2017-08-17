package search;

import org.junit.Test;

public class HashTest {

	@Test
	public void test() {
		HashTable hash = new HashTable(11);
		hash.insert(80);
		hash.insert(40);
		hash.insert(65);
		hash.insert(58);
		hash.insert(40);
		hash.insert(35);

		// hash.delete(35);

		for (HashNode a : hash.table) {
			System.out.print(a.element + " ");
		}
	}
}
