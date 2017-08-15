package ufset;

public class UFset {

	int[] parent;
	int size;

	public UFset(int maxSize) {
		parent = new int[maxSize];
	}
	
	public void createUFset(int n){
		size = n;
		for(int i = 0;i<n;i++)
			parent[i] = -1;
	}

	public int Find(int i) {
		int r, t, l;
		for (r = i; parent[r] > 0; r = parent[r]) {
		}
		// 将根到元素i的路径上的所有节点的parent域均重置，使他们直接连至根节点
		if (i != r) {
			for (t = i; parent[t] != r; t = l) {
				l = parent[t];
				parent[t] = r;
			}
		}
		return r;
	}

	public void Union(int x, int y) {
		int temp = parent[x] + parent[y];
		if (parent[x] > parent[y]) {
			parent[x] = y;
			parent[y] = temp;
		} else {
			parent[y] = x;
			parent[x] = temp;
		}
	}
}
