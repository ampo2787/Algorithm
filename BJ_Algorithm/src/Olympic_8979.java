import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Olympic_8979 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();

		int[][] medal = new int[N][4];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 4; j++) {
				medal[i][j] = sc.nextInt();
			}
		}

		Arrays.sort(medal, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				// TODO Auto-generated method stub
				if (o1[1] < o2[1]) {
					return 1;
				} 
				else if (o1[1] == o2[1]) {
					if (o1[2] < o2[2]) {
						return 1;
					} 
					else if (o1[2] == o2[2]) {
						if (o1[3] < o2[3]) {
							return 1;
						} 
						else if(o1[3] == o2[3]) {
							return 0; // ���� -1 �� �ϸ� ��Ÿ�ӿ���.
						}
						else {
							return -1;
						}
					} 
					else {
						return -1;
					}
				} 
				else {
					return -1;
				}
			}
		});
		
		int stair = 1;

		for (int i = 0; i < N; i++) {
			if (medal[i][0] == K) {
				for (int j = i - 1; j >= 0; j--) {
					if (medal[i][1] == medal[j][1] && medal[i][2] == medal[j][2] && medal[i][3] == medal[j][3]) {
						continue;
					} else {
						stair = j + 2;
						i = N;
						break;
					}
				}
				if (i != N) {
					stair = i + 1;
				}
			}
		}
		System.out.println(stair);
		sc.close();
	}

}
