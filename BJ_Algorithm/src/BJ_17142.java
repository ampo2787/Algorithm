import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17142 {
    final static int[] dy = {1,-1,0,0};
    final static int[] dx = {0,0,-1,1};

    static int N,M;
    static int[][] map;
    static ArrayList<int[]> nonActVirusList;
    static int emptyRoom;
    static boolean[] select;

    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken()); // 놓을 수 있는 바이러스 개수

        map = new int[N][N];

        nonActVirusList = new ArrayList<>();
        emptyRoom = 0;
        min = Integer.MAX_VALUE;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 2) {
                    int[] virus = {i,j};
                    nonActVirusList.add(virus);
                }
                else if(map[i][j] == 0) {
                    emptyRoom++;
                }
            }
        }

        select = new boolean[nonActVirusList.size()];

        if(emptyRoom != 0) {
            dfs(0, 0);

            if (min == Integer.MAX_VALUE) {
                min = -1;
            }
        }
        else {
            min = 0;
        }

        System.out.println(min);
    }

    public static void dfs(int start, int count) {
        if(count == M) {
            check();
            return;
        }

        for(int i = start; i < select.length; i++) {
            if(!select[i]) {
                select[i] = true;
                dfs(i, count + 1);
                select[i] = false;
            }
        }
    }

    public static void check() {
        int[][] thisMap = new int[N][N];
        boolean[][] visit = new boolean[N][N];
        int remainRoom = emptyRoom;

        for(int i=0; i<N; i++) {
            thisMap[i] = map[i].clone();
        }

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < nonActVirusList.size(); i++) {
            if(select[i]) {
                int[] virus = nonActVirusList.get(i);
                thisMap[virus[0]][virus[1]] = 3;
                visit[virus[0]][virus[1]] = true;
                queue.add(virus);
            }
        }

        int time = 0;

        while(!queue.isEmpty()) {
            time++;
            ArrayList<int[]> activeVirusList = new ArrayList<>();

            while(!queue.isEmpty()) {
                activeVirusList.add(queue.poll());
            }

            for(int i=0; i<activeVirusList.size(); i++) {
                int[] item = activeVirusList.get(i);

                for(int d=0; d<4; d++) {
                    int ny = item[0] + dy[d];
                    int nx = item[1] + dx[d];
                    int[] nextItem = {ny, nx};

                    if(ny < 0 || ny >= N || nx < 0 || nx >= N) {
                        continue;
                    }

                    if(thisMap[ny][nx] == 1) {
                        continue;
                    }


                    if(thisMap[ny][nx] == 2) {
                        thisMap[ny][nx] = 3;
                        queue.add(nextItem);
                        visit[ny][nx] = true;
                        continue;
                    }

                    if(thisMap[ny][nx] == 3) {
                        if(!visit[ny][nx]) {
                            queue.add(nextItem);
                            visit[ny][nx] = true;
                        }
                        continue;
                    }
                    else {
                        thisMap[ny][nx] = 3;
                        visit[ny][nx] = true;
                        remainRoom--;
                        queue.add(nextItem);
                    }
                }
            }

            if(remainRoom == 0) {
                min = Math.min(time, min);
                return;
            }
        }



    }
}
