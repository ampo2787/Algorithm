import java.util.ArrayList;
import java.util.Scanner;

public class BJ_1987 {
    static int R;
    static int C;
    static char[][] map;
    static boolean[][] visit;
    static int result;
    final static int[] dy = {1,-1,0,0};
    final static int[] dx = {0,0,1,-1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        map = new char[R][C];
        visit = new boolean[R][C];
        result = 1;

        for(int i=0;i<R; i++) {
            map[i] = sc.nextLine().toCharArray();
        }
        visit[0][0] = true;
        // 말은 좌측 최상단 -> 0,0.
        // 같은 알파벳을 두 번 지날 수 없다.
        // 최대 몇 칸을 지날 수 있을까
        ArrayList<Character> list = new ArrayList<>();
        list.add(map[0][0]);

        dfs(0,0, list);
        System.out.println(result);
    }

    private static void dfs(int y, int x, ArrayList<Character> list) {
        if(list.size() > result) {
            result = list.size();
        }

        for(int d=0; d<4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(ny < 0 || ny >= R || nx < 0 || nx >= C) {
                continue;
            }

            if(visit[ny][nx]) {
                continue;
            }

            if(!list.contains(map[ny][nx])) {
                list.add(map[ny][nx]);
                visit[ny][nx] = true;
                dfs(ny,nx,list);
                visit[ny][nx] = false;
                list.remove((Object) map[ny][nx]);
            }
        }
    }
}
