import java.util.Scanner;

public class Decimal_1929 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int M = scanner.nextInt();
        int N = scanner.nextInt();

        boolean[] decimal = new boolean[N+1];
        //false 를 소수로.
        decimal[1] = true;

        for(int i= 2; i < decimal.length; i++) {
            for(int j = 2; i*j < decimal.length; j++) {
                decimal[i*j] = true;
            }
        }

        for(int i=M; i<=N; i++) {
            if(decimal[i] == false)
                System.out.println(i);
        }

    }
}
