import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt();
		int n = sc.nextInt();
		int h = sc.nextInt();

		int[][][] tomato = new int[h][n][m];
		int solveT = 0;
		int day = 0;
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < m; k++) {
					tomato[i][j][k] = sc.nextInt();
					if (tomato[i][j][k] == 0) {
						solveT++;
					} else if (tomato[i][j][k] == 1) {
						int[] temp = { i, j, k };
						q.add(temp);
					}
				}
			}
		}
		int[] dm = { 0, 0, 1, -1, 0, 0 };
		int[] dn = { 1, -1, 0, 0, 0, 0 };
		int[] dh = { 0, 0, 0, 0, 1, -1 };

		if (solveT == 0) {
			System.out.println(day);
			System.exit(0);
		}
		while (!q.isEmpty()) {
			int tempSolve = solveT;
			ArrayList<int[]> list = new ArrayList<>();
			while (!q.isEmpty()) {
				list.add(q.poll());
			}

			for (int i = 0; i < list.size(); i++) {
				int hx = list.get(i)[0];
				int nx = list.get(i)[1];
				int mx = list.get(i)[2];
				for (int j = 0; j < 6; j++) {
					if (hx + dh[j] < 0 || hx + dh[j] >= h)
						continue;
					if (nx + dn[j] < 0 || nx + dn[j] >= n)
						continue;
					if (mx + dm[j] < 0 || mx + dm[j] >= m)
						continue;
					if (tomato[hx + dh[j]][nx + dn[j]][mx + dm[j]] == 0) {
						tomato[hx + dh[j]][nx + dn[j]][mx + dm[j]] = 1;
						solveT--;
						int[] temp = { hx + dh[j], nx + dn[j], mx + dm[j] };
						q.add(temp);
					}
				}
			}
			day++;
			if (solveT == 0) {
				System.out.println(day);
				break;
			}

			if (tempSolve == solveT) {
				System.out.println(-1);
				break;
			}

		}
	}

}
