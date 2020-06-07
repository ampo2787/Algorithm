import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_4014 {
    static int N, X;
    static int[][] map;
    static int result;

    final static int[] dy = {0,0,-1,1};
    final static int[] dx = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 일단 가능한 모든 줄 수.
            result = N * N;

            // 1. 바로 옆의 높낮이가 2 이상인 줄은 제외한다.
            // 2. 높이 변화 후 X 길이만큼 평면이 되지 않고 높이가 변화하는 줄은 제외한다.
            // 3. 자신보다 낮은 높이가 있는데, 자신과 같은 높이가 두번 이상 떨어져 나오는 줄은 제외한다.

            // 가로, 세로 불가능 여부.
            boolean[] yVisit = new boolean[N];
            boolean[] xVisit = new boolean[N];

            // 1.
            for(int i=0; i<N; i++) {
                for(int j=1; j<N; j++) {
                    if(Math.abs(map[i][j] - map[i][j-1]) >= 2) {
                        yVisit[i] = true;
                        result--;
                    }

                    if(Math.abs(map[j][i] - map[j-1][i]) >= 2) {
                        xVisit[i] = true;
                        result--;
                    }
                }
            }

            // 2.
            for(int i=0; i<N; i++) {
                int thisLength = 1;
                for (int j = 0; j < N; j++) {

                }
            }

            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.print(sb);
    }

    public static void check(int y, int x, int dir) {
        int length = 1;

        while(y < 0 || y >= N || x < 0 || x >= N) {
            int ny = y + dy[dir];
            int nx = x + dx[dir];

            if(map[ny][nx] == map[y][x]) {
                length++;
                y = ny;
                x = nx;
            }
        }

        if(length < X) {
            return;
        }

        result += 1;
    }
}
