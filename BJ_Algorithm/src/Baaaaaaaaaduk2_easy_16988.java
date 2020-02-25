import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Baaaaaaaaaduk2_easy_16988 {
	static int n, m;
	static int[][] map;
	static ArrayList<int[]> list = new ArrayList();
	static ArrayList<int[]> enemyList = new ArrayList();
	static int max = 0;

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new int[n][m];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = sc.nextInt();
				int[] temp = { i, j };
				if (map[i][j] == 2) {
					enemyList.add(temp);
				}
				if (map[i][j] == 0) {
					list.add(temp);
				}
			}
		}

//		for (int i = 0; i < n; i++) {
//			for (int j = 0; j < m; j++) {
//				if (map[i][j] == 0) {
//					int zeroNum = 0;
//					for (int k = 0; k < 4; k++) {
//						int nx = dx[k] + i;
//						int ny = dy[k] + j;
//						if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
//							continue;
//						}
//						if (map[nx][ny] == 0) {
//							zeroNum++;
//						}
//					}
//					if (zeroNum < 4) {
//						int[] temp = { i, j };
//						list.add(temp);
//					}
//				}
//			}
//		}
		recursive(0);

		System.out.println(max);
	}

	public static void recursive(int index) {
		if (index == 2) {
			max = Math.max(max, check());
			return;
		}

		for (int i = 0; i < list.size(); i++) {
			if (map[list.get(i)[0]][list.get(i)[1]] != 1) {
				map[list.get(i)[0]][list.get(i)[1]] = 1;
				recursive(index + 1);
				map[list.get(i)[0]][list.get(i)[1]] = 0;
			}
		}
	}

	public static int check() {
		boolean[][] checkMap = new boolean[n][m];

		Queue<int[]> q = new LinkedList<>();
		int result = 0;

		for (int a = 0; a < enemyList.size(); a++) {
			int x = enemyList.get(a)[0];
			int y = enemyList.get(a)[1];
			int sum = 0;
			boolean isNext = true;
			if (checkMap[x][y] == false) {
				int[] temp = { x, y };
				q.add(temp);
				checkMap[x][y] = true;
				while (!q.isEmpty()) {
					ArrayList<int[]> nextList = new ArrayList<>();
					while (!q.isEmpty()) {
						nextList.add(q.poll());
						if (isNext) {
							sum++;
						}
					}
					for (int k = 0; k < nextList.size(); k++) {
						x = nextList.get(k)[0];
						y = nextList.get(k)[1];

						for (int i = 0; i < 4; i++) {
							int nx = dx[i] + x;
							int ny = dy[i] + y;
							if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
								continue;
							}

							if (map[nx][ny] == 0) {
								sum = 0;
								k = nextList.size();
								isNext = false;
							} else if (map[nx][ny] == 2) {
								if (checkMap[nx][ny] == false) {
									int[] next = { nx, ny };
									q.add(next);
									checkMap[nx][ny] = true;
								}
							}
						}

					}

				}
			}
			result = sum + result;
		}
		return result;
	}

}
