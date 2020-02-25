import java.util.Arrays;
import java.util.Scanner;

public class LanLineCut_1654 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int n = sc.nextInt();
		// K <= N.

		int[] length = new int[k];

		for (int i = 0; i < k; i++) {
			length[i] = sc.nextInt();
		}

		Arrays.sort(length);

		long middle = 0;
		long left = 1;
		long right = length[k - 1];

		long thisN;

		while (left <= right) {
			long thisLength = (left + right) / 2;
			thisN = 0;

			for (int i = 0; i < k; i++) {
				thisN += length[i] / thisLength;
			}

			if (thisN < n) {
				right = thisLength - 1;
			} else if (thisN >= n) {
				left = thisLength + 1;
			}
		}

		System.out.println(right);

	}

}
