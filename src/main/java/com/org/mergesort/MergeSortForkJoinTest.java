package com.org.mergesort;

import java.util.Random;

import com.org.common.CommonUtils;


public class MergeSortForkJoinTest {

	public static void runSort(int limit,int poolSize ) {
		System.out.println("***************Merge Sort running time***********");
		for (int i = 0; i < limit; i++) {
			int power = (int) Math.round(Math.pow(2, i));
			Random random = new Random(limit);
			int[] array = new int[power];
			for (int j = 0; j < power; ++j) {
				array[j] = random.nextInt(CommonUtils.MAX_LIMIT);
			}
			//System.out.println("**********Unsorted Array*****************");
			//CommonUtils.printArray(array);
			System.out.println("**********************************************");
			System.out.print("Sorting Time for " + power + " elements ");
			long start = System.nanoTime();
			new MergeSortForkJoin(poolSize).sort(array);
			///System.out.println("**********Sorted Array*****************");
			//CommonUtils.printArray(array);
			

			System.out.println(String.format("%f [msec]", (System.nanoTime() - start) / 1000000.0));
			System.out.print("**********************************************");
		}

	}

	public static void main(String[] args) {
		int limit = 32;
		int poolSize = 10000;
		runSort(limit,poolSize);
	}
}
