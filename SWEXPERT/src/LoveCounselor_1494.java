import java.util.ArrayList;
import java.util.Scanner;

public class LoveCounselor_1494 {
	static int[] arr;
	static ArrayList<boolean[]> combination;
	static int N;
	static long[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		for (int test_case = 1; test_case <= T; test_case++) {
			long min = Long.MAX_VALUE;
			N = sc.nextInt();
			map = new long[N][2];
			combination = new ArrayList<>();
			combination(new boolean[N], 0, N / 2);

			for (int i = 0; i < N; i++) {
				map[i][0] = sc.nextInt();
				map[i][1] = sc.nextInt();
			}

			for (int i = 0; i < combination.size(); i++) {
				long x = 0;
				long y = 0;
				boolean[] com = combination.get(i);
				for (int j = 0; j < com.length; j++) {
					if (com[j]) {
						x += map[j][0];
						y += map[j][1];
					} else {
						x -= map[j][0];
						y -= map[j][1];
					}
				}
				min = Math.min(x * x + y * y, min);
			}
			System.out.println("#" + test_case + " " + min);
		}

		sc.close();
	}

	static void combination(boolean[] visited, int start, int r) {
		if (r == 0) {
			combination.add(visited.clone());
			return;
		} else {
			for (int i = start; i < N; i++) {
				visited[i] = true;
				combination(visited, i + 1, r - 1);
				visited[i] = false;
			}
		}
	}

}
