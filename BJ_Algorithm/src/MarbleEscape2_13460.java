import java.util.Scanner;

public class Main {
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = {0, 1, 0, -1};
    static int[] direction = {0, 1, 2, 3};
    static int[] Red;
    static int[] Blue;
    static int[] Hole;
    static int result;
    static char[][] board;

    static int N;
    static int M;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        sc.nextLine();

        board = new char[N][M];
        int[] Red = new int[2];
        int[] Blue = new int[2];
        int[] Hole = new int[2];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            board[i] = sc.nextLine().toCharArray();
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'R') {
                    Red[0] = i;
                    Red[1] = j;
                } else if (board[i][j] == 'B') {
                    Blue[0] = i;
                    Blue[1] = j;
                } else if (board[i][j] == 'O') {
                    Hole[0] = i;
                    Hole[1] = j;
                }
            }
        }

        dfs(1, -1, Red, Blue);
        if(result == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else {
            System.out.println(result);
        }

    }

    public static void dfs(int index, int previousMove, int[] red, int[] blue) {
        if (index == 11) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (i != previousMove) {
                // i 방향으로 다 민다.
                // 다 밀어서 O로 R 이 들어갔으면 exit.
                // O 로 B 가 들어갔으면 return.
                int[] nextred = {red[0], red[1]};
                int[] nextblue = {blue[0], blue[1]};
                boolean change = false;
                if (nextred[0] < 0 || nextred[1] < 0 || nextred[0] >= N || nextred[1] >= M) {
                    continue;
                }
                if (nextblue[0] < 0 || nextblue[1] < 0 || nextblue[0] >= N || nextblue[1] >= M) {
                    continue;
                }
                // 둘 다 다음이 # 이거나, 하나가 다음이 # 이고 하나는 다음이 다른 하나일 경우.
                while (true) {
                    int[] prRed = {nextred[0], nextred[1]};
                    int[] prBlue = {nextblue[0], nextblue[1]};
                    nextred[0] = nextred[0] + di[i];
                    nextred[1] = nextred[1] + dj[i];
                    nextblue[0] = nextblue[0] + di[i];
                    nextblue[1] = nextblue[1] + dj[i];
                    if (nextred[0] < 0 || nextred[1] < 0 || nextred[0] >= N || nextred[1] >= M) {
                        break;
                    }
                    if (nextblue[0] < 0 || nextblue[1] < 0 || nextblue[0] >= N || nextblue[1] >= M) {
                        break;
                    }
                    if (board[nextred[0]][nextred[1]] == '#' && board[nextblue[0]][nextblue[1]] == '#') {
                        nextred = prRed;
                        nextblue = prBlue;
                        break;
                    }

                    if (board[nextred[0]][nextred[1]] == '#') {
                        nextred = prRed;
                        if (nextblue[0] == prRed[0] && nextblue[1] == prRed[1]) {
                            nextblue = prBlue;
                            break;
                        }
                    }

                    if (board[nextblue[0]][nextblue[1]] == '#') {
                        nextblue = prBlue;
                        if (nextred[0] == prBlue[0] && nextred[1] == prBlue[1]) {
                            nextred = prRed;
                            break;
                        }
                    }

                    if (board[nextblue[0]][nextblue[1]] == 'O') {
                        change = false;
                        break;
                    }

                    if (board[nextred[0]][nextred[1]] == 'O') {
                        int bi = nextblue[0];
                        int bj = nextblue[1];
                        boolean blueHole = false;
                        while (true) {
                            if (bi < 0 || bi >= N || bj < 0 || bj >= M) {
                                break;
                            }
                            if (board[bi][bj] == '#') {
                                break;
                            }
                            if (board[bi][bj] == 'O') {
                                blueHole = true;
                                break;
                            }
                            bi = bi + di[i];
                            bj = bj + dj[i];
                        }
                        if (!blueHole) {
                            result = Math.min(index, result);
                            return;
                        } else {
                            change = false;
                            break;
                        }

                    }
                    change = true;
                    /*
                    for (int j = 0; j < 2; j++) {
                        if (nextred[j] < 0) {
                            nextred[j] = 0;
                        }

                        if (nextblue[j] < 0) {
                            nextblue[j] = 0;
                        }
                    }

                    if (nextred[0] >= N) {
                        nextred[0] = N - 1;
                    }
                    if (nextblue[0] >= N) {
                        nextblue[0] = N - 1;
                    }
                    if (nextred[1] >= M) {
                        nextred[1] = M - 1;
                    }
                    if (nextblue[1] >= M) {
                        nextblue[1] = M - 1;
                    }*/
                }
                if (change) {
                    switch (i) {
                        case 0:
                            dfs(index + 1, 2, nextred, nextblue);
                            break;
                        case 1:
                            dfs(index + 1, 3, nextred, nextblue);
                            break;
                        case 2:
                            dfs(index + 1, 0, nextred, nextblue);
                            break;
                        case 3:
                            dfs(index + 1, 1, nextred, nextblue);
                            break;
                        default:
                    }
                }
            }
        }
    }
}
