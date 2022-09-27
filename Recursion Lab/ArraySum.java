public class ArraySum {
	public int sumOfArray(Integer[] a, int index) {
		// If index is out of bounds return 0
		if (index == -1) return 0;
		// Else return the number in the array at the index plus the previous number
		else return a[index] + sumOfArray(a, --index);
	}
}