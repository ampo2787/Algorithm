import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_1953 {
    static int N,M,R,C,L;
    static int[][] map;
    static boolean[][] visit;
    static int result;
    static int time;

    final static int[] dy = {-1,0,1,0};
    final static int[] dx = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            visit = new boolean[N][M];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());

                for(int j=0; j<M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int[] start = {R,C};

            // L <- 시간 제한.
            bfs(start);
            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.print(sb);
    }

    public static void bfs(int[] start) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        result = 1;
        time = 0;
        visit[R][C] = true;

        while(!queue.isEmpty()) {
            ArrayList<int[]> list = new ArrayList<>();
            time++;

            if(time >= L) {
                break;
            }

            while(!queue.isEmpty()) {
                list.add(queue.poll());
            }

            for(int i=0; i<list.size(); i++) {
                int[] item = list.get(i);

                for(int d=0; d<4; d++) {
                    int ny = dy[d] + item[0];
                    int nx = dx[d] + item[1];

                    if(ny < 0 || ny >= N || nx < 0 || nx >= M) {
                        continue;
                    }

                    if(map[ny][nx] == 0) {
                        continue;
                    }

                    if(visit[ny][nx]) {
                        continue;
                    }

                    if(check(map[item[0]][item[1]], map[ny][nx], d)) {
                        int[] nextItem = {ny, nx};
                        visit[ny][nx] = true;
                        result++;
                        queue.add(nextItem);
                    }
                    // 매개변수 : 현재 관 모양, 다음 관 모양, 다음 관 방향(d)
                    // 위, 오, 아, 왼 순.
                }
            }


        }
    }

    public static boolean check(int now, int next, int dir) {
        switch (dir) {
            case 0: {
                switch (now) {
                    case 1:
                    case 2:
                    case 4:
                    case 7:
                        if(next == 1 || next == 2 || next == 5 || next == 6) {
                            return true;
                        }
                        return false;
                    case 3:
                    case 5:
                    case 6:
                        return false;
                }
            }
            case 1: {
                switch (now) {
                    case 1:
                    case 3:
                    case 4:
                    case 5:
                        if(next == 1 || next == 3 || next == 6 || next == 7) {
                            return true;
                        }
                        return false;
                    case 2:
                    case 6:
                    case 7:
                        return false;
                }
            }
            case 2: {
                switch (now) {
                    case 1:
                    case 2:
                    case 5:
                    case 6:
                        if(next == 1 || next == 2 || next == 4 || next == 7) {
                            return true;
                        }
                        return false;
                    case 3:
                    case 4:
                    case 7:
                        return false;
                }
            }
            case 3: {
                switch (now) {
                    case 1:
                    case 3:
                    case 6:
                    case 7:
                        if(next == 1 || next == 3 || next == 4 || next == 5) {
                            return true;
                        }
                        return false;
                    case 2:
                    case 4:
                    case 5:
                        return false;
                }
            }
            default:
                System.out.println("error");
                return false;
        }
    }
}
