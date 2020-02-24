import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static boolean[][] chess;
    static int N;
    static int number;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        number = 0;
        chess = new boolean[N][N];
        //첫 줄에 N 개의 들어갈 수 있는 자리가 있다. 하나를 놓으면 두 번째 줄에는 두 자리가 막힌다.
        //첫 번째 줄과 두 번째 줄을 결정한 후 세 번째 줄에는

        dfs(0);

        System.out.println(number);
    }

    public static void dfs(int index) {
        if(N == index) {
            number++;
            return;
        }

        for(int i=0; i < N; i++) {
            if(queenMoveCheck(index, i)) {
                chess[index][i] = true;
                dfs(index+1);
                chess[index][i] = false;
            }
        }
    }

    public static boolean queenMoveCheck(int i, int j) {
        //ex 1,2 가 왔다.
        for(int x=0; x<N; x++) {
            if(chess[x][j] == true) {
                return false;
            }

            if(chess[i][x] == true) {
                return false;
            }
        }

        int zeroX = i;
        int zeroY = j;

        while(zeroX != -1 && zeroY != -1) {
            if (chess[zeroX][zeroY] == true) {
                return false;
            }
            zeroX--;
            zeroY--;
        }
        zeroX = i;
        zeroY = j;

        while(zeroX != N && zeroY != N) {
            if (chess[zeroX][zeroY] == true) {
                return false;
            }
            zeroX++;
            zeroY++;
        }
        zeroX = i;
        zeroY = j;

        while(zeroX != -1 && zeroY != -1 && zeroX != N && zeroY != N) {
            if (chess[zeroX][zeroY] == true) {
                return false;
            }
            zeroX++;
            zeroY--;
        }
        zeroX = i;
        zeroY = j;

        while(zeroX != -1 && zeroY != -1 && zeroX != N && zeroY != N) {
            if (chess[zeroX][zeroY] == true) {
                return false;
            }
            zeroX--;
            zeroY++;
        }

        return true;
    }
}
