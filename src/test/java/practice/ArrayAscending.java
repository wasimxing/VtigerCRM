package practice;

public class ArrayAscending {
	public static void main(String[] args) {

		int b[] = { 8, 2, 7, 3, 5, 6, 9, 1 };
		int temp = 0;
		for (int i = 0; i < b.length; i++) {
			for (int j = i + 1; j < b.length; j++) {
				if (b[i] > b[j]) {
					temp = b[j];
					b[j] = b[i];
					b[i] = temp;
				}

			}
			System.out.println(b[i]);
		}

	}

}
