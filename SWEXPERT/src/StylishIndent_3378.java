import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class StylishIndent_3378 {
    static int[][] master;
    static int[][] myStyle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());

            master = new int[p][4]; // {온점, 닫히지 않은 소괄호, 중괄호, 대괄호)
            myStyle = new int[q][4];

            for(int i=0; i<p; i++) {
                char[] line = br.readLine().toCharArray();
                int finish = 0;
                int small = 0;
                int middle = 0;
                int big = 0;

                if(i != 0) {
                    small = master[i-1][1];
                    middle = master[i-1][2];
                    big = master[i-1][3];
                }

                boolean start = true;
                for(int j=0; j<line.length; j++) {
                    if(line[j] == '.' && start) {
                        finish++;
                        continue;
                    } else {
                        start = false;
                    }

                    switch (line[j]) {
                        case '(':
                            small++;
                            break;
                        case ')':
                            small--;
                            break;
                        case '{':
                            middle++;
                            break;
                        case '}':
                            middle--;
                            break;
                        case '[':
                            big++;
                            break;
                        case ']':
                            big--;
                            break;
                        default:
                            break;
                    }
                }
                master[i][0] = finish;
                master[i][1] = small;
                master[i][2] = middle;
                master[i][3] = big;
            }

            for(int i=0; i<q; i++) {
                char[] line = br.readLine().toCharArray();
                int small = 0;
                int middle = 0;
                int big = 0;

                if(i != 0) {
                    small = myStyle[i-1][1];
                    middle = myStyle[i-1][2];
                    big = myStyle[i-1][3];
                }

                for(int j=0; j<line.length; j++) {
                    switch (line[j]) {
                        case '(':
                            small++;
                            break;
                        case ')':
                            small--;
                            break;
                        case '{':
                            middle++;
                            break;
                        case '}':
                            middle--;
                            break;
                        case '[':
                            big++;
                            break;
                        case ']':
                            big--;
                            break;
                    }
                }
                myStyle[i][1] = small;
                myStyle[i][2] = middle;
                myStyle[i][3] = big;
            }

            int R = -1,C = -1,S = -1;
            // R,C,S 구하기.

            for(int i=0; i<q; i++) {
                myStyle[i][0] = -2;
            }

            for(int r=1; r<=20; r++) {
                for(int c=1; c<=20; c++) {
                    for(int s=1; s<=20; s++) {
                        if (check(r,c,s)) {
                            cal(r,c,s);
                        }
                    }
                }
            }

            sb.append("#" + tc + " 0 ");
            for(int i=1; i<q; i++) {
                sb.append(myStyle[i][0] + " ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    public static void cal(int r, int c, int s) {
        for(int i=1;i<myStyle.length; i++) {
            int x = myStyle[i-1][1] * r + myStyle[i-1][2] * c + myStyle[i-1][3] * s;
            if(myStyle[i][0] == -2) {
                myStyle[i][0] = x;
            }
            else if(myStyle[i][0] != x){
                myStyle[i][0] = -1;
            }
        }
    }

    public static boolean check(int r, int c, int s) {
        for(int i=1; i < master.length; i++) {
            if(master[i][0] != master[i-1][1] * r + master[i-1][2] * c + master[i-1][3] * s) {
                return false;
            }
        }
        return true;
    }
}
