import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		char[] a = sc.next().toCharArray();
		char[] b = sc.next().toCharArray();

		int aIndex = 0;
		int bIndex = 0;

		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b.length; j++) {
				if (a[i] == b[j]) {
					aIndex = i;
					bIndex = j;
					i = a.length;
					break;
				}
			}
		}

		char[][] result = new char[b.length][a.length];

		for (int i = 0; i < b.length; i++) {
			result[i][aIndex] = b[i];
		}

		for (int i = 0; i < a.length; i++) {
			result[bIndex][i] = a[i];
		}

		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < a.length; j++) {
				if (result[i][j] < 'A' || result[i][j] > 'Z') {
					System.out.print(".");
				} else {
					System.out.print(result[i][j]);
				}
			}
			System.out.println();
		}
	}

}
