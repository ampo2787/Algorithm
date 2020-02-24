import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] dx = { 0, 0, -1, 1 };
		int[] dy = { 1, -1, 0, 0 };
		sc.nextLine();

		int[][] map = new int[n][m];
		int[][] visit = new int[n][m];

		ArrayList<int[]> wall = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			String temp = sc.nextLine();
			for (int j = 0; j < temp.length(); j++) {
				map[i][j] = temp.charAt(j) - 48;
				visit[i][j] = Integer.MAX_VALUE;
			}
		}

		int ans = Integer.MAX_VALUE;

		Queue<Place> q = new LinkedList<>();

		q.add(new Place(0, 0, 1, 0));
		visit[0][0] = 0;

		while (!q.isEmpty()) {
			ArrayList<Place> list = new ArrayList<>();

			while (!q.isEmpty()) {
				list.add(q.poll());
			}

			for (int a = 0; a < list.size(); a++) {
				Place p = list.get(a);
				
				if (p.x == n - 1 && p.y == m - 1) {
					ans = Math.min(ans, p.dis);
					break;
				}

				for (int i = 0; i < 4; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (ny < 0 || nx < 0 || ny >= m || nx >= n) {
						continue;
					}

					if (visit[nx][ny] <= p.drill) {
						continue;
					}

					if (map[nx][ny] == 0) {
						visit[nx][ny] = p.drill;
						q.add(new Place(nx, ny, p.dis + 1, p.drill));
					} else {
						if (p.drill == 0) {
							visit[nx][ny] = p.drill + 1;
							q.add(new Place(nx, ny, p.dis + 1, p.drill + 1));
						}
					}
				}
			}
		}

		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);

	}
}

class Place {
	int x;
	int y;
	int dis;
	int drill;

	public Place(int x, int y, int dis, int drill) {
		this.x = x;
		this.y = y;
		this.dis = dis;
		this.drill = drill;
	}
}
