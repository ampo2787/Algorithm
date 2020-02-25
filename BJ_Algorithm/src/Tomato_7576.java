import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Tomato_7576 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();
        int[][] tomato = new int[N][M];
        int notTomato = 0;

        Queue<int[]> q = new LinkedList<>();

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                tomato[i][j] = scanner.nextInt();
                if(tomato[i][j] == 1)
                    q.add(new int[]{i, j});
                 if(tomato[i][j] == 0)
                     notTomato++;
            }
        }

        if(notTomato == 0) {
            System.out.println(0);
            return;
        }

        int x,y;
        int day = -1;
        boolean noChange = true;
            while (!q.isEmpty()) {
                ArrayList<int[]> list = new ArrayList<>();
                while (!q.isEmpty()) {
                    list.add(q.poll());
                }
                day++;
                for (int i = 0; i < list.size(); i++) {
                    x = list.get(i)[0];
                    y = list.get(i)[1];
                    if (x != 0) {
                        if (tomato[x - 1][y] == 0) {
                            noChange = false;
                            notTomato--;
                            tomato[x - 1][y] = 1;
                            q.add(new int[]{x-1, y});
                        }
                    }
                    if (x != N - 1) {
                        if (tomato[x + 1][y] == 0) {
                            noChange = false;
                            notTomato--;
                            tomato[x + 1][y] = 1;
                            q.add(new int[]{x+1, y});

                        }
                    }

                    if (y != 0) {
                        if (tomato[x][y - 1] == 0) {
                            noChange = false;
                            notTomato--;
                            tomato[x][y - 1] = 1;
                            q.add(new int[]{x, y-1});
                        }
                    }
                    if (y != M - 1) {
                        if (tomato[x][y + 1] == 0) {
                            noChange = false;
                            notTomato--;
                            tomato[x][y + 1] = 1;
                            q.add(new int[]{x, y+1});
                        }
                    }

                }
                if(noChange && notTomato != 0) {
                    day = -1;
                    break;
                }
                noChange = true;
            }
        System.out.println(day);
    }
}
