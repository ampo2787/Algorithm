import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] map = new int[401];
		int index = 1;

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 2; j++) {
				map[index++] = i;
			}
		}

		int T = sc.nextInt();

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int time = 0;
			int[] corridor = new int[200];

			for (int i = 0; i < N; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if (a < b) {
					for (int j = map[a]; j <= map[b]; j++) {
						corridor[j]++;
					}
				} else {
					for (int j = map[b]; j <= map[a]; j++) {
						corridor[j]++;
					}
				}

			}

			for (int i = 0; i < 200; i++) {
				time = Math.max(corridor[i], time);
			}

			System.out.println("#" + test_case + " " + time);

		}
	}

}
