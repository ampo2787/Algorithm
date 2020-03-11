import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BeOne_1251 {
    static double min;
    static int N;
    static long[][] islands;
    static double E;
    static long[][] graph; // 정점들 간의 거리 그래프 MST.

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case=1; test_case<=T; test_case++) {
            N = Integer.parseInt(br.readLine());
            islands = new long[N][2];

            for(int a=0; a<2; a++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int i=0; i<N; i++) {
                    islands[i][a] = Integer.parseInt(st.nextToken());
                }
            }

            E = Double.parseDouble(br.readLine());
            // 입력된 자료를 기반으로 섬 간의 가중치 그래프를 구해보자.
            graph = new long[N][N];
            long[] from, to;

            // 그래프 작성.
            for(int r=0; r<N; r++) {
                from = islands[r];
                for(int c = r+1; c < N; c++) {
                    to = islands[c];
                    graph[c][r] = graph[r][c] = (from[0] - to[0]) * (from[0] - to[0]) + (from[1] - to[1]) * (from[1] - to[1]);
                }
            }

            double cost = prim(0) * E;
            System.out.println("#" + test_case + " " + Math.round(cost));
        }
    }

    static long prim(int start) {
        long cost = 0;
        // pq 에는 MST에 들어가지 않은 녀석들이 들어감.
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        // 모든 정점들을 관리.
        Edge[] dynamicGraph = new Edge[N];

        for(int n = 0; n < dynamicGraph.length; n++) {
            dynamicGraph[n] = new Edge(n, Long.MAX_VALUE);
            if(n == start) {
                dynamicGraph[n].cost = 0;
            }
            pq.add(dynamicGraph[n]);
        }

        while(!pq.isEmpty()) {
            Edge front = pq.poll(); // MST에 포함되는 녀석
            cost += front.cost;

            // 자식 탐색.
            for(int i=0; i<dynamicGraph.length; i++) {
                Edge child = dynamicGraph[i];
                // pq : MST에 들어가지 않은 녀석들 집합.
                if(pq.contains(child)) {
                    long tempCost = graph[front.idx][child.idx];
                    if(tempCost < child.cost) {
                        child.cost = tempCost;
                        pq.remove(child);
                        pq.offer(child);
                    }
                }
            }
        }
        return cost;
    }

    static class Edge implements Comparable<Edge> {
        int idx;
        long cost;

        public Edge(int idx, long cost) {
            super();
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            // o.cost - Long.MIN_VALUE 등의 경우에 오버플로우가 발생.
            // return this.cost - o.cost > 0 ? 1 : -1;
            return Long.compare(this.cost, o.cost);
        }
    }
}
