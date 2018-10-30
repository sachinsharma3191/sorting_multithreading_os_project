package com.org.quicksort;

import java.util.Comparator;

import com.org.common.CommonUtils;

/**
 * @author sachin
 *
 */
public class ParallelQuickSort extends Thread {

	/**
	 * Sorts an array, using the quick sort algorithm.
	 * 
	 * @param <T>
	 *
	 * @param a
	 *            the array to sort
	 * @param comp
	 *            the comparator to compare array elements
	 */
	public static <T> void sort(T[] a, Comparator<? super T> comp, long threads) {
		parallelQuickSort(a, 0, a.length - 1, comp, threads);
	}

	private static <T> void parallelQuickSort(T[] a, int from, int to, Comparator<? super T> comp,
			long availableThreads) {
		if (from - to > 0) {
			if (availableThreads <= 1) {
				quickSort(CommonUtils.convert(a), from, to);
			}
		} else {
			if (from < to) {
				if (from < to) {
					/*
					 * pi is partitioning index, arr[pi] is now at right place
					 */
					int pivot = partition(CommonUtils.convert(a), from, to);
					Thread firstHalf = new Thread() {
						public void run() {
							parallelQuickSort(a, from, pivot, comp, availableThreads - 1);
						}
					};
					Thread secondHalf = new Thread() {
						public void run() {
							parallelQuickSort(a, pivot + 1, to, comp, availableThreads - 1);
						}
					};

					firstHalf.start();
					secondHalf.start();

					try {
						firstHalf.join();
						secondHalf.join();
					} catch (InterruptedException ie) {
						ie.printStackTrace();
					}

				}
			}
		}
	}

	/**
	 * @param arr
	 * @param low
	 * @param high
	 */
	static void quickSort(int arr[], int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pivot = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			quickSort(arr, low, pivot - 1);
			quickSort(arr, pivot + 1, high);
		}
	}

	/**
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	static int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	/**
	 * @param data
	 * @param i
	 * @param j
	 */
	void swap(int[] data, int i, int j) {
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
	
	
	

}
