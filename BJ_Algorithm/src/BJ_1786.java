import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1786 {
    static int count = 0;
    static StringBuilder stringBuilder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        String pattern = br.readLine();
        KMP(text,pattern);
        stringBuilder.insert(0, count + "\n");
        System.out.println(stringBuilder);
    }

    static int[] getPi(String pattern) {
        // 접두사와 접미사가 일치하는 최대길이를 담을 배열 선언
        int[] pi = new int[pattern.length()];

        // idx
        int j = 0;
        // i,j가 가리키는 값이 일치하면 둘다 증가
        // 불일치하면 i만 증가시켜야 하므로 for문
        for (int i = 1; i < pattern.length(); i++) {
            // pattern 내에서 i와 j가 가리키는 값이 다를때
            // while 문안에 넣는 이유는 중간단계를 건너뛰고 최대한으로 점프하려고
            while (j > 0 && pattern.charAt(i) != pattern.charAt(j)) {
                // j의 값을 한칸 뺀 곳의 값으로 j를 바꿈
                j = pi[j - 1];
            }
            // pattern 내에서 i와 j가 가리키는 값이 같으면
            if (pattern.charAt(i) == pattern.charAt(j)) {
                // i번째의 최대길이는 ++j한 값
                pi[i] = ++j;
            }
        }
        return pi;
    }

    static void KMP(String parent, String pattern) {
        int[] table = getPi(pattern);
//		System.out.println("점프테이블");
//		System.out.println(Arrays.toString(table));
        int j = 0;
        for(int i = 0 ; i< parent.length(); i++) {
            while(j > 0 && parent.charAt(i) != pattern.charAt(j)) {
                j = table[j - 1];
            }
            //부모와 패턴이 일치한다면
            if(parent.charAt(i) == pattern.charAt(j)) {
                //j의 값이 패턴의 길이-1이라면 한번 다찾은거니까
                //찾아다고 처리
                if(j == pattern.length()-1) {
                    count++;
                    stringBuilder.append((i-pattern.length() + 1 + 1) + " ");
                    //패턴을 또 찾기 위해서
                    j = table[j];
                }else {
                    //다찾은건아니라면 계속 진행해야하므로 j값 증가
                    j++;
                }
            }
        }

    }
}
