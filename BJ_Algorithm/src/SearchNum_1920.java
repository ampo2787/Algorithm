import java.util.Arrays;
import java.util.Scanner;

public class SearchNum_1920 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N];
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		int M = sc.nextInt();
		int[] B = new int[M];

		for (int i = 0; i < M; i++) {
			B[i] = sc.nextInt();
		}

		Arrays.sort(A);
		int item, result;
		int left, right, mid;

		for (int i = 0; i < M; i++) {
			item = B[i];
			left = 0;
			right = N - 1;
			result = 0;

			while (left <= right) {
				mid = (left + right) / 2;
				if (A[mid] < item) {
					left = mid + 1;
				} else if (A[mid] > item) {
					right = mid - 1;
				} else {
					result = 1;
					break;
				}
			}
			sb.append(result + "\n");
		}

		System.out.println(sb);
		sc.close();

	}

}
