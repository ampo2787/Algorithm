import java.util.Scanner;

public class Main {
    static int N,M;
    static StringBuilder sb = new StringBuilder();
    static int[] list;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        list = new int[M]; //선택된 수열.

        dfs(0);
        System.out.print(sb);
    }

    public static void dfs(int index){
        if(index==M){
            for(int i=0; i<M; i++) {
                sb.append(list[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<N+1; i++) {
                list[index] = i;
                dfs(index+1);
        }
    }
}
