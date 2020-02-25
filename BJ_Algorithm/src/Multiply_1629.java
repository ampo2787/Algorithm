import java.util.Scanner;

public class Multiply_1629 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextInt();
        long b = sc.nextInt();
        long c = sc.nextInt();
        System.out.println(solve(a,b, c));
    }

    public static long solve(long a, long b, long c) {
        if(b == 0) {
            return 1;
        }

        long temp = solve(a,b/2, c);
        temp = temp * temp % c;

        if(b%2 == 0) {
            return temp;
        }
        else {
            return a * temp % c;
        }
    }
}
