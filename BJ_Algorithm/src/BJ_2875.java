import java.util.Scanner;

public class BJ_2875 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int team = N/2;

        if(team > M) {
            team = M;
        }

        int remain = N - team * 2 + M - team;
        K = K - remain;

        if(K > 0) {
            team = (team * 3 - K)/3;
        }

        System.out.println(team);
    }
}
