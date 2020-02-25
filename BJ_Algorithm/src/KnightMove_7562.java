import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class KnightMove_7562 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        int[] di = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dj = {1, 2, 2, 1, -1, -2, -2, -1};
        for (int t = 1; t <= T; t++) {
            int result = 0;
            int I = sc.nextInt();
            int[] start = {sc.nextInt(), sc.nextInt()};
            int[] end = {sc.nextInt(), sc.nextInt()};

            if(start[0] == end[0] && start[1] == end[1]) {
                System.out.println(0);
                continue;
            }

            Queue<int[]> queue = new LinkedList<>();
            queue.add(start);
            boolean[][] visit = new boolean[I][I];
            visit[start[0]][start[1]] = true;

            while (!queue.isEmpty()) {
                ArrayList<int[]> list = new ArrayList<>();

                while (!queue.isEmpty()) {
                    list.add(queue.poll());
                }
                result++;

                for (int a = 0; a < list.size(); a++) {
                    int[] temp = list.get(a);
                    for (int i = 0; i < 8; i++) {
                        int ni = di[i] + temp[0];
                        int nj = dj[i] + temp[1];

                        if (ni < 0 || ni >= I || nj < 0 || nj >= I) {
                            continue;
                        }

                        if (end[0] == ni && end[1] == nj) {
                            queue.clear();
                            a = list.size();
                            break;
                        }
                        if (!visit[ni][nj]) {
                            int[] next = {ni, nj};
                            queue.add(next);
                            visit[ni][nj] = true;
                        }
                    }
                }
            }
            System.out.println(result);
        }
    }
}

