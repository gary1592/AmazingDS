package string;

import org.junit.Test;

public class PatternMatch {

	/**
	 * 简单模式匹配 O(n*m) 主串s长度为n，子串p长度为m
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public int simpleMatch(String s, String p) {
		char[] chs = s.toCharArray();
		char[] chp = p.toCharArray();
		int k = 0, i = 0, j = 0;
		while (j < chp.length) {
			if (chs[i++] != chp[j++]) {
				i = ++k;
				j = 0;
			}
		}
		if (j == chp.length)
			return k;
		return -1;
	}

	/**
	 * KMP模式匹配 O(n) 主串指针无需回溯，子串回溯到哪一位取决于子串的特性
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public int kmpMatch(String s, String p, int[] f) {
		char[] chs = s.toCharArray();
		char[] chp = p.toCharArray();
		int i = 0, j = 0;
		while (i < chs.length && j < chp.length) {
			if (j == -1 || chs[i] == chp[j]) {
				i++;
				j++;
			} else {
				j = f[j];
			}
		}
		return (j == chp.length) ? i - chp.length : -1;
	}

	/**
	 * 失败函数 O(m)
	 * 
	 * @param p
	 * @param f
	 */
	public void fail(String p, int[] f) {
		char[] chp = p.toCharArray();
		int j = 0, k = -1;
		f[0] = -1;
		while (j < p.length()) {
			if (k == -1 || chp[j] == chp[k]) {
				j++;
				k++;
				f[j] = k;
			} else {
				k = f[k];
			}
		}
	}

	/**
	 * 改进的失败函数 O(m)
	 * 
	 * @param p
	 * @param f
	 */
	public void fail2(String p, int[] f) {
		char[] chp = p.toCharArray();
		int j = 0, k = -1;
		f[0] = -1;
		while (j < p.length() - 1) {
			if (k == -1 || chp[j] == chp[k]) {
				j++;
				k++;
				if (chp[j] == chp[k])
					f[j] = f[k];
				else
					f[j] = k;
			} else {
				k = f[k];
			}
		}
	}

	@Test
	public void test() {
		String s = "abcabcaabca";
		String p = "abcaab";
		int index = simpleMatch(s, p);
		System.out.println("简单模式匹配：" + index);

		int[] f = new int[p.length()];
		fail2(p, f);
		int k = kmpMatch(s, p, f);
		System.out.println("KMP模式匹配：" + k);
	}
}
