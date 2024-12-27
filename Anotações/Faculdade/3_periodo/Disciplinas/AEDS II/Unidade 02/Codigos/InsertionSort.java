package Trab_Pratico;

public class InsertionSort {
	void sort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int tmp = array[i];
			int j = i - 1;

			while ((j >= 0) && (array[j] > tmp)) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = tmp;
		}
	}
}
