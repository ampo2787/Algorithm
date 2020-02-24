import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();
		int[] x = new int[N];
		int[] y = new int[N];
		
		for(int i=0; i<N; i++) {
			x[i] = sc.nextInt();
			y[i] = sc.nextInt();
		}
		
		int[] score = new int[N];
		Arrays.fill(score, 1);
		
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(x[i] > x[j] && y[i] > y[j]) {
					score[j]++;
				}
				else if(x[i] < x[j] && y[i] < y[j]) {
					score[i]++;
				}
				else {
				}
			}
		}
				
		for(int i=0; i<N; i++) {
			sb.append(score[i] + " ");
		}
		
		
		System.out.print(sb);
		
	}

}
