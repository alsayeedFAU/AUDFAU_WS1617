public class QuickSort {

	public static <E> E[] swap(E[] array, int i, int j) {
		// TODO
		E k = array[j];
		array[j] = array[i];
		array[i] = k;

		return array;
	}

	public static <E extends Comparable<? super E>> int choosePivot(E[] array, int start, int end) {
		// TODO
		int pivot = (start + end) / 2;
		if ((array[pivot].compareTo(array[start]) > 0) && (array[pivot].compareTo(array[end]) < 0)
				|| ((array[pivot].compareTo(array[start]) < 0) && (array[pivot].compareTo(array[end]) > 0))) {
			return pivot;
		}
		if ((array[start].compareTo(array[pivot]) > 0) && (array[start].compareTo(array[end]) < 0)
				|| ((array[start].compareTo(array[end]) > 0) && (array[start].compareTo(array[pivot]) < 0))) {
			return start;
		}
		if ((array[end].compareTo(array[pivot]) > 0) && (array[end].compareTo(array[start]) < 0)
				|| ((array[end].compareTo(array[pivot]) < 0) && (array[end].compareTo(array[start]) > 0))) {
			return end;
		}
		if ((array[start].compareTo(array[pivot]) == 0) || (array[start].compareTo(array[end]) == 0)
				|| ((array[start].compareTo(array[pivot]) == 0) && (array[start].compareTo(array[end]) == 0))) {
			return start;

		} else
			return pivot;

	}

	public static <E extends Comparable<? super E>> int partition(E[] array, int pivot, int start, int end) {
		// TODO!
		int n = end;
		int m = start;

		E p = array[end];
		int i = m - 1;
		int j = m;

		while (j < n) {

			if (array[j].compareTo(p) <= 0) {
				i++;
				swap(array, i, j);
			}
			j++;
		}
		int r = i + 1;
		swap(array, r, n);

		return r;
	}

	protected static <E extends Comparable<? super E>> void quicksortRec(E[] array, int start, int end) {

		int pivot = choosePivot(array, start, end);
		swap(array, pivot, end);

		if (array[start].compareTo(array[pivot]) <= 0) {
			partition(array, pivot, start + 1, end);
		}
		if (array[end].compareTo(array[pivot]) > 0) {
			partition(array, pivot, start, end - 1);
		}
		if(array[start].compareTo(array[pivot]) > 0 && array[end].compareTo(array[pivot]) < 0){
			swap(array,start,end);
		}
	}

	public static <E extends Comparable<? super E>> void sort(E[] array) {
		// TODO
		quicksortRec(array, 0, array.length - 1);
	}
}