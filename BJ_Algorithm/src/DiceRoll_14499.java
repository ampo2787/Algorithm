import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int N = sc.nextInt();
		int M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int k = sc.nextInt();

		// x,y 가 주사위 위치.
		// 윗 면이 1이고, 오른쪽이 3인 상태.
		// 왼쪽 4, 위 2, 아래 5, 밑면 6.
		// 지도는 위쪽이 북쪽, 오른쪽이 동쪽.
		int[][] map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// 처음 상태는 2가 북쪽을 바라보고 있다.
		// 1,4,6,3;
		// 1,5,6,2;
		int diceTop = 0;
		int[] diceArr = { 0, 0, 0, 0 };
		int diceBottom = 0;

		int[] dx = { 0, 0, 0, -1, 1 };
		int[] dy = { 0, 1, -1, 0, 0 };
		int nx = x;
		int ny = y;
		int order;
		for (int i = 0; i < k; i++) {
			order = sc.nextInt();
			nx = dx[order] + nx;
			ny = dy[order] + ny;

			if (ny < 0 || ny >= M || nx < 0 || nx >= N) {
				nx = nx - dx[order];
				ny = ny - dy[order];
				continue;
			}
			int temp;
			switch (order) {
			case 1:
				temp = diceTop;
				diceTop = diceArr[3];
				diceArr[3] = diceBottom;
				diceBottom = diceArr[1];
				diceArr[1] = temp;
				break;
			case 2:
				temp = diceTop;
				diceTop = diceArr[1];
				diceArr[1] = diceBottom;
				diceBottom = diceArr[3];
				diceArr[3] = temp;
				break;
			case 3:
				temp = diceTop;
				diceTop = diceArr[2];
				diceArr[2] = diceBottom;
				diceBottom = diceArr[0];
				diceArr[0] = temp;
				break;
			case 4:
				temp = diceTop;
				diceTop = diceArr[0];
				diceArr[0] = diceBottom;
				diceBottom = diceArr[2];
				diceArr[2] = temp;
				break;
			}

			if (map[nx][ny] == 0) {
				map[nx][ny] = diceBottom;
			} else {
				diceBottom = map[nx][ny];
				map[nx][ny] = 0;
			}

			sb.append(diceTop + "\n");
		}
		System.out.println(sb);
	}
}
