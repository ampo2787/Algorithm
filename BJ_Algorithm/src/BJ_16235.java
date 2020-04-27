import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_16235 {
    static int[] dy = {-1, -1, 0, 1 ,1 ,1 ,0,-1};
    static int[] dx = {0,1,1,1,0,-1,-1,-1};
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();
        int[][] A = new int[N + 1][N + 1];
        Deque<Tree>[][] tree = new Deque[N+1][N+1];
        // 나무 위치 y, x, 마지막은 나무의 나이.

        for(int i=1; i< N + 1; i++) {
            for(int j=1; j<N + 1; j++) {
                A[i][j] = sc.nextInt();
                tree[i][j] = new ArrayDeque<>();
            }
        }

        for(int i=0; i<M; i++) {
            Tree thisTree = new Tree();
            int y = sc.nextInt();
            int x = sc.nextInt();
            thisTree.age = sc.nextInt();
            tree[y][x].offer(thisTree);
        }

        // 양분은 모든 칸에 처음에 5 들어있다.
        // 봄 -> 나무의 나이만큼 양분을 흡수, 나이가 1 증가
        // 한 칸에 여러 나무가 있으면 어린 나무부터 먹음. 양분이 부족해서 못 먹으면 나무가 죽음.
        // 여름 -> 죽은 나무가 양분으로 변함. 양분 += 죽은 나무 나이 / 2
        // 가을 -> 나이가 5의 배수인 나무가 번식. 8방향으로 나이 1인 나무 추가.
        // 겨울 -> 양분 A[r][c] 모든 땅에 추가.
        // K 년 후 살아있는 나무의 개수는?

        int[][] resource = new int[N+1][N+1];

        for(int i=1; i<N+1; i++) {
            for(int j=1; j<N+1; j++) {
                resource[i][j] = 5;
            }
        }

        for(int k=0; k<K; k++) {
            for(int i=1; i< N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    boolean dead = false;

                    for(Iterator<Tree> it = tree[i][j].iterator(); it.hasNext() ; ) {
                        Tree t = it.next();
                        if(!dead) {
                            if(resource[i][j] >= t.age) {
                                resource[i][j] -= t.age;
                                t.age++;
                            }
                            else {
                                dead = true;
                                resource[i][j] += t.age / 2;
                                it.remove();
                            }
                        }
                        else {
                            resource[i][j] += t.age / 2;
                            it.remove();
                        }
                    }
                }
            }

            //위에는 봄, 여름.
            //아래는 가을, 겨울.
            Deque<Tree>[][] nextTree = new Deque[N+1][N+1];
            for(int i=1; i<N+1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    nextTree[i][j] = new ArrayDeque<>();
                }
            }
            for(int i=1; i<N+1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    for(Tree t : tree[i][j]) {
                        nextTree[i][j].offer(t);
                        if(t.age % 5 == 0) {
                            for(int d=0; d<8; d++) {
                                int ny = i + dy[d];
                                int nx = j + dx[d];
                                if(ny < 1 || ny >= N + 1 || nx < 1 || nx >= N + 1) {
                                    continue;
                                }
                                Tree newTree = new Tree();
                                newTree.age = 1;
                                nextTree[ny][nx].offerFirst(newTree);
                            }
                        }
                    }
                    resource[i][j] += A[i][j];
                }
            }
            tree = nextTree;
        }
        int result = 0;

        for(int i=1; i< N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                for(Tree t : tree[i][j]) {
                    result++;
                }
            }
        }

        System.out.println(result);
    }

    static class Tree implements Comparable<Tree> {
        int age;

        public Tree() {

        }

        @Override
        public int compareTo(Tree t) {
            return this.age - t.age;
        }
    }
}
