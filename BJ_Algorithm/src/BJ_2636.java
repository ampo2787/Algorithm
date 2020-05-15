import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2636 {
    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        int cheese = 0;
        int priviousCheese = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cheese++;
                }
            }
        }

        // BFS로 바깥의 0을 2로 써서 구분한다.
        // 바깥에서 시간마다 치즈를 지운다.
        // 치즈를 지웠을 때, 4방향으로 돌아서 0을 만나면 그 인접한 모든 0을 2로 바꾼다.

        int[] start = {0,0};
        Queue<int[]> queue = new LinkedList<>();
        map[0][0] = 2;
        queue.add(start);

        while(!queue.isEmpty()) {
            int[] item  = queue.poll();

            for(int d=0; d<4; d++) {
                int ny = item[0] + dy[d];
                int nx = item[1] + dx[d];

                if(ny < 0 || ny >= N || nx < 0 || nx >= M) {
                    continue;
                }

                if(map[ny][nx] == 0) {
                    int[] nextItem = {ny,nx};
                    map[ny][nx] = 2;
                    queue.add(nextItem);
                }
            }
        }
        // 바깥의 0을 2로 만들었다.
        int time = 0;

        while(cheese != 0) {
            time++;
            priviousCheese = cheese;
            boolean[][] visit = new boolean[N][M];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 2) {
                        for (int d = 0; d < 4; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];

                            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                                continue;
                            }

                            if (map[ny][nx] == 1 && !visit[ny][nx]) {
                                visit[ny][nx] = true;
                            }
                        }
                    }
                }
            }
            // 공기에 먹힐 치즈를 선택한다.

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (visit[i][j]) {
                        map[i][j] = 2;
                        cheese--;
                        // 치즈가 사라진다.

                        for (int d = 0; d < 4; d++) {
                            int ny = i + dy[d];
                            int nx = j + dx[d];

                            if (ny < 0 || ny >= N || nx < 0 || nx >= M) {
                                continue;
                            }

                            if (map[ny][nx] == 0) {
                                int[] newAir = {ny, nx};
                                map[ny][nx] = 2;
                                queue.add(newAir);

                                while (!queue.isEmpty()) {
                                    int[] item = queue.poll();

                                    for (int dir = 0; dir < 4; dir++) {
                                        int nny = item[0] + dy[dir];
                                        int nnx = item[1] + dx[dir];

                                        if (nny < 0 || nny >= N || nnx < 0 || nnx >= M) {
                                            continue;
                                        }

                                        if (map[nny][nnx] == 0) {
                                            int[] nextItem = {nny, nnx};
                                            map[nny][nnx] = 2;
                                            queue.add(nextItem);
                                        }
                                    }

                                }
                            }
                            // 주변에 공기가 들어갈 공간이 있다면 공기를 채운다.
                        }

                    }
                }
            }
        }

        System.out.println(time);
        System.out.println(priviousCheese);

    }
}
