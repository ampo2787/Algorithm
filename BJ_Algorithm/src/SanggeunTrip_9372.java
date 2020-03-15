import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class SanggeunTrip_9372 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            boolean[][] graph = new boolean[N+1][N+1];

            for(int i=0; i<M; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                graph[a][b] = true;
                graph[b][a] = true;
            }

            // 최대한 적은 종류의 비행기를 타고 모든 도시를 여행할 수 있게. 다른 국가를 거쳐도 됨.
            boolean[] visit = new boolean[N+1];

            Queue<Integer> queue = new LinkedList<>();
            queue.add(1);
            visit[1] = true;
            int airplane = 0;

            while(!queue.isEmpty()) {
                int item = queue.poll();

                for(int i=1; i<N+1; i++) {
                    if(graph[item][i] && !visit[i]) {
                        queue.add(i);
                        visit[i] = true;
                        airplane++;
                    }
                }
            }

            System.out.println(airplane);

        }
    }
}
