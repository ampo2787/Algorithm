import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class GetHoney_2115 {
    static int[][] honeyMap;
    static boolean[][] visit;
    static int N;
    static int M;
    static int C;
    static int maxValue;

    static int tempResult;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            maxValue = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            honeyMap = new int[N][N];
            visit = new boolean[N][N];

            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    honeyMap[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 일꾼 두 명, 연속된 개수 M. 한 명이 채취할 수 있는 꿀 최대 C.
            // 수익은 각 꿀 칸 양의 제곱을 다 더한 것. 최대 수익을 출력.

            dfs(0, 0);
            System.out.println("#" + test_case + " " + maxValue);
        }
    }

    public static void dfs(int index, int result) {
        if(index == 2) {
            maxValue = Math.max(result, maxValue);
            return;
        }

        for(int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                checkValue(index, i, j, result);
            }
        }

    }

    public static void checkValue(int index, int y, int x, int result) {
        int[] honeyList = new int[M];

        if(x + M > N) {
            return;
        }

        ArrayList<int[]> list = new ArrayList<>();

        for(int i = x; i < x+M; i++) {
            if(!visit[y][i]) {
                int[] temp = {y, i};
                list.add(temp);
                honeyList[i-x] = honeyMap[y][i];
            }
            else {
                return;
            }
        }

        for(int i=0; i<list.size(); i++) {
            int[] temp = list.get(i);
            visit[temp[0]][temp[1]] = true;
        }

        //honeyList에서 모든 조합 중 C를 넘지 않는 조합 중 제곱의 합이 제일 큰 값을 리턴한다.
        tempResult = 0;

        combination(list, new boolean[list.size()],0, 0 , 0 , list.size());

        dfs(index + 1, result + tempResult);

        for(int i=0; i<list.size(); i++) {
            int[] temp = list.get(i);
            visit[temp[0]][temp[1]] = false;
        }
    }

    public static void combination(ArrayList<int[]> list,boolean[] visit, int sum, int pow ,int index, int r) {
        if(sum > C) {
            return;
        }

        if(sum <= C || r == 0) {
            tempResult = Math.max(tempResult, pow);
        }

        if(sum == C) {
            return;
        }

        for(int i = index; i< list.size(); i++) {
            if(!visit[i]) {
                visit[i] = true;
                int[] temp = list.get(i);
                int nextPow = pow + honeyMap[temp[0]][temp[1]] * honeyMap[temp[0]][temp[1]];
                combination(list,visit,sum + honeyMap[temp[0]][temp[1]], nextPow, i + 1 ,r-1);
                visit[i] = false;
            }
        }

    }
}
