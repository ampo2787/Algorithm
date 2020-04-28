import java.util.Scanner;

public class BJ_15684 {
    static int N;
    static int M;
    static int H;
    static boolean[][] connect;
    static int answer;
    static boolean finish;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        H = sc.nextInt();

        connect = new boolean[H+1][N+1];

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            connect[a][b] = true;
            // connect가 true 면 오른쪽 연결선이 있는 것으로 취급.
            // b 세로선과 b+1 세로선을 a 위치에서 연결했다.
        }

        // 결과는 i 로 들어가면 i가 나와야 함.
        // 추가해야하는 가로선의 최솟값을 구해라.
        // 조합인 거 같다.
        // \ N
        // M

        for (int i = 0; i <= 3; i++) {
            answer = i;
            dfs(1, 0);
            if (finish) break;
        }

        System.out.println((finish) ? answer : -1);
    }

    public static void dfs(int x, int count) {
        if (finish) return;
        if (answer == count) {
            if (check()) finish = true;
            return;
        }

        for (int i = x; i < H + 1; i++) {
            for (int j = 1; j < N; j++) {
                if (!connect[i][j-1] && !connect[i][j]) {
                    connect[i][j] = true;
                    dfs(i, count + 1);
                    connect[i][j] = false;
                }
            }
        }
    }

    public static boolean check() {
        for (int i = 1; i < N + 1; i++) {
            if(!checkLine(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkLine(int line) {
        int currentLine = line;
        for(int i=1; i < H+1; i++) {
            if(connect[i][currentLine-1]) {
                currentLine--;
            }

            else if(connect[i][currentLine]) {
                currentLine++;
            }
        }

        return currentLine == line;
    }
}
