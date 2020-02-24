import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine();

        int[][] maze = new int[n][m];
        String temp;

        for(int i=0;i<n; i++) {
            temp = sc.nextLine();
            for(int j=0; j<m; j++) {
                maze[i][j] = Integer.parseInt(String.valueOf(temp.charAt(j)));
            }
        }

        int[] start = {0,0};

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        Queue<int[]> q = new LinkedList();
        boolean[][] privious = new boolean[n][m];

        q.add(start);
        privious[0][0] = true;

        int move = 0;

        while(!q.isEmpty()) {
            move++;
            ArrayList<int[]> list = new ArrayList<>();
            while(!q.isEmpty()) {
                list.add(q.poll());
            }

            for(int[] arr : list) {
                for(int i=0; i<4; i++) {
                    int[] next = {dx[i] + arr[0], dy[i] + arr[1]};
                    if(next[0] == n-1 && next[1] == m-1) {
                        System.out.println(move + 1);
                        System.exit(0);
                    }
                    if(next[0] < 0 || next[0] >= n || next[1] < 0 || next[1] >=m) continue;
                    if(!privious[next[0]][next[1]] && maze[next[0]][next[1]] == 1) {
                        q.add(next);
                        privious[next[0]][next[1]] = true;
                    }
                }
            }
        }

    }
}
