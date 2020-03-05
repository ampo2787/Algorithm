import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DessertCafe_2105 {
    static int N;
    static int[][] map;
    static int max;

    final static int[] dy_1 = {1,1,-1,-1};
    final static int[] dx_1 = {-1,1,1,-1};

    final static int[] dy_2 = {1,-1,-1,1};
    final static int[] dx_2 = {1,1,-1,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int test_case=1; test_case <= T; test_case ++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            max = -1;

            for(int i=0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 대각선으로 탐방, 사각형을 그려야 함. 같은 가게, 같은 종류, 왔던 길 안됨. 하나만 먹는 것 안됨.
            // 가장 많은 종류의 디저트를 먹어야 함.

            for(int i=0; i < N; i++) {
                for(int j=0; j<N; j++) {
                    dfsLeft(0,0,0, i ,j , new ArrayList<>()); // left
                    dfsRight(0,0,0, i ,j, new ArrayList<>()); // right
                }
            }

            System.out.println("#" + test_case + " " + max);
        }
    }

    public static void dfsLeft(int turn, int one, int two, int y, int x, ArrayList<Integer> List) {
        if(turn == 3 && one == 0 && two == 0) { //종료조건.
            max = Math.max(List.size(), max);
            return;
        }

        if(y < 0 || y >= N || x < 0 || x >= N) {
            return;
        }
        ArrayList<Integer> list = (ArrayList) List.clone();

        if(!list.contains((Object) map[y][x])) {
            list.add(map[y][x]);
        }
        else {
            return;
        }

        if(turn == 0) {
            dfsLeft(turn, one + 1, two, y + dy_1[turn], x + dx_1[turn], list);
            turn++;
            dfsLeft(turn, one, two + 1, y + dy_1[turn], x + dx_1[turn], list);
        }
        else if(turn == 1) {
            dfsLeft(turn, one, two + 1, y + dy_1[turn], x + dx_1[turn], list);
            turn++;
            dfsLeft(turn, one - 1, two, y + dy_1[turn], x + dx_1[turn], list);
        }
        else if(turn == 2){ //되돌아가는 부분.
            if(one != 0) {
                dfsLeft(turn, one - 1, two, y + dy_1[turn], x + dx_1[turn], list);
            }
            else {
                turn++;
                dfsLeft(turn, one, two - 1, y + dy_1[turn], x + dx_1[turn], list);
            }
        }
        else {
            dfsLeft(turn, one, two - 1, y + dy_1[turn], x + dx_1[turn], list);
        }

    }

    public static void dfsRight(int turn, int one, int two, int y, int x, ArrayList<Integer> List) {
        if(turn == 3 && one == 0 && two == 0) { //종료조건.
            max = Math.max(List.size(), max);
            return;
        }

        if(y < 0 || y >= N || x < 0 || x >= N) {
            return;
        }

        ArrayList<Integer> list = (ArrayList) List.clone();

        if(!list.contains((Object) map[y][x])) {
            list.add(map[y][x]);
        }
        else {
            return;
        }

        if(turn == 0) {
            dfsRight(turn, one + 1, two, y + dy_2[turn], x + dx_2[turn], list);
            turn++;
            dfsRight(turn, one, two + 1, y + dy_2[turn], x + dx_2[turn], list);
        }
        else if(turn == 1) {
            dfsRight(turn, one, two + 1, y + dy_2[turn], x + dx_2[turn], list);
            turn++;
            dfsRight(turn, one - 1, two, y + dy_2[turn], x + dx_2[turn], list);
        }
        else if(turn == 2){ //되돌아가는 부분.
            if(one != 0) {
                dfsRight(turn, one - 1, two, y + dy_2[turn], x + dx_2[turn], list);
            }
            else {
                turn++;
                dfsRight(turn, one, two - 1, y + dy_2[turn], x + dx_2[turn], list);
            }
        }
        else {
            dfsRight(turn, one, two - 1, y + dy_2[turn], x + dx_2[turn], list);
        }
    }
}
