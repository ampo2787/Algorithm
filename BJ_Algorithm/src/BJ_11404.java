import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11404 {
    public static int cityCount;
    public static int[][] distance;
    public static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        cityCount = Integer.parseInt(br.readLine()); // N
        int busCount = Integer.parseInt(br.readLine()); // M

        distance = new int[cityCount + 1][cityCount + 1];

        // 모든 경로를 INF 로 초기화.
        for (int i = 1; i <= cityCount ; i++) {
            for (int j= 1; j <= cityCount; j++) {
                if(i == j)
                    continue;
                distance[i][j] = INF;
            }
        }

        for(int i = 0; i < busCount; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 인접한 경로들만 일단 업데이트.
            distance[a][b] = Math.min(distance[a][b], c);
        }

        floydWarshall();

        StringBuilder sb = new StringBuilder();

        for (int i=1; i<=cityCount; i++) {
            for(int j=1; j<= cityCount; j++) {
                // 접근할 수 없느 도시 -> 0 출력.
                if(distance[i][j] >= INF)
                    sb.append("0 ");
                else
                    sb.append(distance[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void floydWarshall() {
        for(int k = 1; k <= cityCount; k++) {
            for(int i=1; i<= cityCount; i++) {
                for(int j=1; j<=cityCount; j++) {
                    // i 에서 k 를 거쳐 j로 가능 방법과, i에서 j 로 가는 거리를 비교.
                    int i_k_j = distance[i][k] + distance[k][j];
                    distance[i][j] = Math.min(i_k_j, distance[i][j]);
                }
            }
        }
    }
}
