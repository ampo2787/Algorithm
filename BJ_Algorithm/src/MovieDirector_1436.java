import java.util.Scanner;

public class MovieDirector_1436 {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int num = 665;

        String str;
        while(N > 0) {
            num++;
            str = Integer.toString(num);

            if(str.contains("666"))
                N--;
        }
        System.out.println(num);
    }
}
