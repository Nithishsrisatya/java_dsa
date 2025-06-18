package programs;

import java.util.Arrays;

public class QuickSort {

	public static void quickSort(double[] array, int low, int high) {
		if(low < high) {
			int pivotIndex = Partition.partitionArray(array, low, high);
			quickSort(array, low, pivotIndex-1);
			quickSort(array, pivotIndex+1, high);
		}
	}
	
	public static void main(String[] args) {
		double[] array = {12, 35, 54, 1.2, 2.1, 1.5, 1.7, 1.11, 1.05, 1.55};
		System.out.println("Input Array: " + Arrays.toString(array));
		quickSort(array, 0, array.length-1);
		System.out.println("Sorted Array: " + Arrays.toString(array));
	}
}
