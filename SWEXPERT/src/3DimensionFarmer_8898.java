import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int c1 = sc.nextInt();
			int c2 = sc.nextInt();

			int xDis = Math.abs(c1 - c2);

			int[] cows = new int[N];
			int[] horses = new int[M];

			for (int i = 0; i < N; i++) {
				cows[i] = sc.nextInt();
			}

			for (int i = 0; i < M; i++) {
				horses[i] = sc.nextInt();
			}

			Arrays.sort(cows);
			Arrays.sort(horses);

			int num = 0;
			int zDis = Math.abs(cows[0] - horses[0]);

			int cowIndex = 0;
			int horseIndex = 0;
			int abs;
			while (cowIndex < N && horseIndex < M) {
				abs = Math.abs(cows[cowIndex] - horses[horseIndex]);

				if (abs < zDis) {
					zDis = abs;
					num = 1;
				} else if (abs == zDis) {
					num++;
				}

				if (cows[cowIndex] < horses[horseIndex]) {
					cowIndex++;
				} else {
					horseIndex++;
				}
			}

			zDis = xDis + zDis;
			System.out.println("#" + test_case + " " + zDis + " " + num);
		}
	}

}
