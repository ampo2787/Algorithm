import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17472 {
    // 사방탐색
    static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    static int R, C;// 1 ≤ N, M ≤ 10
    static int[][] map, graph;
    static int islandIdx;// 섬의 번호 - 2 ≤ 섬의 개수 ≤ 6
    static int INF = 987654321;// 그래프에서 사용할 초기화 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader(src));

        StringTokenizer tokens = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            tokens = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(tokens.nextToken());
            }
        }

//		// 맵 확인
//		for(int [] row: map) {
//			System.out.println(Arrays.toString(row));
//		}
//
        islandIdx = 2;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                // 땅을 발견하면 거기를 기점으로 BFS 탐색 --> 연결된 지점들을 표시
                if (map[r][c] == 1) {
                    bfs(r, c); // 한번의 BFS --> 섬 하나가 발견되었다!!
                    islandIdx++;
                }
            }
        }
        //
        /*
         * System.out.println("섬 확인"); for(int [] row: map) {
         * System.out.println(Arrays.toString(row)); } System.out.println(islandIdx);
         */

        // 그래프 초기화 하기..
        graph = new int[islandIdx][islandIdx];
        // 각 섬간의 최단 거리로 그래프를 구성할 계획 - 그래프의 값을 최대값으로 초기화
        for (int r = 2; r < islandIdx; r++) {
            Arrays.fill(graph[r], INF);
        }

        // 각 섬별로 거리(간선 가중치) 찾아보기
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 1) {
                    makeGraph(r, c);
                }
            }
        }

        // 그래프 확인
        /*
         * for(int [] row: graph) { System.out.println(Arrays.toString(row)); }
         */
        // MST 구하기 및 출력
        System.out.println(prim());
    }

    static int prim() {
        // 모든 vertex가 처음에 여기 들어있다가 탈출해야한다.
        PriorityQueue<Vertex> notMstGroup = new PriorityQueue<>();

        // 연결 비용과 관련된 섬들의 정보를 관리할 배열
        Vertex[] vertexes = new Vertex[islandIdx];
        for (int i = 2; i < islandIdx; i++) {
            if (i == 2) {
                vertexes[i] = new Vertex(i, 0);
            } else {
                vertexes[i] = new Vertex(i, INF);
            }
            notMstGroup.offer(vertexes[i]);
        }

        int sum = 0;

        while (!notMstGroup.isEmpty()) {
            Vertex front = notMstGroup.poll();

            if (front.cost == INF) {
                return -1;
            }
            sum += front.cost;

            for (int i = 2; i < islandIdx; i++) {
                Vertex child = vertexes[i];
                // 아직 MST의 요소가 아닌 녀석이라면...
                if (notMstGroup.contains(child)) {
                    // 그래프에서 비용 조회, 현재 가지고 있는 것보다 작으면 업데이트
                    if (child.cost > graph[front.idx][i]) {
                        child.cost = graph[front.idx][i];
                        notMstGroup.remove(child);
                        notMstGroup.offer(child);
                    }
                }
            }
        }
        return sum;
    }

    static void makeGraph(int row, int col) {
        // 내땅
        int base = map[row][col];
        for (int d = 0; d < dirs.length; d++) {

            for (int l = 1;; l++) {
                int nr = row + dirs[d][0] * l;
                int nc = col + dirs[d][1] * l;

                if (isIn(nr, nc)) {
                    // 바다면 쭉 간다.
                    if (map[nr][nc] == 0) {
                        continue;
                    }
                    // 내륙 - 그만..
                    else if (map[nr][nc] == base) {
                        break;
                    }
                    // 아니면 다른 섬 --> 거리는 2칸 이상 떨어져야 한다.
                    else {
                        // 드디어 다리를 만들 수 있는 곳에 왔어요..
                        if (l > 2) {
                            // 무향 그래프 --> 양방향 업데이트
                            graph[map[nr][nc]][base] = graph[base][map[nr][nc]] = Math.min(graph[base][map[nr][nc]], l - 1);
                        }
                        break;
                    }
                }
                // 영역 안에 없으면 그만..
                else {
                    break;
                }
            }
        }
    }

    static void bfs(int row, int col) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(row, col));
        // 방문 표시 - map 자체에다가 islandIdx로 표시 한다.
        map[row][col] = islandIdx;

        while (!q.isEmpty()) {
            Point front = q.poll();
            for (int d = 0; d < dirs.length; d++) {
                int nr = front.row + dirs[d][0];
                int nc = front.col + dirs[d][1];
                // map[nr][nc]==1 --> 아직 방문하지 않은 땅이라면...
                if (isIn(nr, nc) && map[nr][nc] == 1) {
                    map[nr][nc] = islandIdx;
                    q.offer(new Point(nr, nc));
                }
            }
        }
    }

    static boolean isIn(int row, int col) {
        return 0 <= row && row < R && 0 <= col && col < C;
    }

    static class Vertex implements Comparable<Vertex> {
        int idx, cost;

        public Vertex(int idx, int cost) {
            super();
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Vertex o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    static class Point {
        int row, col;

        public Point(int row, int col) {
            super();
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "Point [row=" + row + ", col=" + col + "]";
        }
    }

    static String src = "7 8\r\n" +
            "0 0 0 0 0 0 1 1\r\n" +
            "1 1 0 0 0 0 1 1\r\n" +
            "1 1 0 0 0 0 0 0\r\n" +
            "1 1 0 0 0 1 1 0\r\n" +
            "0 0 0 0 0 1 1 0\r\n" +
            "0 0 0 0 0 0 0 0\r\n" +
            "1 1 1 1 1 1 1 1";
}
