import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class NetworkConnection_1922 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] graph = new int[N+1][N+1];

        for (int i = 0; i < M ; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph[a][b] = c;
            graph[b][a] = c;
        }

        // 모든 컴퓨터를 연결하는 최소비용.
        // kruskal : 가장 낮은 비용을 연결, 단 cycle 이 생기지 않게.
        // prim : 임의 정점 선택 집합 t에 넣음 -> t 에 없는 정점들 중 간선 가중치가 최소인 것 선택.
        // -> t 에 없는 노드를 t에 포함. -> 모든 노드가 t 에 들어왔다면 종료.
        // 프림으로 해 보자.

        ArrayList<Integer> list = new ArrayList<>();
        int cost = 0;
        list.add(1);

        while(list.size() != N) {
            ArrayList<Integer> next = (ArrayList) list.clone();
            int min = Integer.MAX_VALUE;
            int target = 0;

            for(int i=0; i<list.size(); i++) {
                int node = list.get(i);
                for(int j = 1; j < N+1; j++) {
                    if(graph[node][j] != 0 && graph[node][j] < min) {
                        if(!list.contains(j)) {
                            min = graph[node][j];
                            target = j;
                        }
                    }
                }
            }

            cost += min;
            next.add(target);
            list = (ArrayList) next.clone();
        }

        System.out.println(cost);
    }
}
