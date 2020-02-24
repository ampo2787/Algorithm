import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		sc.nextLine();
		char[] seat = sc.nextLine().toCharArray();
		int cupHolder = 1;
		
		for(int i=0; i<seat.length; i++) {
			if(seat[i] == 'S') {
				cupHolder++;
			}
			else {
				cupHolder++;
				i++;
			}
		}
		
		if(N < cupHolder) {
			cupHolder = N;
		}
		
		System.out.println(cupHolder);
		sc.close();
	}

}
