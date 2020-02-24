import java.util.Scanner;

public class N_PUZZLE {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] line = new String[4];

        String[] correct = {"ABCD","EFGH","IJKL","MNO."};

        int dist = 0;

        for(int i=0; i<line.length; i++) {
            line[i] = scanner.nextLine();
        }

        for(int i=0; i<line.length; i++) {
            String thisLine = line[i];
            for(int j=0; j<thisLine.length(); j++) {
                char thisChar = thisLine.charAt(j);

                if(thisChar == '.' || correct[i].charAt(j) == thisChar) {
                    continue;
                }
                else {
                    for(int k=0; k<correct.length; k++) {
                        if(correct[k].contains(String.valueOf(thisChar))) {
                            int index = correct[k].indexOf(String.valueOf(thisChar));
                            dist += Math.abs(k-i) + Math.abs(index - j);
                        }
                    }

                }

            }
        }
        System.out.print(dist);

    }
}
