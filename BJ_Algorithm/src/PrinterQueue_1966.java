import java.util.Scanner;

public class PrinterQueue_1966 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int test_case = sc.nextInt();

		for (int t = 1; t <= test_case; t++) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[] prior = new int[n];
			int maxPrior = 0;
			int result = 0;
			boolean[] visit = new boolean[n];

			for (int i = 0; i < n; i++) {
				prior[i] = sc.nextInt();
				maxPrior = Math.max(prior[i], maxPrior);
			}

			boolean print = false;

			while (!print) {
				for (int i = 0; i < n; i++) {
					if (prior[i] < maxPrior) {
						continue;
					} else {
						if (!visit[i]) {
							result++;
							visit[i] = true;
							maxPrior = 0;
							for (int j = 0; j < n; j++) {
								if (!visit[j])
									maxPrior = Math.max(prior[j], maxPrior);
							}

							if (i == m) {
								print = true;
								break;
							}
						}
					}

				}
			}

			System.out.println(result);
			result = 0;
		}
	}

}
