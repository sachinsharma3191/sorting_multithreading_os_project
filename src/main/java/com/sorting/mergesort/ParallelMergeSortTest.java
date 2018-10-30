package com.sorting.mergesort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Taylor Carr
 * @version 1.0
 */
public class ParallelMergeSortTest {

	public static void main(String[] args) {
		runSortTester();
	}

	/**
	 * Runs a nested for loop of tests that call ParallelMergeSorter and then checks
	 * the array afterwards to ensure correct sorting
	 */
	public static void runSortTester() {
		long SIZE = 100000000L, // initial length of array to sort
				ROUNDS = 2, availableThreads = (Runtime.getRuntime().availableProcessors()) * 2;

		System.out.println(Runtime.getRuntime().availableProcessors());

		Integer[] a;

		Comparator<Integer> comp = new Comparator<Integer>() {
			public int compare(Integer d1, Integer d2) {
				return d1.compareTo(d2);
			}
		};

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
				ParallelMergeSort.sort(a, comp, availableThreads);
				long endTime = System.currentTimeMillis();

				if (!isSorted(a, comp)) {
					throw new RuntimeException("not sorted afterward: " + Arrays.toString(a));
				}

				System.out.printf("%10d elements  =>  %6d ms \n", k, endTime - startTime);
			}
			System.out.print("\n");
		}
	}

	/**
	 * Returns true if the given array is in sorted ascending order.
	 *
	 * @param a
	 *            the array to examine
	 * @param comp
	 *            the comparator to compare array elements
	 * @return true if the given array is sorted, false otherwise
	 */
	public static <E> boolean isSorted(E[] a, Comparator<? super E> comp) {
		for (int i = 0; i < a.length - 1; i++) {
			if (comp.compare(a[i], a[i + 1]) > 0) {
				System.out.println(a[i] + " > " + a[i + 1]);
				return false;
			}
		}
		return true;
	}

	// Randomly rearranges the elements of the given array.
	public static <E> void shuffle(E[] a) {
		for (int i = 0; i < a.length; i++) {
			// move element i to a random index in [i .. length-1]
			int randomIndex = (int) (Math.random() * a.length - i);
			swap(a, i, i + randomIndex);
		}
	}

	// Swaps the values at the two given indexes in the given array.
	public static final <E> void swap(E[] a, int i, int j) {
		if (i != j) {
			E temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}

	// Creates an array of the given length, fills it with random
	// non-negative integers, and returns it.
	public static Integer[] createRandomArray(long length) {
		Integer[] a = new Integer[(int) length];
		Random rand = new Random(System.currentTimeMillis());
		for (int i = 0; i < a.length; i++) {
			a[i] = rand.nextInt(1000000);
		}
		return a;
	}
}