import java.util.Scanner;

public class BattleField_1873 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        int[] dy = {-1,0,1,0};
        int[] dx = {0,1,0,-1};

        for(int test_case = 1; test_case <= T; test_case++) {
            int H = sc.nextInt();
            int W = sc.nextInt();
            sc.nextLine();

            char[][] map = new char[H][W];
            int[] tank = new int[2];
            int tankDirection = 0; //0,1,2,3 - 위 오른 아래 왼.

            for(int i=0; i<H; i++) {
                map[i] = sc.nextLine().toCharArray();
                for(int j=0; j<W; j++) {
                    switch (map[i][j]) {
                        case '^':
                            tankDirection=0;
                            tank[0] = i;
                            tank[1] = j;
                            break;
                        case '<':
                            tankDirection=3;
                            tank[0] = i;
                            tank[1] = j;
                            break;
                        case '>':
                            tankDirection=1;
                            tank[0] = i;
                            tank[1] = j;
                            break;
                        case 'v':
                            tankDirection=2;
                            tank[0] = i;
                            tank[1] = j;
                            break;
                    }
                }
            }

            int N = sc.nextInt();
            char[] input = new char[N];
            sc.nextLine();
            input = sc.nextLine().toCharArray();

            for(int i=0; i<N; i++) {
                switch (input[i]) {
                    case 'U':
                        tankDirection = 0;
                        map[tank[0]][tank[1]] = '^';
                        if(tank[0] - 1 >= 0) {
                            if(map[tank[0]-1][tank[1]] == '.') {
                                map[tank[0]][tank[1]] = '.';
                                tank[0]--;
                                map[tank[0]][tank[1]] = '^';
                            }
                        }
                        break;
                    case 'D':
                        tankDirection = 2;
                        map[tank[0]][tank[1]] = 'v';
                        if(tank[0] + 1 < H) {
                            if(map[tank[0]+1][tank[1]] == '.') {
                                map[tank[0]][tank[1]] = '.';
                                tank[0]++;
                                map[tank[0]][tank[1]] = 'v';
                            }
                        }
                        break;
                    case 'L':
                        tankDirection = 3;
                        map[tank[0]][tank[1]] = '<';
                        if(tank[1] - 1 >= 0) {
                            if(map[tank[0]][tank[1] - 1] == '.') {
                                map[tank[0]][tank[1]] = '.';
                                tank[1]--;
                                map[tank[0]][tank[1]] = '<';
                            }
                        }
                        break;
                    case 'R':
                        tankDirection = 1;
                        map[tank[0]][tank[1]] = '>';
                        if(tank[1] + 1 < W) {
                            if(map[tank[0]][tank[1]+1] == '.') {
                                map[tank[0]][tank[1]] = '.';
                                tank[1]++;
                                map[tank[0]][tank[1]] = '>';
                            }
                        }
                        break;
                    case 'S':
                        int ny = dy[tankDirection] + tank[0];
                        int nx = dx[tankDirection] + tank[1];
                        while(ny >=0 && ny < H && nx >=0 && nx < W) {
                            if(map[ny][nx] == '*') {
                                map[ny][nx] = '.';
                                break;
                            }
                            else if(map[ny][nx] == '#') {
                                break;
                            }
                            ny = dy[tankDirection] + ny;
                            nx = dx[tankDirection] + nx;
                        }
                        break;
                }
            }

            sb.append("#" + test_case + " ");
            for(int i=0; i<H; i++) {
                for(int j=0; j<W; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}
