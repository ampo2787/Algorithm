import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class FindMatrix_1258 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int test_case = 1; test_case <= T; test_case++) {
			int N = Integer.parseInt(br.readLine());

			int[][] map = new int[N][N];
			boolean[][] visit = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ArrayList<int[]> list = new ArrayList();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!visit[i][j]) {
						if (map[i][j] != 0) {
							list.add(quad(map, visit, i, j, N));
						}
					}
				}
			}

			Collections.sort(list, new Comparator<int[]>() {
				@Override
				public int compare(int[] o1, int[] o2) {
					// TODO Auto-generated method stub
					if (o1[0] * o1[1] < o2[0] * o2[1]) {
						return -1;
					} else if (o1[0] * o1[1] > o2[0] * o2[1]) {
						return 1;
					} else {
						if (o1[0] < o2[0]) {
							return -1;
						} else {
							return 1;
						}
					}
				}
			});

			sb.append("#" + test_case + " " + list.size() + " ");
			for (int i = 0; i < list.size(); i++) {
				sb.append(list.get(i)[0] + " " + list.get(i)[1] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static int[] quad(int[][] map, boolean[][] visit, int y, int x, int N) {
		int py = y;
		int px = x;

		while (y < N && map[y][x] != 0) {
			y++;
		}
		y--;
		while (x < N && map[y][x] != 0) {
			x++;
		}
		y++;
		for (int i = py; i < y; i++) {
			for (int j = px; j < x; j++) {
				visit[i][j] = true;
			}
		}

		int[] result = { y - py, x - px };
		return result;
	}
}
