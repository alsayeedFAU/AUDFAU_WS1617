public class MergeSort {

	public void sort(List list) {
		// TODO
		doSort(list, list.size());
	}

	public List doSort(List list, int n) {
		// TODO
		if(n <=1){
			return list;
		}
		
		List left = list;
		List right = null;
		Element pointer = left.first;
		for(int i = 0;i < n && pointer != null ; i++){
			if(i == n/2){
				right = new List(pointer.next);
				pointer.next = null;
			}
			pointer = pointer.next;
		}
		
		left = doSort(left, n/2);
		right = doSort(right, (n-n/2));
		
		
		return merge(left, right);

	}

	public List merge(List left, List right) {
		// TODO
		while (right != null && right.size() != 0) {

			Element kopf = right.first;
			right.first = right.first.next;

			if (kopf.value < left.first.value) {
				kopf.next = left.first;
				left.first = kopf;
			} else {

				Element pointer = left.first;
				while (pointer.next != null && pointer.next.value < kopf.value) {
					pointer = pointer.next;
				}
				kopf.next = pointer.next;
				pointer.next = kopf;
			}

		}
		return left;
	}
}
