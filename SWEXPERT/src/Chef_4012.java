import java.io.IOException;
import java.util.Scanner;

public class Chef_4012 {
    static int N;
    static int[][] synergy;
    static int result;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();

        for(int tc=1; tc<=T; tc++) {
            N = sc.nextInt();
            synergy = new int[N+1][N+1];
            result = Integer.MAX_VALUE;
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    synergy[i][j] = sc.nextInt();
                }
            }

            dfs(0, 1, new boolean[N+1]);
            // 식재료를 N / 2 개씩 나누어 요리.
            sb.append("#" + tc + " " + result + "\n");
        }
        System.out.println(sb);
    }

    public static void dfs(int selected, int start, boolean[] food) {
        if(selected == N/2) {
            int falseTeam = 0;
            int trueTeam = 0;

            for(int i=1; i <=N; i++) {
                if(food[i]) {
                    for (int j = 1; j <= N; j++) {
                        if(food[j]) {
                            trueTeam += synergy[i][j];
                        }
                    }
                }
                else {
                    for (int j = 1; j <= N; j++) {
                        if(!food[j]) {
                            falseTeam += synergy[i][j];
                        }
                    }
                }
            }

            int abs = Math.abs(trueTeam - falseTeam);
            result = Math.min(abs, result);
            return;
        }

        for(int i=start; i <= N; i++) {
            food[i] = true;
            dfs(selected + 1, i + 1, food);
            food[i] = false;
        }
    }
}
