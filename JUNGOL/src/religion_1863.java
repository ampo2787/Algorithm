import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class religion_1863 {
	static int[] arr;
	static int[] rank;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		arr = new int[n + 1];
		rank = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = i;
			rank[i] = 1;
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}

		ArrayList<Integer> list = new ArrayList<>();
		int result = 0;
		for (int i = 1; i <= n; i++) {
			int find = find(i);
			if (!list.contains((Object) find)) {
				list.add(find);
				result++;
			}
		}
		System.out.println(result);
	}

	public static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if (x == y) {
			return;
		}

		if (rank[x] < rank[y]) {
			arr[x] = y;
		} else {
			arr[y] = x;

			if (rank[x] == rank[y])
				rank[x]++;
		}
	}

	public static int find(int x) {
		if (arr[x] == x) {
			return x;
		} else {
			return arr[x] = find(arr[x]);
		}
	}

}
