import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Snake_3190 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		int[][] map = new int[N + 1][N + 1];
		map[1][1] = 1; // ��.

		for (int i = 0; i < K; i++) {
			map[sc.nextInt()][sc.nextInt()] = 2;
		}

		int L = sc.nextInt();
		int[] direction = new int[10001];
		// 1�� ���������� 90��, 0�� �״��, -1�� �������� 90��.

		for (int i = 0; i < L; i++) {
			int idx = sc.nextInt();
			if (sc.next().equals("D")) {
				direction[idx] = 1;
			} else {
				direction[idx] = -1;
			}
		}

		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };
		int myDirection = 0;
		int ny = 1;
		int nx = 1;
		int time = 0;
		Queue<int[]> snake = new LinkedList<>();
		int[] start = { ny, nx };
		snake.add(start);
		// �� ���� 0, �� 1, ��� 2.
		while (true) {
			/*
			 * for (int i = 1; i <= N; i++) { for (int j = 1; j <= N; j++) {
			 * System.out.print(map[i][j] + " "); } System.out.println(); }
			 * System.out.println(time); System.out.println();
			 */
			myDirection = myDirection + direction[time++];
			if (myDirection > 3) {
				myDirection = 0;
			}
			if (myDirection < 0) {
				myDirection = 3;
			}
			ny = ny + dy[myDirection];
			nx = nx + dx[myDirection];

			if (ny < 1 || ny >= N + 1 || nx < 1 || nx >= N + 1) {
				break;
			}

			if (map[ny][nx] == 1) {
				break;
			}

			int[] head = { ny, nx };
			if (map[ny][nx] == 2) {
				map[ny][nx] = 1;
				snake.add(head);
			} else if (map[ny][nx] == 0) {
				int[] tail = snake.poll();
				snake.add(head);
				map[tail[0]][tail[1]] = 0;
				map[ny][nx] = 1;
			}
		}

		System.out.println(time);

	}

}
