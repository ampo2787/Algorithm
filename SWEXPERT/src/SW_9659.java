import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class SW_9659 {
    static int N;
    static int[][] tab;
    static int M;
    static int[] x;
    static long[][] f;
    static boolean[][] fvisit;
    static final long divide = 998244353;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());

            tab = new int[N+1][3];

            for(int i = 2; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                tab[i][0] = Integer.parseInt(st.nextToken()); //t
                tab[i][1] = Integer.parseInt(st.nextToken()); //a
                tab[i][2] = Integer.parseInt(st.nextToken()); //b
            }

            M = Integer.parseInt(br.readLine());
            x = new int[M+1];
            st = new StringTokenizer(br.readLine());

            for(int i=1; i< M + 1; i++) {
                x[i] = Integer.parseInt(st.nextToken());
            }

            f = new long[M+1][N+1];
            fvisit = new boolean[M+1][N+1];

            for(int m = 1; m < M + 1; m++) {
                f[m][N] = dfs(N, m);
            }

            sb.append("#" + tc);
            for(int i = 1; i < M + 1; i++) {
                sb.append(" " + f[i][N]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    public static long dfs(int idx, int m) {
        // m 이 i 고, N 이다.
        // f[x][밑];
        int ti = tab[idx][0];
        int ai = tab[idx][1];
        int bi = tab[idx][2];

        if(idx == 0) {
            return 1;
        }
        else if(idx == 1) {
            return x[m];
        }

        if(!fvisit[m][ai]) {
            f[m][ai] = dfs(ai, m);
            fvisit[m][ai] = true;
        }

        if(!fvisit[m][bi]) {
            f[m][bi] = dfs(bi, m);
            fvisit[m][bi] = true;
        }

        if(!fvisit[m][idx]) {
            if(ti == 1) {
                f[m][idx] = (f[m][ai] + f[m][bi]) % divide;
            }
            else if(ti == 2){
                f[m][idx] = (ai * f[m][bi]) % divide;
            }
            else {
                f[m][idx] = (f[m][ai] * f[m][bi]) % divide;
            }
            fvisit[m][idx] = true;
        }
        return f[m][idx];
    }
}
