import java.util.Scanner;

public class MergeColorPaper_17136 {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] map = new int[10][10];

        boolean allZero = true;

        for(int i=0; i<10; i++) {
            for(int j=0; j<10; j++) {
                map[i][j] = sc.nextInt();
                if(map[i][j] == 1) {
                    allZero = false;
                }
            }
        }

        if(allZero) {
            System.out.println(0);
            System.exit(0);
        }
        //색종이 다섯 종류, 각 5개씩.
        int[] myPaper = {0, 5, 5, 5, 5, 5};

        dfs(map,0,0, 0, myPaper);

        if(min == Integer.MAX_VALUE) {
            min = -1;
        }

        System.out.println(min);
    }

    public static void dfs(int[][] map, int y, int x, int paper, int[] myPaper) {
        for(int i=y; i<10; i++) {
            for(int j=x; j<10; j++) {
                if(map[i][j] == 1) {
                    for(int k=1; k<=5; k++) {
                        AddColorPaper(map, k, i, j, paper, myPaper.clone());
                    }
                    return;
                }
            }
            x = 0;
        }

        for(int i=y; i<10; i++) {
            for (int j = x; j < 10; j++) {
                if(map[i][j] == 1) {
                    return;
                }
            }
        }

        min = Math.min(min, paper);
    }

    public static void AddColorPaper(int[][] map, int k, int y, int x, int paper, int[] myPaper) {
        int[][] result = new int[10][10];

        for(int i=0; i<10; i++) {
            result[i] = map[i].clone();
        }

        myPaper[k]--;

        if(myPaper[k] == -1) {
            return;
        }

        for(int i = y; i < y+k; i++) {
            for(int j = x; j < x+k; j++) {
                if(i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return;
                }

                if(result[i][j] == 0) {
                    return;
                }
                result[i][j] = 0;
            }
        }

        x++;
        if(x == 10) {
            y++;
            x = 0;
        }

        dfs(result, y, x ,paper+1, myPaper);
    }
}
