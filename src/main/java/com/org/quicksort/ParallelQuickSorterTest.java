package com.org.quicksort;

import java.util.Comparator;
import java.util.Random;

import com.org.common.CommonUtils;


public class ParallelQuickSorterTest {

	public static void main(String[] args) {
		runSortTester();
	}

	/**
	 * Runs a nested for loop of tests that call ParallelMergeSorter and then checks
	 * the array afterwards to ensure correct sorting
	 */
	public static void runSortTester() {
		long SIZE = 1000000L, // initial length of array to sort
				ROUNDS = 2, availableThreads = (Runtime.getRuntime().availableProcessors()) * 2;

		System.out.println(Runtime.getRuntime().availableProcessors());

		Integer[] a;


		System.out.printf("\nMax number of threads == %d\n\n", availableThreads);
		for (int i = 1; i <= availableThreads; i *= 2) {
			if (i == 1) {
				System.out.printf("%d Thread:\n", i);
			} else {
				System.out.printf("%d Threads:\n", i);
			}
			for (long j = 0, k = SIZE; j < ROUNDS; ++j, k *= 2) {
				a = createRandomArray(k);
				// run the algorithm and time how long it takes to sort the elements
				long startTime = System.currentTimeMillis();
				ParallelQuickSort.quicksort(a);
				long endTime = System.currentTimeMillis();
				System.out.printf("%10d elements  =>  %6d ms \n", k, endTime - startTime);
			}
			System.out.print("\n");
		}
	}

	
	// Creates an array of the given length, fills it with random
	// non-negative integers, and returns it.
	public static Integer[] createRandomArray(long length) {
		Integer[] a = new Integer[(int) length];
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt(CommonUtils.MAX_LIMIT);
		}
		return a;
	}
}
