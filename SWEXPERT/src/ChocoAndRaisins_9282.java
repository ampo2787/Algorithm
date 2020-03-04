import java.util.Arrays;
import java.util.Scanner;

public class ChocoAndRaisins_9282 {
    static int n;
    static int m;
    static int[][] map;
    static int result;
    static int[][][][] memory;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int TC = sc.nextInt();

        for(int test_case=1; test_case<=TC; test_case++) {
            n = sc.nextInt();
            m = sc.nextInt();

            map = new int[n][m];
            memory = new int[n+1][m+1][n+1][m+1];

            for(int[][][] d1 : memory) {
                for(int[][] d2 : d1) {
                    for(int[] d3 : d2) {
                        Arrays.fill(d3, Integer.MAX_VALUE);
                    }
                }
            }

            for(int i=0;i < n; i++) {
                for(int j=0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            result = dfs(0, 0, n, m);
            System.out.println("#" + test_case + " " + result);
        }
    }

    public static int dfs(int y, int x, int h, int w) {
        if(w == 1 && h == 1) {
            return 0;
        }

        int sum = 0;

        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++) {
                sum += map[i][j];
            }
        }

        // 가로.
        for(int i = 1; i < h; i++) {
            if(memory[y][x][i][w] == Integer.MAX_VALUE) {
                memory[y][x][i][w] = dfs(y, x, i, w);
            }
            if(memory[y+i][x][h-i][w] == Integer.MAX_VALUE) {
                memory[y+i][x][h-i][w] = dfs(y + i, x, h-i, w);
            }
            int sum1 = memory[y][x][i][w];
            int sum2 = memory[y+i][x][h-i][w];
            int sum3 = sum + sum1 + sum2;
            memory[y][x][h][w] = Math.min(memory[y][x][h][w], sum3);
        }

        // 세로.
        for(int i = 1; i < w; i++) {
            if(memory[y][x][h][i] == Integer.MAX_VALUE) {
                memory[y][x][h][i] = dfs(y, x, h, i);
            }
            if(memory[y][x+i][h][w-i] == Integer.MAX_VALUE) {
                memory[y][x+i][h][w-i] = dfs(y, x + i, h, w - i);
            }
            int sum1 = memory[y][x][h][i];
            int sum2 = memory[y][x+i][h][w-i];
            int sum3 = sum + sum1 + sum2;
            memory[y][x][h][w] = Math.min(memory[y][x][h][w], sum3);
        }

        return memory[y][x][h][w];
    }

}
