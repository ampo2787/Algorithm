import java.util.Scanner;

public class DiscTurn_17822 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,1,-1};

		int n = sc.nextInt();
		int m = sc.nextInt();
		int t = sc.nextInt();

		int[][] disc = new int[n][m];
		int[] x = new int[t];
		int[] d = new int[t];
		int[] k = new int[t];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				disc[i][j] = sc.nextInt();
			}
		}
		// 원판 마지막 수와 첫 번째 수는 인접.
		// 다른 원판 같은 위치 수는 인접.

		for (int i = 0; i < t; i++) {
			x[i] = sc.nextInt();
			d[i] = sc.nextInt();
			k[i] = sc.nextInt();
		}

		for (int i = 0; i < t; i++) {
			boolean turn = false;
			for (int j = 0; j < n; j++) {
				if ((j + 1) % x[i] == 0) {
					turn = true;
					if (d[i] == 0) {
						disc[j] = clock(disc[j], k[i]);
					} else {
						disc[j] = reverseClock(disc[j], k[i]);
					}
				}
			}
				if(turn) {
					boolean noAvg = false;
					boolean[][] visit = new boolean[n][m];

					for (int a = 0; a < n; a++) {
						int tempA = a % n;
						for (int b = 0; b < m; b++) {
							boolean same = false;
							int tempB = b % m;
							for(int c=0;c<4; c++) {
								int nx = dx[c] + tempA;
								int ny = dy[c] + tempB;
								if(nx < 0 || nx >= n) {
									continue;
								}

								if(ny < 0){
									ny = m-1;
								}
								else if(ny >= m) {
									ny=0;
								}

								if(disc[nx][ny] == disc[tempA][tempB]) {
									if(disc[nx][ny] != 0) {
										visit[nx][ny] = true;
										same = true;
									}
								}
							}
							if (same) {
								noAvg = true;
								visit[tempA][tempB] = true;
							}
						}

					}
					if (noAvg) {
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								if (visit[a][b] == true) {
									disc[a][b] = 0;
								}
							}
						}
					} else {
						int sum = 0;
						double avg = 0;
						for (int a = 0; a < n; a++) {
							for (int b = 0; b < m; b++) {
								if (disc[a][b] != 0) {
									sum += disc[a][b];
									avg++;
								}
							}
						}
						if(avg != 0) {
							avg = sum / avg;
							for (int a = 0; a < n; a++) {
								for (int b = 0; b < m; b++) {
									if (disc[a][b] != 0) {
										if (disc[a][b] < avg) {
											disc[a][b]++;
										} else if (disc[a][b] > avg) {
											disc[a][b]--;
										}
									}
								}
							}
						}
					}
				}

		}

		int sum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sum += disc[i][j];
			}
		}
		System.out.println(sum);
	}

	public static int[] clock(int[] arr, int move) {
		int[] temp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			temp[(i+move)%arr.length] = arr[i];
		}
		return temp;
	}

	public static int[] reverseClock(int[] arr, int move) {
		int[] temp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int abs = (i + arr.length - move % arr.length) % arr.length;
			temp[abs] = arr[i];
		}
		return temp;
	}

}
