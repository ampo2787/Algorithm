import java.util.HashMap;
import java.util.Scanner;

public class NumberCard2_10816 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int n = sc.nextInt();
        int[] card = new int[n];
        HashMap<Integer, Integer> cardMap = new HashMap();

        for(int i=0; i<n; i++) {
            card[i] = sc.nextInt();
            if(cardMap.containsKey(card[i])) {
                cardMap.replace(card[i], cardMap.get(card[i]) + 1);
            }
            else {
                cardMap.put(card[i], 1);
            }
        }

        int m = sc.nextInt();

        int[] targetCard = new int[m];

        for(int i=0; i<m; i++) {
            targetCard[i] = sc.nextInt();
            if(cardMap.containsKey(targetCard[i])) {
                sb.append(cardMap.get(targetCard[i]) + " ");
            }else {
                sb.append("0 ");
            }
        }

        System.out.println(sb);


    }
}
