import java.util.Scanner;

public class LeaveCompany_14501 {
    static int N;
    static int[][] plan;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        plan = new int[N+1][2];
        max = 0;

        for(int i=1; i < N+1; i++) {
            plan[i][0] = sc.nextInt();
            plan[i][1] = sc.nextInt();
        }

        dfs(1,0);
        System.out.println(max);
    }

    public static void dfs(int day, int money) {
        if(day > N) {
            if(day == N+1) {
                max = Math.max(money,max);
            }
            return;
        }

        max = Math.max(money,max);

        dfs(day+1, money);
        dfs(day + plan[day][0], money + plan[day][1]);

    }
}
