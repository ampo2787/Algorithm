import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		for (int test_case = 1; test_case <= 10; test_case++) {
			int length = sc.nextInt();
			int startPoint = sc.nextInt();

			ArrayList<Integer>[] contact = new ArrayList[101];
			
			for (int i = 0; i < 101; i++) {
				contact[i] = new ArrayList<>();
			}

			for (int i = 0; i < length / 2; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if (!contact[a].contains(b)) {
					contact[a].add(b);
				}
			}

			boolean[] visit = new boolean[101];
			Queue<Integer> queue = new LinkedList<>();
			visit[startPoint] = true;
			queue.add(startPoint);
			ArrayList<Integer> thisList = new ArrayList<>();

			while (!queue.isEmpty()) {
				thisList = new ArrayList<>();
				while (!queue.isEmpty()) {
					thisList.add(queue.poll());
				}
				for (int i = 0; i < thisList.size(); i++) {
					int thisPerson = thisList.get(i);

					for (int j = 0; j < contact[thisPerson].size(); j++) {
						if (!visit[contact[thisPerson].get(j)]) {
							queue.add(contact[thisPerson].get(j));
							visit[contact[thisPerson].get(j)] = true;
						}
					}
				}
			}
			int result = 0;
			for (int i = 0; i < thisList.size(); i++) {
				result = Math.max(thisList.get(i), result);
			}

			System.out.println("#" + test_case + " " + result);
		}
		sc.close();

	}

}
