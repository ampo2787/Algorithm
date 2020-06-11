import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11403 {
    static final int INF = 100000000;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        dist = new int[N][N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                dist[i][j] = Integer.parseInt(st.nextToken());

                if(dist[i][j] == 0) {
                    dist[i][j] = INF;
                }
            }
        }



        // 모든 정점 (i,j) 에 대해서 i 에서 j로 가는 경로가 있는지 없는지 구하는 프로그램을 작성하시오.
        // i -> j 와 i -> k -> j 를 비교한다.

        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    int ijk = dist[i][k] + dist[k][j];

                    dist[i][j] = Math.min(ijk, dist[i][j]);
                }
            }
        }

        for(int i=0; i<N; i++) {
            for (int j = 0; j < N; j++) {
                if(dist[i][j] >= INF) {
                    sb.append(0 + " ");
                }
                else {
                    sb.append(1 + " ");
                }
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}
