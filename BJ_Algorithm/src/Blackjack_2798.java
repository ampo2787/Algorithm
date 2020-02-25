import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack_2798 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt(); // 3 <= N <=100 카드 개수
        int M = scanner.nextInt(); // 10 <= M <= 300000 카드에 쓰여 있는 수

        ArrayList<Integer> Card = new ArrayList<>();

        for (int i = 0; i < N; i ++) {
            Card.add(scanner.nextInt());
        }
        int TempNum = 0;

        for (int a = 0; a < N; a++) {
            for (int b = a+1; b < N; b++) {
                for (int c = b+1; c < N; c++){
                    int Sum = Card.get(a) + Card.get(b) + Card.get(c);
                    if(Sum <= M && Sum > TempNum) {
                        TempNum = Sum;
                        if(Sum == M){
                            System.out.println(TempNum);
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(TempNum);
    }
}
