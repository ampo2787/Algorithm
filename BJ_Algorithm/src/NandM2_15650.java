import java.util.Scanner;

public class NandM2_15650 {
    static int[] list;
    static boolean[] visit;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        sb = new StringBuilder();

        N = scanner.nextInt();
        M = scanner.nextInt();

        list = new int[M]; // 현재 선택된 숫자들을 나타냄.
        visit = new boolean[N+1]; // 선택할 수 있는 숫자들을 나타냄.

        dfs(1,0);
        System.out.print(sb);
    }

    public static void dfs(int current, int d){
        if(d==M) { // 현재 배열이 다 선택되었다.
            for (int a:list){
                sb.append(a + " "); //출력한다.
            }
            sb.append("\n"); //다 출력했으니 줄을 바꿔 준다.
            return; //현재 반복은 완료.
        }

        for (int i=current; i<=N; i++) {
            if(!visit[i]) {                //아직 선택되지 않은 수라면.
                visit[i] = true; //선택됨을 표시.
                list[d] = i; //현재 위치에 숫자를 넣음.
                dfs(i, d + 1); //다음 자리로.
                visit[i] = false; //재귀가 끝난 상태. 즉 선택되었던 것을 풀어 줌.
            }
        }
        return;
    }
}
