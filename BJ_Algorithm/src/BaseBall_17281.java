import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BaseBall_17281 {
	static int n;
	static int[][] score;
	static boolean[] visit;
	static int max = 0;
	static ArrayList<int[]> gameList = new ArrayList();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();

		score = new int[n + 1][10];
		visit = new boolean[10];

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < 10; j++) {
				score[i][j] = sc.nextInt();
			}
		}

		// 타순으로 간다. 이닝을 따로 카운트한다. n+1 일 때 종료한다.
		// 4번 타자는 무조건 1번 선수이다.
		// 저번 이닝에 타격할 번호(ex : 7번 타자) 를 함께 넘긴다.
		int[] arr = new int[10];
		tasun(1, arr);

		for (int i = 0; i < gameList.size(); i++) {
			int outCount = 0;
			int inning = 1;
			int myScore = 0;
			boolean[] lu = new boolean[4];

			int[] thisList = gameList.get(i);
			int index = 1;

			while (inning != (n + 1)) {
				if (score[inning][thisList[index]] == 0) {
					outCount++;
					if (outCount == 3) {
						outCount = 0;
						inning++;
						Arrays.fill(lu, false);
					}
				} else {
					myScore += goLu(lu, score[inning][thisList[index]]);
				}
				index++;
				if (index == 10) {
					index = 1;
				}
			}
			max = Math.max(myScore, max);
		}
		System.out.println(max);
	}

	public static void tasun(int index, int[] arr) {
		if (index == 10) {
			gameList.add(arr.clone());
			return;
		}
		if (index == 4) {
			arr[index] = 1;
			tasun(index + 1, arr);
			return;
		}

		for (int i = 2; i < 10; i++) {
			if (!visit[i]) {
				visit[i] = true;
				arr[index] = i;
				tasun(index + 1, arr);
				visit[i] = false;
			}
		}
	}

	public static int goLu(boolean[] lu, int hit) {
		int score = 0;

		switch (hit) {
		case 0:
			break;
		case 1:
		case 2:
		case 3:
			for (int i = 3; i > 0; i--) {
				if (lu[i] == true) {
					if (i + hit >= 4) {
						score++;
						lu[i] = false;
					} else {
						lu[i] = false;
						lu[i + hit] = true;
					}
				}
			}
			lu[hit] = true;
			break;
		case 4:
			for (int i = 1; i < 4; i++) {
				if (lu[i] == true) {
					score++;
					lu[i] = false;
				}
			}
			score++;
			break;
		}
		return score;

	}

}
