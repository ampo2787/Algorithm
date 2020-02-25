import java.util.ArrayList;
import java.util.Scanner;

public class GoodbyeFineDust_17144 {
    public static void main(String[] args) {
        int[] dy = {0,0,1,-1};
        int[] dx = {1,-1,0,0};

        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();
        int T = sc.nextInt();

        int[][] map = new int[R+1][C+1];
        int[] airCleaner1 = new int[2];
        int[] airCleaner2 = new int[2];
        boolean isOne = true;
        ArrayList<int[]> dustExist = new ArrayList<>();

        for(int i=1; i<=R; i++) {
            for(int j=1; j<=C; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == -1) {
                    if(isOne) {
                        airCleaner1[0] = i;
                        airCleaner1[1] = j;
                        isOne = false;
                    }
                    else {
                        airCleaner2[0] = i;
                        airCleaner2[1] = j;
                    }
                }
                else if(map[i][j] != 0) {
                    int[] temp = new int[2];
                    temp[0] = i; temp[1] = j;
                    dustExist.add(temp);
                }
            }
        }

        // 공기청정기는 -1. 2칸 차지.
        // 1. 확산되는 양은 /5 이다. 소숫점 버린다. 즉 int 사용가능.
        // 2. 그 후 공기청정기 작동. 위쪽은 반시계, 아래쪽은 시계방향.
        // 공기청정기는 항상 왼쪽 열.
        // 먼지 배열 두 개 필요. 하나는 원래 존재하던 것.
        // 다른 하나는 계산 결과가 들어갈 것. 계산이 전부 완료되면 두 개를 스왑.
        for(int t=0; t<T; t++) {
            int[][] nextMap = new int[R+1][C+1];

            for(int i=0; i<dustExist.size(); i++) {
                int[] dust = dustExist.get(i);
                int continueNum = 4;

                for(int j=0; j<4; j++) {
                    int ny = dy[j] + dust[0];
                    int nx = dx[j] + dust[1];

                    if(ny < 1 || ny > R || nx < 1 || nx > C) {
                        continueNum--;
                        continue;
                    }

                    if(map[ny][nx] == -1) {
                        continueNum--;
                        continue;
                    }
                    nextMap[ny][nx] = nextMap[ny][nx] + map[dust[0]][dust[1]] / 5;
                }
                nextMap[dust[0]][dust[1]] = nextMap[dust[0]][dust[1]] - (map[dust[0]][dust[1]]/5) * continueNum;
            }
            // 여기까지 미세먼지의 확산.
            // nextMap 과 nextDustExist 가지고 공기청정기 작동 구현.
            // 그 후 원래 배열에 돌려줌.
            for(int i=1; i<=R; i++) {
                for(int j=1; j<=C; j++) {
                    map[i][j] = map[i][j] + nextMap[i][j];
                }
            }

            for(int i=airCleaner1[0] - 1; i > 1; i--) {
                map[i][1] = map[i-1][1];
            }

            for(int i=airCleaner2[0] + 1; i < R; i++) {
                map[i][1] = map[i + 1][1];
            }

            for(int i = 1; i < C; i++) {
                map[1][i] = map[1][i + 1];
                map[R][i] = map[R][i + 1];
            }

            for(int i=R; i > airCleaner2[0]; i--) {
                map[i][C] = map[i-1][C];
            }

            for(int i=1; i < airCleaner1[0]; i++) {
                map[i][C] = map[i+1][C];
            }

            for(int i = C; i > 2; i--) {
                map[airCleaner1[0]][i] = map[airCleaner1[0]][i-1];
                map[airCleaner2[0]][i] = map[airCleaner2[0]][i-1];
            }
            map[airCleaner1[0]][2] = 0;
            map[airCleaner2[0]][2] = 0;
            dustExist.clear();

            for(int i=1; i<=R; i++) {
                for(int j=1; j<=C; j++) {
                    if(map[i][j] != -1 && map[i][j] != 0) {
                        int[] temp = {i,j};
                        dustExist.add(temp);
                    }
                }
            }
        }

        int sum = 0;
        for(int i=0; i<dustExist.size(); i++) {
            sum += map[dustExist.get(i)[0]][dustExist.get(i)[1]];
        }

        System.out.println(sum);
    }
}
