package programs;

import java.util.Arrays;
import java.util.Scanner;

public class Partition {
	
	public static int partitionArray(double[] array, int low, int high) {
		double pivot = array[high];
		int j = low;
		for(int i = low; i < high; i++) {
			if(array[i] < pivot) {
				double temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				j++;
			}
		}
		double temp = array[high];
		array[high] = array[j];
		array[j] = temp;
		return j;
	}
}
