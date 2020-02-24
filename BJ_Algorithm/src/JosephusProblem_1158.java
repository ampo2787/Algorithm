import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int k = sc.nextInt();

		LinkedList<Integer> list = new LinkedList<>();
		ArrayList<Integer> result = new ArrayList<>();

		for (int i = 1; i <= n; i++) {
			list.add(i);
		}

		int nextIndex = 0;

		while (!list.isEmpty()) {
			nextIndex = (nextIndex + k - 1) % list.size();
			result.add(list.remove(nextIndex));
		}

		System.out.print("<");
		for (int i = 0; i < result.size() - 1; i++) {
			System.out.print(result.get(i) + ", ");
		}
		System.out.println(result.get(result.size() - 1) + ">");
	}

}
