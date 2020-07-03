import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_3085 {
    static int max = 0;
    static int N;
    static char[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        board = new char[N][N];

        for(int i=0; i<N; i++) {
            board[i] = br.readLine().toCharArray();
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N - 1; j++) {
                if(board[i][j] != board[i][j+1]) {
                    solve(i, j , i, j+1);
                }
            }
        }

        for(int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] != board[i + 1][j]) {
                    solve(i, j, i + 1, j);
                }
            }
        }

        System.out.println(max);
    }

    public static void solve(int y1, int x1, int y2, int x2) {
        swap(y1,x1,y2,x2);
        check();
        swap(y1,x1,y2,x2);
    }

    public static void swap(int y1, int x1, int y2, int x2) {
        char temp = board[y1][x1];
        board[y1][x1] = board[y2][x2];
        board[y2][x2] = temp;
    }

    public static void check() {
        for(int i=0; i<N; i++) {
            int continueNum = 1;
            char previousCandy = board[i][0];

            for(int j=1; j<N; j++) {
                char thisCandy = board[i][j];

                if(thisCandy == previousCandy) {
                    continueNum++;
                }
                else {
                    previousCandy = thisCandy;
                    max = Math.max(continueNum, max);
                    continueNum = 1;
                }
            }
            max = Math.max(continueNum, max);
        }

        for(int i=0; i<N; i++) {
            int continueNum = 1;
            char previousCandy = board[0][i];

            for(int j=1; j<N; j++) {
                char thisCandy = board[j][i];

                if(thisCandy == previousCandy) {
                    continueNum++;
                }
                else {
                    previousCandy = thisCandy;
                    max = Math.max(continueNum, max);
                    continueNum = 1;
                }
            }
            max = Math.max(continueNum, max);
        }
    }
}
