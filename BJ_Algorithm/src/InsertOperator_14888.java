import java.util.Scanner;

public class InsertOperator_14888 {
	static int N;
	static int[] A;
	static int[] oper;
	static char[] myOper = { '+', '-', '*', '/' };
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int temp = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		A = new int[N];
		oper = new int[4];

		for (int i = 0; i < N; i++) {
			A[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			oper[i] = sc.nextInt();
		}
		temp += A[0];

		dfs(1);
		System.out.println(max);
		System.out.println(min);

	}

	public static void dfs(int index) {
		if (index == N) {
			if (temp < min) {
				min = temp;
			}

			if (temp > max) {
				max = temp;
			}
			return;
		}
		int myTemp = temp;
		for (int i = 0; i < 4; i++) {
			if (oper[i] != 0) {
				oper[i]--;
				if (i == 0) {
					temp += A[index];
				} else if (i == 1) {
					temp -= A[index];
				} else if (i == 2) {
					temp *= A[index];
				} else {
					temp /= A[index];
				}
				dfs(index+1);
				temp = myTemp;
				oper[i]++;
			}
		}

	}

}
