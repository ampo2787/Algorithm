import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class refrigerator_1828 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] temperature = new int[N][2];

		for (int i = 0; i < N; i++) {
			temperature[i][0] = sc.nextInt();
			temperature[i][1] = sc.nextInt();
		}

		// 최저를 기준으로 정렬한다.
		// 첫 최고 기온과 다음 것들의 최저 기온을 비교하면서 올라간다.
		// 최고 기온을 넘었다면, 그 것을 새로운 기준으로 잡고 올라간다.

		Arrays.sort(temperature, new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				return o1[1] - o2[1];
			}
		});

		int temp = temperature[0][1];
		int result = 1;

		for (int i = 1; i < N; i++) {
			if (temp >= temperature[i][0]) {
				continue;
			} else {
				temp = temperature[i][1];
				result++;
			}
		}
		System.out.println(result);
	}

}
