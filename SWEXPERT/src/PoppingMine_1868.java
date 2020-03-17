import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class PoppingMine_1868 {
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int size;
    static int[][] ref;
    static boolean[][] board;

    static Queue<Loc> queue;

    public static void spread() {
        Loc current = queue.poll();
        board[current.y][current.x] = true;

        for(int d=0; d<8; d++) {
            int ny = current.y + dy[d];
            int nx = current.x + dx[d];

            if(ny < 0 || ny >= size || nx < 0 || nx >= size || board[ny][nx]) {
                continue;
            }

            board[ny][nx] = true;

            if(ref[ny][nx] == 0) {
                queue.add(new Loc(ny, nx));
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            // 입력 겸 초기화.
            size = Integer.parseInt(br.readLine());
            board = new boolean[size][size];
            for(int i=0; i<size; i++) {
                String input = br.readLine();
                for(int j=0; j<size; j++) {
                    if(input.charAt(j) == '*') {
                        board[i][j] = true;
                    }
                }
            }

            // 주변 지뢰 개수 카운팅.
            ref = new int[size][size];
            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    int count = 0;
                    for(int d=0; d<8; d++) {
                        int ny = i + dy[d];
                        int nx = j + dx[d];

                        if(ny < 0 || ny >= size || nx < 0 || nx >= size) {
                            continue;
                        }

                        if(board[ny][nx])
                            count++;
                    }
                    ref[i][j] = count;
                }
            }

            int result = 0;
            queue = new LinkedList<Loc>();

            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    if(board[i][j] || ref[i][j] != 0) {
                        continue; // 지뢰이거나, 주변에 지뢰가 잇는 경우 스킵.
                    }
                    result++;
                    queue.add(new Loc(i,j));

                    while(!queue.isEmpty()) {
                        spread();
                    }
                }
            }

            for(int i=0; i<size; i++) {
                for (int j = 0; j < size; j++) {
                    if(!board[i][j]) {
                        result++;
                    }
                }
            }

            sb.append("#" + tc + " " + result + "\n");
        }

        System.out.print(sb);
    }

    public static class Loc {
        int y, x;
        public Loc(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
