import java.util.Scanner;

public class BJ_1062 {
    static int N, K;
    static String[] inputWords;
    static int max;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        sc.nextLine();

        boolean[] visit = new boolean[26];
        inputWords = new String[N];
        int wordNum = 0;

        for (int i = 0; i < N; i++) {
            inputWords[i] = sc.nextLine();

            for (int j = 0; j < inputWords[i].length(); j++) {
                visit[inputWords[i].charAt(j) - 'a'] = true;
            }
        }

        for (int i = 0; i < 26; i++) {
            if(visit[i] == true) {
                wordNum++;
            }
        }

        // 글자 26개 중 K개만을 가르칠 수 있다.
        // 길이는 8~15개. 중복 x.

        // anta  +  tica -> a n t i c -> 5개.
        // antic 5개는 필수.

        // 글자를 받을 때 어느 알파벳이 필요한지 boolean 배열로 표기한다.

        // rc hello car -> a n t i c +++ r -> rc car

        // 반례 : 하나만 가르칠 수 있음. -> a, 세 개 -> b c d
        // a
        // b c
        // b c d

        K-=5;

        if(K < 0) {
            // antic 는 필수.
            System.out.println(0);
            System.exit(0);
        }

        if(wordNum <= K) {
            // 무조건 전부 출력가능.
            System.out.println(N);
            System.exit(0);
        }

        // 값이 true -> 존재하는 알파벳 중에 K 개를 선택하는 경우의 수?
        boolean[] alphabet = new boolean[26];
        alphabet['a' - 'a'] = true;
        alphabet['n' - 'a'] = true;
        alphabet['t' - 'a'] = true;
        alphabet['i' - 'a'] = true;
        alphabet['c' - 'a'] = true;

        dfs(0, 0, alphabet);

        System.out.println(max);
    }

    public static void dfs(int start, int count, boolean[] alphabet) {
        if(count == K) {
            int result = 0;

            for(int i=0; i<N; i++) {
                boolean isOk = true;

                for(int j=0; j<inputWords[i].length(); j++) {
                    if(!alphabet[inputWords[i].charAt(j)-'a']) {
                        isOk = false;
                        break;
                    }
                }

                if(isOk)
                    result++;
            }
            max = Math.max(result, max);
            return;
        }

        for(int i = start; i < 26; i++) {
            if(!alphabet[i]) {
                alphabet[i] = true;
                dfs(i, count + 1, alphabet);
                alphabet[i] = false;
            }
        }
    }
}

