import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int p = sc.nextInt();
		int w = sc.nextInt();
		sc.nextLine();

		char[] line = sc.nextLine().toCharArray();
		int time = 0;
		int previousAski = -1;

		for (int i = 0; i < line.length; i++) {
			if (line[i] == ' ') {
				time += p;
				previousAski = -1;
				continue;
			}
			int aski = line[i] - 'A';
			if (aski < 3) {
				if (previousAski >= 0 && previousAski < 3) {
					time += w;
				}
			} else if (aski < 6) {
				if (previousAski >= 3 && previousAski < 6) {
					time += w;
				}
			} else if (aski < 9) {
				if (previousAski >= 6 && previousAski < 9) {
					time += w;
				}
			} else if (aski < 12) {
				if (previousAski >= 9 && previousAski < 12) {
					time += w;
				}
			} else if (aski < 15) {
				if (previousAski >= 12 && previousAski < 15) {
					time += w;
				}
			} else if (aski < 19) {
				if (previousAski >= 15 && previousAski < 19) {
					time += w;
				}
			} else if (aski < 22) {
				if (previousAski >= 19 && previousAski < 22) {
					time += w;
				}
			} else if (aski < 26) {
				if (previousAski >= 22 && previousAski < 26) {
					time += w;
				}
			}

			if (aski < 18) {
				time += (aski % 3 + 1) * p;
			} else if (aski == 18) {
				time += 4 * p;
			} else if (aski == 25){
				time += 4 * p;
			}
			else {
				time += ((aski - 1) % 3 + 1) * p;
			}
			previousAski = aski;
		}

		System.out.println(time);
	}
}
