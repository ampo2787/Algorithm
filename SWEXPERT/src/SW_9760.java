import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_9760 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for(int tc=1; tc<=T; tc++) {
            String[] card = new String[5];
            boolean[] visit = new boolean[5];

            st = new StringTokenizer(br.readLine());

            for (int i = 0; i < 5; i++) {
                card[i] = st.nextToken();
            }


        }
    }
}
