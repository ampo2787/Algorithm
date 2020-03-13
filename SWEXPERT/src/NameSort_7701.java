import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NameSort_7701 {
    static int N;

//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int TC = Integer.parseInt(br.readLine());
//        StringBuilder sb = new StringBuilder();
//
//        for (int tc = 1; tc <= TC; tc++) {
//            N = Integer.parseInt(br.readLine());
//            TreeSet<String>[] tsrr = new TreeSet[51];
//
//            for (int i = 1; i < 51; i++) {
//                tsrr[i] = new TreeSet<String>();
//            }
//
//            for (int i = 0; i < N; i++) {
//                String str = br.readLine();
//                tsrr[str.length()].add(str);
//            }
//
//            sb.append("#" + tc + "\n");
//
//            for (int i = 1; i < 51; i++) {
//                for (String string : tsrr[i]) {
//                    sb.append(string).append('\n');
//                }
//            }
//        }
//        System.out.print(sb);
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int tc = 1; tc <= TC; tc++) {
            N = Integer.parseInt(br.readLine());
            HashSet<String>[] sets = new HashSet[51];

            for(int i=1; i<51; i++) {
                sets[i] = new HashSet<>();
            }

            for (int i = 0; i < N; i++) {
                String str = br.readLine();
                sets[str.length()].add(str);
            }

            sb.append("#" + tc + "\n");

            ArrayList<String>[] lists = new ArrayList[51];

            for(int i=1; i<51; i++) {
                lists[i] = new ArrayList<>();

                for (String string : sets[i]) {
                    lists[i].add(string);
                }

                Collections.sort(lists[i], new Comparator<String>() {
                    @Override
                    public int compare(String s, String t1) {
                        for(int i=0; i<s.length(); i++) {
                            if(s.charAt(i) < t1.charAt(i)) {
                                return -1;
                            }
                            else if(s.charAt(i) > t1.charAt(i)) {
                                return 1;
                            }
                            else {

                            }
                        }
                        return 0;
                    }
                });

                for (String string : lists[i]) {
                    sb.append(string + "\n");
                }
            }
        }

        System.out.print(sb);
    }
}