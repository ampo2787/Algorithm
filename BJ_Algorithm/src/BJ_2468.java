import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2468 {
    final static int[] dy = {1,-1,0,0};
    final static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        int maxHeight = 0;
        int result = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(map[i][j], maxHeight);
            }
        }

        for(int h = 0; h < maxHeight; h++) {
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visit = new boolean[N][N];

            int safeZone = 0;

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {

                    if(map[i][j] > h && !visit[i][j]) {

                        int[] temp = {i,j};
                        queue.add(temp);
                        safeZone++;
                        visit[i][j] = true;

                        while(!queue.isEmpty()) {
                            int[] item = queue.poll();

                            for(int d=0; d<4; d++) {
                                int ny = dy[d] + item[0];
                                int nx = dx[d] + item[1];

                                if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
                                    continue;
                                }

                                if(map[ny][nx] > h && !visit[ny][nx]) {
                                    int[] nextItem = {ny, nx};
                                    visit[ny][nx] = true;
                                    queue.add(nextItem);
                                }
                            }
                        }
                    }
                }
            }

            result = Math.max(safeZone, result);
        }

        System.out.println(result);
    }
}
