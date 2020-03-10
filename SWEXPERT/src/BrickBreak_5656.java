import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BrickBreak_5656 {
    static int N;
    static int W;
    static int H;
    static int remain;
    static int origin;

    static int[] dy = {1,-1,0,0};
    static int[] dx = {0,0,-1,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T ; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            int[][] map = new int[H][W];
            origin = 0;

            for(int i=0; i<H; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] != 0) {
                        origin++;
                    }
                }
            }
            remain = origin;

            dfs(0, map, 0);
            System.out.println("#" + test_case + " " + remain);
        }
    }

    public static void dfs(int index, int[][] map, int number) {
        if(index == N) {
            int result = 0;
//            remain = Math.min(origin - number, remain);
//            for(int a=0; a<H; a++) {
//                for (int b = 0; b < W; b++) {
//                    System.out.print(map[a][b] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
            for(int a=0; a<H; a++) {
                for (int b = 0; b < W; b++) {
                    if(map[a][b] != 0) {
                        result++;
                    }
                }
            }
            remain = Math.min(result, remain);
            return;
        }
        boolean allZero = true;

        for(int i=0; i<W; i++) {
            int[][] copyMap = new int[H][W];

            for(int j=0;j<H; j++) {
                copyMap[j] = map[j].clone();
            }

            int[] point = getPoint(i, copyMap);
            if(point[0] != -1) {
                allZero = false;
                int nextNumber = number + breakBrick(point[0], point[1], copyMap, copyMap[point[0]][point[1]]);
                copyMap = optimize(copyMap);

                dfs(index + 1, copyMap, nextNumber);
            }
        }

        if(allZero) {
            remain = 0;
            return;
        }
    }

    public static int[] getPoint(int x, int[][] map) {
        int[] point = {-1, x};

        for(int i=0; i < H; i++) {
            if(map[i][x] != 0) {
                point[0] = i;
                break;
            }
        }
        return point;
    }

    public static int breakBrick(int y, int x, int[][] map ,int breakThrough) {
        map[y][x] = 0;

        if(breakThrough == 1) {
            return 1;
        }

        int number = 0;

        for(int i=0; i<4; i++) {
            int ny = y;
            int nx = x;

            for(int j=0; j<breakThrough -1; j++) {
                ny = ny + dy[i];
                nx = nx + dx[i];

                if(ny < 0 || ny >= H || nx <0 || nx >= W) {
                    break;
                }

                if(map[ny][nx] != 0) {
                    int temp = map[ny][nx];
                    number += breakBrick(ny, nx, map, temp);
                }

            }
        }

        return number;
    }

    public static int[][] optimize(int[][] map) {
        int[][] resultMap = new int[H][W];

        for(int i=0; i<W; i++) {
            int index = H-1;
            for(int j=H-1; j>=0; j--) {
                if(map[j][i] != 0) {
                    resultMap[index--][i] = map[j][i];
                }
            }
        }

        return resultMap;
    }
}
