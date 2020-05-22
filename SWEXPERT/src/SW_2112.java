import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_2112 {
    static int D, W, K;
    static int[][] spec;
    static int min;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T ; tc++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            spec = new int[D][W];
            visit = new boolean[D];

            for(int i=0; i<D; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    spec[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            min = Integer.MAX_VALUE;
            // 동일한 특성의 셀이 K개 이상 연속으로 있는 경우에 통과.
            // 약품을 투과할 경우 가로막의 모든 셀은 하나의 특성으로 바뀐다.
            // 최소 약품투입 횟수로 성능검사를 통과할 수 있는 방법을 찾아라.

            dfs(0, 0);

            sb.append("#" + tc + " " + min + "\n");
        }

        System.out.print(sb);
    }


    public static void dfs(int start, int count) {
        if(checkAll()) {
            min = Math.min(min, count);
            return;
        }

        for(int i=start; i<D; i++) {
            if(!visit[i]) {
                int[] temp = spec[i].clone();

                visit[i] = true;
                Arrays.fill(spec[i], 0);
                dfs(i, count + 1);
                Arrays.fill(spec[i], 1);
                dfs(i, count + 1);
                visit[i] = false;

                spec[i] = temp;
            }
        }
    }

    public static boolean checkAll() {
        for(int i = 0; i < W; i++) {
            int continousA = 0;
            int continousB = 0;
            int thisA = 0;
            int thisB = 0;

            if(spec[0][i] == 0) {
                continousA = 1;
                thisA = 1;
            }
            else {
                continousB = 1;
                thisB = 1;
            }

            for(int j = 1; j < D; j++) {
                if(spec[j-1][i] == spec[j][i]) {
                    if(spec[j][i] == 0) {
                        thisA++;
                    }
                    else {
                        thisB++;
                    }
                }
                else {
                    if(spec[j][i] == 0) {
                        thisA = 1;
                        thisB = 0;
                    }
                    else {
                        thisB = 1;
                        thisA = 0;
                    }
                }

                continousA = Math.max(thisA, continousA);
                continousB = Math.max(thisB, continousB);

                if(continousA >= K || continousB >= K) {
                    break;
                }
            }

            if(continousA < K && continousB < K) {
                return false;
            }
        }

        return true;
    }
}
