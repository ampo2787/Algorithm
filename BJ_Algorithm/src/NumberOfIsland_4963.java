import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

		while (true) {
			int w = sc.nextInt();
			int h = sc.nextInt();
			if (w == 0 && h == 0) {
				sc.close();
				break;
			}
			int[][] map = new int[h][w];

			ArrayList<int[]> islandList = new ArrayList<>();

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					map[i][j] = sc.nextInt();
					if (map[i][j] == 1) {
						int[] temp = { i, j };
						islandList.add(temp);
					}
				}
			}

			Queue<int[]> queue = new LinkedList<>();
			boolean[][] visit = new boolean[h][w];
			int result = 0;

			for (int i = 0; i < islandList.size(); i++) {
				int[] thisIsland = islandList.get(i);

				if (!visit[thisIsland[0]][thisIsland[1]]) {
					queue.add(islandList.get(i));
					visit[thisIsland[0]][thisIsland[1]] = true;
					result++;
					while (!queue.isEmpty()) {
						int[] myIsland = queue.poll();
						for (int j = 0; j < 8; j++) {
							int ny = myIsland[0] + dy[j];
							int nx = myIsland[1] + dx[j];
							if (ny < 0 || ny >= h || nx < 0 || nx >= w) {
								continue;
							}

							if (map[ny][nx] == 1) {
								if (!visit[ny][nx]) {
									int[] temp = { ny, nx };
									visit[ny][nx] = true;
									queue.add(temp);
								}
							}
						}
					}
				}
			}
			System.out.println(result);
		}
	}

}
