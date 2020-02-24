import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int result = die_Insect(map, N, M);
			sb.append("#" + test_case + " " + result + "\n");
		}
		System.out.println(sb);
	}

	public static int die_Insect(int[][] map, int N, int M) {
		int max = 0;
		for (int i = 0; i < N - M + 1; i++) {
			for (int j = 0; j < N - M + 1; j++) {
				int sum = 0;
				for (int k = i; k < i + M; k++) {
					for (int l = j; l < j + M; l++) {
						sum += map[k][l];
					}
				}
				max = Math.max(sum, max);
			}
		}
		return max;
	}
}
