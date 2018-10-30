package com.org.common;

public class CommonUtils<E> {


	public static <T> int[] convert(T[] nums) {
		int counter = 0;
		int array[] = new int[nums.length];
		for (T num : nums) {
			array[counter++] = (int) num;
		}
		return array;
	}

}
