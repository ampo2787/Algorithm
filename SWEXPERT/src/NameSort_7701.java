import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class NameSort_7701 {
    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            TreeSet<String>[] tsrr = new TreeSet[51];

            for (int i = 1; i < 51; i++) {
                tsrr[i] = new TreeSet<String>();
            }

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                tsrr[str.length()].add(str);
            }

            sb.append("#" + tc + "\n");

            for (int i = 1; i < 51; i++) {
                for (String string : tsrr[i]) {
                    sb.append(string).append('\n');
                }
            }
        }
        System.out.print(sb);
    }
}