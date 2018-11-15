package com.org.common;

import java.util.Arrays;

public class CommonUtils {

	public static final int MAX_LIMIT  = 10000;
	
	public static <T> int[] convert(T[] nums) {
		int counter = 0;
		int array[] = new int[nums.length];
		for (T num : nums) {
			array[counter++] = (int) num;
		}
		return array;
	}

	public static void printArray(int array[]) {
		System.out.print(Arrays.toString(array));
	}

	public static void printArray(Integer[] a) {
		// TODO Auto-generated method stub
		System.out.print(Arrays.toString(a));
		
	}

}
