import java.util.Scanner;

public class Main {
    static int whitePaper = 0;
    static int bluePaper = 0;
    static boolean allPaper = true;
    static int firstPaper;
    static int[][] paper;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        paper = new int[n][n];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                paper[i][j] = sc.nextInt();
            }
        }
        //0 : white, 1 : blue.
        recursive(0,0, n);

        System.out.println(whitePaper);
        System.out.println(bluePaper);
    }

    public static void recursive(int startX,int startY, int n) {

        if(n == 1) {
            if(paper[startX][startY] == 0) {
                whitePaper++;
            }
            else {
                bluePaper++;
            }
            return;
        }

        allPaper = true;

        firstPaper = paper[startX][startY];

        for(int i = startX; i<startX + n; i++) {
            for(int j = startY; j<startY + n; j++) {
                if(firstPaper != paper[i][j]) {
                    allPaper = false;
                    break;
                }
            }
            if(!allPaper) {
                break;
            }
        }

        if(!allPaper) {
            recursive(startX, startY, n / 2);
            recursive(startX + n/2, startY, n / 2);
            recursive(startX + n/2, startY + n/2, n / 2);
            recursive(startX, startY + n/2, n / 2);
        }
        else {
            if(firstPaper == 0) {
                whitePaper++;
            }
            else {
                bluePaper++;
            }
        }
    }
}
