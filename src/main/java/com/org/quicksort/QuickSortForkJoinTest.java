package com.thread.quicksort;

import java.util.Random;

import com.thread.mergesort.MergeSortForkJoin;

public class QuickSortForkJoinTest {

	public static void runSort(int limit) {
		System.out.println("***************Quick Sort running time***********");
		for (int i = 0; i < limit; i++) {
			int power = (int) Math.round(Math.pow(2, i));
			Random random = new Random(limit);
			int[] array = new int[power];
			for (int j = 0; j < power; ++j) {
				array[j] = random.nextInt();
			}
			System.out.println("**********************************************");
			System.out.print("Sorting Time for " + power + " elements ");
			long start = System.nanoTime();
			new MergeSortForkJoin(1000).sort(array);

			System.out.println(String.format("%f [msec]", (System.nanoTime() - start) / 1000000.0));
			System.out.print("**********************************************");
		}

	}

	public static void main(String[] args) {
		int limit = 29;
		runSort(limit);
	}

}
