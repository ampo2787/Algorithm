import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Password_1234 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			int N = Integer.parseInt(st.nextToken());
			char[] line = st.nextToken().toCharArray();
			boolean[] visit = new boolean[N];

			for (int i = 1; i < N; i++) {
				int a = i - 1;
				int b = i;
				while (a >= 0 && visit[a] == true) {
					a--;
				}
				while (b < N && visit[b] == true) {
					b++;
				}
				check(a, b, line, visit);
			}

			System.out.print("#" + test_case + " ");
			for (int i = 0; i < N; i++) {
				if (!visit[i]) {
					System.out.print(line[i]);
				}
			}
			System.out.println();
		}
	}

	public static void check(int a, int b, char[] line, boolean[] visit) {
		while (a >= 0 && visit[a] == true) {
			a--;
		}
		while (b < line.length && visit[b] == true) {
			b++;
		}
		if (a < 0 || b >= line.length) {
			return;
		}
		if (line[a] == line[b]) {
			visit[a] = true;
			visit[b] = true;
			check(a - 1, b + 1, line, visit);
		}
	}

}
