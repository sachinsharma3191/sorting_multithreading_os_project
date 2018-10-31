package com.thread.quicksort;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class QuickSortForkJoin {
	private final ForkJoinPool pool;

	private class QuickSortTask extends RecursiveAction {

		private static final long serialVersionUID = 1L;
		private final int[] array;
		private final int low;
		private final int high;
		private static final int THRESHOLD = 8;

		public QuickSortTask(int[] array, int low, int high) {
			this.array = array;
			this.low = low;
			this.high = high;
		}

		@Override
		protected void compute() {
			// TODO Auto-generated method stub
			if (high - low <= THRESHOLD) {
				Arrays.sort(array, low, high);
			} else {
				int pivot = partition(array, low, high);
				invokeAll(new QuickSortTask(array, low, pivot), new QuickSortTask(array, pivot + 1, high));
			}
		}

		private int partition(int[] array, int low, int high) {
			int pivot = array[low];
			int i = low - 1;
			int j = high + 1;
			while (true) {
				do {
					i++;
				} while (array[i] < pivot);

				do {
					j--;
				} while (array[j] > pivot);
				if (i >= j)
					return j;

				swap(array, i, j);
			}
		}

		private void swap(int[] array, int i, int j) {
			int temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}

	}

	public QuickSortForkJoin(int parallelism) {
		pool = new ForkJoinPool(parallelism);
	}

	/**
	 * Sorts all the elements of the given array using the ForkJoin framework
	 * 
	 * @param array the array to sort
	 */
	public void sort(int[] array) {
		ForkJoinTask<Void> job = pool.submit(new QuickSortTask(array, 0, array.length));
		job.join();
	}

	protected void finalize() {
		pool.shutdown();
	}

}