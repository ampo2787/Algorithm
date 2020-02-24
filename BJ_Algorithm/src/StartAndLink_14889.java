import java.util.Scanner;

public class Main {
	static int N;
	static int[][] S;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		S = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				S[i][j] = sc.nextInt();
			}
		}

		comb(0, 0);
		System.out.println(min);
	}

	// 모든 팀의 조합 구하기
	static void comb(int start, int depth) {
		if (depth == N / 2) {
			min = Math.min(min, getAbilityDifference());
			return;
		}

		for (int i = start; i < N; i++) {
			if (visited[i] != true) {
				visited[i] = true;
				comb(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}

	// 팀의 능력치 차이를 구하기
	static int getAbilityDifference() {
		int sumStart = 0;
		int sumLink = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// true 면 스타트팀
				if (visited[i] && visited[j])
					sumStart += S[i][j];

				// false 면 링크팀
				if (visited[i] != true && visited[j] != true)
					sumLink += S[i][j];
			}
		}

		return Math.abs(sumStart - sumLink);
	}

}
