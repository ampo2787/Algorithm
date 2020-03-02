import java.util.ArrayList;
import java.util.Scanner;

public class SuzyTrable_7699 {
    static int R;
    static int C;
    static char[][] alpha;
    static int result;
    static ArrayList<Character> myList;

    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for(int test_case=1; test_case<=T; test_case++) {
            R = sc.nextInt();
            C = sc.nextInt();
            sc.nextLine();
            alpha = new char[R][C];

            for(int i=0; i<R; i++) {
                alpha[i] = sc.nextLine().toCharArray();
            }

            //같은 알파벳은 같은 명물이다. 보고, 상하좌우 중 하나로 이동 후 본다.
            //같은 명물은 한 번만 볼 수 있다.. 최대로 볼 수 잇는 명물의 수.
            //수지는 1,1 에서 시작한다.

            result = 0;

            myList = new ArrayList<>();
            myList.add(alpha[0][0]);
            dfs(0,0);

            System.out.println("#" + test_case + " " + result);

        }
    }

    public static void dfs(int y, int x) {
        // 상하좌우에 더 볼 수 있는 명물이 없을 떄 반복을 끝내고 현재 리스트 사이즈를 result 와 비교한다.
        boolean noMore = true;

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny < 0 || ny >= R || nx < 0|| nx >= C) {
                continue;
            }

            if(!myList.contains(alpha[ny][nx])) {
                noMore = false;
                myList.add(alpha[ny][nx]);
                dfs(ny,nx);
                myList.remove((Object) alpha[ny][nx]);
            }
        }

        if(noMore) {
            result = Math.max(myList.size(), result);
        }
    }
}
