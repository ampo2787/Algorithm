import java.util.Scanner;

public class Hay_5603 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int[] hay = new int[N];
			int sum = 0;
			for (int i = 0; i < N; i++) {
				hay[i] = sc.nextInt();
				sum += hay[i];
			}

			int avg = sum / N;
			int min = 0;

			for (int i = 0; i < N; i++) {
				if (hay[i] < avg) {
					min += avg - hay[i];
				}
			}
			System.out.println("#" + test_case + " " + min);
		}
	}

}
