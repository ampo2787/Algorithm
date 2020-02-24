import java.util.Scanner;

public class Main {
    static int result;
    static int N;
    static int[][] map;
    static int[] dy = {0,1,1};
    static int[] dx = {1,1,0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map =  new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        //빈 칸 0, 벽 1.
        //파이프는 항상 빈 칸만.
        //미는 방향은 오른, 오른아래대각, 아래.
        //회전은 45도만 가능.
        //한 쪽 끝은 N,N 으로.
        dfs(0,1, 2);
        System.out.println(result);
    }

    public static void dfs(int position ,int y, int x) {
        if(y == N && x == N) {
            result++;
            return;
        }

        //오른쪽, 대각선, 아래 순으로 체크한다.
        //그러니까 이 배열에 0,1,2 에 차례대로 들어간다. 0,0 은 갈 수 없을을 뜻한다.

        int[][] nextStep = new int[3][2];
        boolean allTrue = true;
        for(int i=0; i<3; i++) {
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            int[] temp = {ny, nx};
            if(ny <1 || ny > N || nx < 1 || nx > N) {
                allTrue = false;
                continue;
            }
            if(map[ny][nx] != 1) {
                nextStep[i] = temp;
            }
            else {
                allTrue = false;
            }
        }

        if(!allTrue) {
            int[] temp = {0,0};
            nextStep[1] = temp;
        }

        switch (position) {
            case 0:
                for(int i=0; i<2; i++) {
                    if(nextStep[i][0] == 0 && nextStep[i][1] == 0) {

                    }
                    else {
                        dfs(i, nextStep[i][0], nextStep[i][1]);
                    }
                }
                break;
            case 1:
                for(int i=0; i<3; i++) {
                    if (nextStep[i][0] == 0 && nextStep[i][1] == 0) {

                    } else {
                        dfs(i, nextStep[i][0], nextStep[i][1]);
                    }
                }
                break;
            case 2:
                for(int i=1; i<3; i++) {
                    if(nextStep[i][0] == 0 && nextStep[i][1] == 0) {

                    }
                    else {
                        dfs(i, nextStep[i][0], nextStep[i][1]);
                    }
                }
                break;
        }


        //가로.
        //세로

        //대각선.
    }
}
