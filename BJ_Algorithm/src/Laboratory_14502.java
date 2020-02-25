import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Laboratory_14502 {
	static ArrayList<int[]> combination = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] map = new int[N][M];
		int[][] origin_map = new int[N][M];

		ArrayList<int[]> virus = new ArrayList<int[]>();
		ArrayList<int[]> empty_Place = new ArrayList<int[]>();

		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				origin_map[i][j] = map[i][j];
				int[] temp = { i, j };
				if (map[i][j] == 2) {
					virus.add(temp);
				} else if (map[i][j] == 0) {
					empty_Place.add(temp);
				}
			}
		}

		// new_Wall_Place �� ���� ���︸�� �ĺ���.
		// virus �� ���̷��� ��ġ.
		// new_Walll_Place �� ���� �� �������� virus �� BFS �� Ȯ��.

		combination(empty_Place.size());
		int max_SafetyZone = 0;

		for (int i = 0; i < combination.size(); i++) {
			for (int j = 0; j < 3; j++) {
				int y = empty_Place.get(combination.get(i)[j])[0];
				int x = empty_Place.get(combination.get(i)[j])[1];
				map[y][x] = 1;
			}
			// BFS ����.
			Queue<int[]> queue = new LinkedList<>();
			for (int j = 0; j < virus.size(); j++) {
				queue.add(virus.get(j));
			}

			while (!queue.isEmpty()) {
				int[] next = queue.poll();
				for (int j = 0; j < 4; j++) {
					int ny = next[0] + dy[j];
					int nx = next[1] + dx[j];
					int[] temp = { ny, nx };

					if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
						continue;
					}
					if (map[ny][nx] == 0) {
						map[ny][nx] = 2;
						queue.add(temp);
					}
				}
			}
			int safe = 0;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < M; k++) {
					if (map[j][k] == 0) {
						safe++;
					}
				}
				// ���� �ʱ�ȭ.
				map[j] = origin_map[j].clone();
			}
			max_SafetyZone = Math.max(safe, max_SafetyZone);
		}
		System.out.println(max_SafetyZone);

	}

	public static void combination(int size) {
		boolean[] visit = new boolean[size];
		dfs(visit, 0, size, 3);
	}

	static void dfs(boolean[] visited, int start, int n, int r) {
		if (r == 0) {
			int[] arr = new int[3];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					arr[index++] = i;
				}
			}
			combination.add(arr);
			return;
		} else {
			for (int i = start; i < n; i++) {
				visited[i] = true;
				dfs(visited, i + 1, n, r - 1);
				visited[i] = false;
			}
		}
	}

}
