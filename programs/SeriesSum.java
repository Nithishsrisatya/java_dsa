package day1;

import java.util.Scanner;

public class SeriesSum {
	private int termValue;
	private int numberOfTerms;
	private long sumOfSeries;
	
	public void findSumOfSeries() {
		for(int i = 1; i <= numberOfTerms; i++) {
			sumOfSeries = sumOfSeries + (long) (Math.pow(termValue, i-1) * Math.pow(-1, i-1)); 
		}
	}
	
	public static void main(String[] args) {
		SeriesSum obj = new SeriesSum();
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the term value: ");
		obj.termValue = scanner.nextInt();
		System.out.print("Enter number of terms of the series : ");
		obj.numberOfTerms = scanner.nextInt();
		if( (obj.termValue >= 0 && obj.termValue <= 5) && (obj.numberOfTerms >= 1 && obj.numberOfTerms <= 25) ) {
			obj.findSumOfSeries();
			System.out.println("Sum of the series is " + obj.sumOfSeries);
		}
		scanner.close();			
	}
}
