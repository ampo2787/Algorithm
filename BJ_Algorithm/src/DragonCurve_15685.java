import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int x;
		int y;
		int d;
		int g;

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };
		boolean[][] map = new boolean[101][101];

		for (int i = 0; i < N; i++) {
			x = sc.nextInt();
			y = sc.nextInt();
			d = sc.nextInt();
			g = sc.nextInt();
			map[y][x] = true;
			// 0세대
			ArrayList<Integer> list = new ArrayList<>();
			list.add(d);

			for (int j = 1; j <= g; j++) {
				int size = list.size();

				for (int k = size - 1; k >= 0; k--) {
					list.add((list.get(k) + 1) % 4);
				}
			}

			for (int j = 0; j < list.size(); j++) {
				y = y + dy[list.get(j)];
				x = x + dx[list.get(j)];
				map[y][x] = true;
			}
		}
		int sum = 0;

		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == true && map[i + 1][j] == true && map[i][j + 1] == true && map[i + 1][j + 1] == true)
					sum++;
			}
		}
		System.out.println(sum);

	}

}
