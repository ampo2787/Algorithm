import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1507 {
    // 플로이드 역으로 활용
    // 모든 경로로 갈 수 있다고 문제에 정의됨 -> a 배열에 각 정점에 대해 모든 간선 연결
    // 최단 경로가 존재하면 i->j로 가는 간선은 없애버린다. i->k->j가 존재함으로써 i->j가 존재할 필요가 없음.

    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        int[][] dist = new int[n + 1][n + 1];
        int[][] a = new int[n + 1][n + 1];
        boolean[][] c = new boolean[n + 1][n + 1];
        int ans = 0;

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                int d = Integer.parseInt(st.nextToken());

                dist[i][j] = d;
                a[i][j] = d;
            }
        }
        // i->j 와 i -> k -> j 를 비교한다.

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    // 동일한 도시는 넘어간다.
                    if (i == k || j == k) {
                        continue;
                    }

                    // 주어진 최단 거리보다 다른 도시를 거쳐서 가는 경로가 더 짧다 -> 조건이 말이 안된다.
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        System.out.println(-1);
                        return;
                    }

                    // 다른 도시를 거쳐서 가는 것과 최단 경로가 같다. 직접 가는 경로는 필요가 없다.
                    if (dist[i][j] == dist[i][k] + dist[k][j]) {
                        a[i][j] = 0;
                    }
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 필요가 없다고 판단한 경로가 아니고, 이미 더한 경로가 아닐 경우를 다 더한다.
                if (a[i][j] != 0 && !c[i][j]) {
                    ans += a[i][j];

                    c[i][j] = true;
                    c[j][i] = true;
                }
            }

        }

        System.out.println(ans);

    }

}