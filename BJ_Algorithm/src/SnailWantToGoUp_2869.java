import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double a = sc.nextInt();
        double b = sc.nextInt();
        double v = sc.nextInt();
    //10억.
        if(a >= v) {
            System.out.println("1");
        }
        else {
            System.out.println((int) Math.ceil((v-a) / (a-b)) + 1);
        }

    }
}
