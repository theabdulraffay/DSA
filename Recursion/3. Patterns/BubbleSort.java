import java.util.*;
class BubbleSort {
	public static void main(String[] args) {
		int[] a = {1,5,2,0};
		selectionSort(a,a.length,0, 0);
		System.out.println(Arrays.toString(a));
		
	}

	static int[] bubbleSort(int[] a, int i, int j) {
		if (i == a.length - 1) {
			return a;
		}

		if (j < a.length) {
			if (a[j] < a[i]) {
				int temp = a[i];
				a[i] = a[j];
				a[j] = temp;
			}
			return bubbleSort(a, i, j + 1);
		} else {
			return bubbleSort(a, i + 1, i + 1);
		}
	}

	static void selectionSort(int[] a, int i, int j, int max) {
		if (i == 0) {
			return;
		}

		if (j < i) {
			if(a[j] > a[max]) max = j;
			selectionSort(a, i, j + 1, max);
		} else {
			int temp = a[max];
			a[max] = a[i-1];
			a[i-1] = temp;
			selectionSort(a, i - 1, 0, 0);
		}
	}
}