import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T;
		T = sc.nextInt();
		int[] dy = { -1, 0, 1, 0 };
		int[] dx = { 0, 1, 0, -1 };

		for (int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt();
			int result = 0;
			int startRoom = 0;

			int[][] map = new int[N][N];
			Queue<int[]> queue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int[] temp = { i, j };
					queue.add(temp);
					int moveRoom = 0;
					
					while (!queue.isEmpty()) {
						ArrayList<int[]> thisTimeRoomList = new ArrayList();
						while (!queue.isEmpty()) {
							thisTimeRoomList.add(queue.poll());
						}
						moveRoom++;
						for (int a = 0; a < thisTimeRoomList.size(); a++) {
							int[] myroom = thisTimeRoomList.get(a);
							for (int k = 0; k < 4; k++) {
								int ny = myroom[0] + dy[k];
								int nx = myroom[1] + dx[k];
								if (ny < 0 || ny >= N || nx < 0 || nx >= N) {
									continue;
								}
								if (map[ny][nx] == map[myroom[0]][myroom[1]] + 1) {
									int[] nextRoom = { ny, nx };
									queue.add(nextRoom);
								}
							}
						}
					}

					if (result < moveRoom) {
						result = moveRoom;
						startRoom = map[i][j];
					} else if (result == moveRoom) {
						if (startRoom > map[i][j]) {
							startRoom = map[i][j];
						}
					}
				}
			}

			System.out.println("#" + test_case + " " + startRoom + " " + result);
		}
	}
}
