package org.credo.testcode;

public class StringTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName1="20121.我大旗.网大.旗网.txt";
		String fileName2="20121我大旗网大旗网.doc";
		String fileName3="20121我大旗网大旗网.xls";
		String aa=fileName1.substring(0, fileName1.lastIndexOf("."));
		System.out.println("aa:"+aa);
	}

}
