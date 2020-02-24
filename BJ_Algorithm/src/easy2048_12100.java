import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, -1, 0, 1 };

	static int n;
	static int max;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int[][] board = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		max = 0;
		Queue<int[][]> queue = new LinkedList<>();
		queue.add(board);
		int index = 1;

		while (!queue.isEmpty() && index <= 6) {

			ArrayList<int[][]> list = new ArrayList<>();
			while (!queue.isEmpty()) {
				list.add(queue.poll());
			}

			for (int a = 0; a < list.size(); a++) {
				//System.out.println(index);
				check(list.get(a));
				if (index == 6) {
					continue;
				}

				int[][] tempBoard = list.get(a);
				int[][] newBoard = new int[n][n];
				// 4방향 경우의 수
				// 1 왼쪽
				for (int j = 0; j < n; j++) {
					Stack<Integer> stack = new Stack<>();
					for (int k = 0; k < n; k++) {
						if (tempBoard[j][k] != 0) {
							stack.push(tempBoard[j][k]);
						}
					}
					if (!stack.isEmpty()) {
						int stackIndex = 0;
						int temp = stack.pop();

						while (!stack.isEmpty()) {
							int thisPop = stack.pop();
							if (thisPop == temp) {
								newBoard[j][n - 1 - (stackIndex++)] = temp * 2;
								if (!stack.isEmpty()) {
									thisPop = stack.pop();
									temp = thisPop;
								} else {
									temp = 0;
								}
							} else {
								newBoard[j][n - 1 - (stackIndex++)] = temp;
								temp = thisPop;
							}
						}
						if (temp != 0) {
							newBoard[j][n - 1 - (stackIndex++)] = temp;
						}
					}
				}
				queue.add(newBoard);
				// 2 오른쪽
				newBoard = new int[n][n];
				for (int j = 0; j < n; j++) {
					Stack<Integer> stack = new Stack<>();
					for (int k = n - 1; k >= 0; k--) {
						if (tempBoard[j][k] != 0) {
							stack.push(tempBoard[j][k]);
						}
					}
					if (!stack.isEmpty()) {
						int stackIndex = 0;
						int temp = stack.pop();
						while (!stack.isEmpty()) {
							int thisPop = stack.pop();
							if (thisPop == temp) {
								newBoard[j][(stackIndex++)] = temp * 2;
								if (!stack.isEmpty()) {
									temp = stack.pop();
								} else {
									temp = 0;
								}
							} else {
								newBoard[j][(stackIndex++)] = temp;
								temp = thisPop;
							}
						}
						if (temp != 0) {
							newBoard[j][(stackIndex++)] = temp;
						}
					}
				}
				queue.add(newBoard);
				// 3 위쪽
				newBoard = new int[n][n];
				for (int k = 0; k < n; k++) {
					Stack<Integer> stack = new Stack<>();
					for (int j = 0; j < n; j++) {
						if (tempBoard[j][k] != 0) {
							stack.push(tempBoard[j][k]);
						}
					}
					if (!stack.isEmpty()) {
						int stackIndex = 0;
						int temp = stack.pop();
						while (!stack.isEmpty()) {
							int thisPop = stack.pop();
							if (thisPop == temp) {
								newBoard[n - 1 - (stackIndex++)][k] = temp * 2;
								if (!stack.isEmpty()) {
									temp = stack.pop();
								} else {
									temp = 0;
								}
							} else {
								newBoard[n - 1 - (stackIndex++)][k] = temp;
								temp = thisPop;
							}
						}
						if (temp != 0)
							newBoard[n - 1 - (stackIndex++)][k] = temp;
					}
				}
				queue.add(newBoard);
				// 4 아래쪽
				newBoard = new int[n][n];
				for (int k = 0; k < n; k++) {
					Stack<Integer> stack = new Stack<>();
					for (int j = n - 1; j >= 0; j--) {
						if (tempBoard[j][k] != 0) {
							stack.push(tempBoard[j][k]);
						}
					}
					if (!stack.isEmpty()) {
						int stackIndex = 0;
						int temp = stack.pop();
						while (!stack.isEmpty()) {
							int thisPop = stack.pop();
							if (thisPop == temp) {
								newBoard[(stackIndex++)][k] = temp * 2;
								if (!stack.isEmpty()) {
									temp = stack.pop();
								} else {
									temp = 0;
								}
							} else {
								newBoard[(stackIndex++)][k] = temp;
								temp = thisPop;
							}
						}
						if (temp != 0)
							newBoard[(stackIndex++)][k] = temp;
					}
				}

				queue.add(newBoard);
			}
			index++;
		}

		System.out.println(max);
	}

	public static void check(int[][] list) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				max = Math.max(list[i][j], max);
				//System.out.print(list[i][j] + " ");
			}
			//System.out.println();
		}
		//System.out.println();

	}
}
