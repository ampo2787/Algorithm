import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MakeBridge2_17472 {
    static int[] dy = {-1,0,1,0};
    static int[] dx = {0,1,0,-1};
    static int N;
    static int M;
    static int[][] map;
    static int minResult = Integer.MAX_VALUE;

    static class Point {
        boolean isSea;
        ArrayList<Integer> bridge; //여기에 bridge 방향을 저장.

        public Point(boolean isSea, ArrayList<Integer> bridge) {
            this.isSea = isSea;
            this.bridge = bridge;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 다리의 양 끝은 바다에. 다리 길이는 2 이상. 방향 변경 불가. 대각선 안됨. 양 끝이 그 방향 그대로 섬과 인접해야 함. 교차 가능.
        // 교차 할 때 다리 길이는 양 쪽에 모두 포함됨.
        // 최소 다리 길이를 구하라.
        // 연결 불가 시 -1.

        // 일단 육지임이 확정된 곳을 만난다. 사방을 체크한다. 바다인 곳으로 다리를 쭉 내려본다. 연결되는 육지가 없으면 패스한다. dfs 활용.
        // 다리 하나를 놓을 때마다 bfs를 사용하여 모든 섬이 연결되어있는지 체크한다.
        // 연결되어 있다면 현재 다리 길이를 비교하여 가장 짧은 길이를 저장한다.

        dfs(0, 0, map, 0);
        if(minResult == Integer.MAX_VALUE) {
            minResult = -1;
        }
        System.out.println(minResult);
    }

    public static void dfs(int y, int x, int[][] map, int bridgeLength) {
        int nextDFSx = x + 1;
        int nextDFSy = y;
        if(nextDFSx == M) {
            nextDFSx = 0;
            nextDFSy++;
        }
        if(nextDFSy == N) {
            return;
        }
        //다음 dfs 할 요소 미리 계산.

        if(map[y][x] != 1) { //바다, 다리일 경우는 바로 다음으로 넘어감.
            dfs(nextDFSy,nextDFSx,map, bridgeLength);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(ny < 0 || ny >= N || nx < 0 || nx >= M) {
                continue;
            }

            if(ny + dy[i] < 0 || ny + dy[i] >= N || nx + dx[i] < 0 || nx + dx[i] >= M) {
                continue;
            }

            if(map[ny][nx] != 1 && map[ny+dy[i]][nx+dx[i]] != 1) { //최소 두 칸 이상이 바다, 다리여야 한다.
                int[][] copy = new int[N][M];

                for(int j=0;j<N; j++) {
                    copy[j] = map[j].clone();
                }

                int newBridgeLength = addBridge(ny, nx, i, copy, bridgeLength);

                if(newBridgeLength != bridgeLength) {
                    dfs(nextDFSy, nextDFSx, copy, newBridgeLength);
                }

            }
        }
        dfs(nextDFSy,nextDFSx, map, bridgeLength); // 다리를 놓지 않은 경우.
    }

    public static int addBridge(int y , int x, int dir, int[][] copyMap, int bridgeLength) {
        int ny = y;
        int nx = x;
        int newBridgeLength = 0;

        while(!(ny < 0 || ny >= N || nx < 0 || nx >= M)) {
            if(copyMap[ny][nx] != 1) {
                if(copyMap[ny][nx] >= 2) {
                    copyMap[ny][nx]++;
                }
                else {
                    copyMap[ny][nx] = 2;
                }
                ny += dy[dir];
                nx += dx[dir];
                newBridgeLength++;
            }
            else { //다리 놓았으면 현재 맵 bfs 체크 필수.
                check(copyMap, newBridgeLength + bridgeLength);
                return newBridgeLength + bridgeLength;
            }
        }

        //연결되는 다른 섬이 없었다.
        return bridgeLength;
    }

    public static boolean check(int[][] map, int length) {
        Queue<int[]> queue = new LinkedList<>();
        int[][] copyMap = new int[N][M];

        for(int j=0;j<N; j++) {
            copyMap[j] = map[j].clone();
            for(int k=0; k<N; k++) {
                System.out.print(copyMap[j][k] + " ");
            }
            System.out.println();
        }

        System.out.println(length);
        System.out.println();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j] == 1) {
                    int[] temp = {i,j};
                    copyMap[i][j] = 0;
                    queue.add(temp);
                    i=N;
                    break;
                }
            }
        }
        // ㅇ

        for(int i=0; i<N; i++) {
            for (int j = 0; j < M; j++) {
                if(copyMap[i][j] != 0) {
                    return false;
                }
            }
        }

        minResult = Math.min(minResult, length);
        return true;
    }
}
