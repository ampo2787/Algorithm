import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();
		int time = 0;
		boolean[] previous = new boolean[100001];
		previous[n] = true;

		Queue<Integer> q = new LinkedList<>();
		q.add(n);

		while (!q.isEmpty()) {
			ArrayList<Integer> list = new ArrayList<>();

			while (!q.isEmpty()) {
				list.add(q.poll());
			}

			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == k) {
					System.out.println(time);
					System.exit(0);
				}
				if (list.get(i) + 1 < 100001 && !previous[list.get(i) + 1]) {
					q.add(list.get(i) + 1);
					previous[list.get(i) + 1] = true;
				}
				if (list.get(i) - 1 > -1 && !previous[list.get(i) - 1]) {
					q.add(list.get(i) - 1);
					previous[list.get(i) - 1] = true;
				} 
				if (list.get(i) * 2 < 100001 && !previous[list.get(i) * 2]) {
					q.add(list.get(i) * 2);
					previous[list.get(i) * 2] = true;
				}
			}
			time++;
		}
	}

}
