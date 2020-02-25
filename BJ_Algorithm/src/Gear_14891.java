import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Gear_14891 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int[][] gear = new int[4][8];
		boolean[] visit = new boolean[4];

		for (int i = 0; i < 4; i++) {
			char[] temp = sc.nextLine().toCharArray();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = temp[j] - '0';
			}
		}

		int k = sc.nextInt();
		int[] moveGear = new int[k];
		int[] moveDirection = new int[k];
		Queue<Integer> queue = new LinkedList<Integer>();
		Queue<Integer> moveq = new LinkedList<Integer>();

		for (int i = 0; i < k; i++) {
			moveGear[i] = sc.nextInt() - 1;
			moveDirection[i] = sc.nextInt();
		}

		for (int i = 0; i < k; i++) {
			queue.add(moveGear[i]);
			moveq.add(moveDirection[i]);
			Arrays.fill(visit, false);
			visit[moveGear[i]] = true;

			while (!queue.isEmpty()) {
				int nextGear = queue.poll();
				int nextMove = moveq.poll();
				int left = gear[nextGear][6];
				int right = gear[nextGear][2];

				if (nextGear - 1 >= 0) {
					if (!visit[nextGear - 1] && left != gear[nextGear - 1][2]) {
						queue.add(nextGear - 1);
						if (nextMove == 1) {
							moveq.add(0);
						} else {
							moveq.add(1);
						}
						visit[nextGear - 1] = true;
					}
				}

				if (nextGear + 1 < 4) {
					if (!visit[nextGear + 1] && right != gear[nextGear + 1][6]) {
						queue.add(nextGear + 1);
						if (nextMove == 1) {
							moveq.add(0);
						} else {
							moveq.add(1);
						}
						visit[nextGear + 1] = true;
					}
				}

				if (nextMove == 1) {
					clock(gear[nextGear]);
				} else {
					reverseClock(gear[nextGear]);
				}
			}
		}
		int result = 0;
		for (int i = 0; i < 4; i++) {
			if (gear[i][0] == 1) {
				result += 1 << i;
			}
		}
		System.out.println(result);

	}

	public static void clock(int[] arr) {
		int temp = arr[arr.length - 1];
		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = temp;
	}

	public static void reverseClock(int[] arr) {
		int temp = arr[0];
		for (int i = 1; i < arr.length; i++) {
			arr[i - 1] = arr[i];
		}
		arr[arr.length - 1] = temp;
	}
}
