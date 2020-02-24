import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int N = Integer.parseInt(br.readLine());
			Node head = null;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken());
				char data = st.nextToken().charAt(0);
				Node left = null;
				Node right = null;

				if (st.hasMoreTokens()) {
					left = new Node(Integer.parseInt(st.nextToken()), 'a', null, null);
				}

				if (st.hasMoreTokens()) {
					right = new Node(Integer.parseInt(st.nextToken()), 'a', null, null);
				}

				if (head == null) {
					head = new Node(index, data, left, right);
				} else {
					Node findNode = findNode(head, index);
					findNode.data = data;
					findNode.left = left;
					findNode.right = right;
				}
			}

			char result = calculate(head);
			if (result == '0') {
				System.out.println("#" + test_case + " " + 0);
			} else {
				System.out.println("#" + test_case + " " + 1);
			}
		}
	}

	public static char calculate(Node node) {
		if (node.data <= '9' && node.data >= '1') {
			if (node.left == null && node.right == null)
				return '1';
			else
				return '0';
		} else {
			switch (node.data) {
			case '*':
			case '/':
			case '+':
			case '-':
				char leftC = calculate(node.left);
				char rightC = calculate(node.right);
				if (leftC > '9' || leftC < '1') {
					return '0';
				}
				if (rightC > '9' || rightC < '1') {
					return '0';
				}
			default:
				return '1';
			}
		}
	}

	public static Node findNode(Node node, int index) {
		if (node.index == index) {
			return node;
		}
		Node left = null;
		Node right = null;

		if (node.left != null) {
			left = findNode(node.left, index);
		}

		if (node.right != null) {
			right = findNode(node.right, index);
		}

		return left == null ? right : left;
	}

	static class Node {
		int index;
		char data;
		Node left;
		Node right;

		public Node(int index, char data, Node left, Node right) {
			this.index = index;
			this.data = data;
			this.left = left;
			this.right = right;
		}
	}

}
