package Trab_Pratico;

public class SelectionSort {
	void selectionSort(int[] array) {
		for (int i = 0; i < (array.length - 1); i++) {
			int menor = i;
			for (int j = (i + 1); j < array.length; j++) {
				if (array[menor] > array[j]) {
					menor = j;
				}
			}
			int temp = array[i];
			array[i] = array[menor];
			array[menor] = temp;
		}
	}
}