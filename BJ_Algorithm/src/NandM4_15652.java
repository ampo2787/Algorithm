import java.util.Scanner;

public class NandM4_15652 {
        static int N,M;
        static StringBuilder sb = new StringBuilder();
        static int[] list;

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            M = sc.nextInt();
            list = new int[M]; //선택된 수열.

            dfs(1,0);
            System.out.print(sb);
        }

        public static void dfs(int privious, int index){
            if(index==M){
                for(int i=0; i<M; i++) {
                    sb.append(list[i] + " ");
                }
                sb.append("\n");
                return;
            }

            for(int i=privious; i<N+1; i++) {
                list[index] = i;
                dfs(i, index+1);
            }
        }
    }
