import java.util.Scanner;

public class SwimmingPool_1952 {

    static int dayUse;
    static int oneMonthUse;
    static int threeMonthUse;
    static int yearUse;
    static int[] plan;
    static int[] monthUsePay;
    static int finalValue;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int test_case=1; test_case<=T; test_case++) {
            dayUse = sc.nextInt();
            oneMonthUse = sc.nextInt();
            threeMonthUse = sc.nextInt();
            yearUse = sc.nextInt();

            plan = new int[13];
            boolean noUse = true;

            for(int i=1;i <13; i++) {
                plan[i] = sc.nextInt();
                if(plan[i] != 0) {
                    noUse = false;
                }
            }

            if (noUse) {
                System.out.println("#" + test_case + " " + 0);
                continue;
            }

            // 1. 이용 계획이 없을 때 -> 0을 출력.
            // 2. 1년 이용권 사용 가정 -> 이용 금액 최대는 1년 이용.권
            // 3. 실제로 따질 건 3달, 1달 , 1일.
            // 4. 한 달에 1일 이용권 이용 금액이 1달 이용권 보다 넘어가면 1달로 쳐버림
            // 5. 세 달에 1달 이용권 금액이 세 달 보다 넘어가면 3달로 쳐버림.

            monthUsePay = new int[13];

            for(int i = 1;i <13; i++) {
                if(plan[i] * dayUse < oneMonthUse) {
                    monthUsePay[i] = plan[i] * dayUse;
                }
                else {
                    monthUsePay[i] = oneMonthUse;
                }
            }
            finalValue = yearUse;
            //달마다 1일 이용권이 이득인 달과, 1달 이용권이 이득인 달을 구분해 저장함.
            //그 후 3달 이용권으로 사용 가능한 모든 경우를 구해보고, 최종 결과를 도출한다.
            combination(0, 1);
            System.out.println("#" + test_case + " " + finalValue);
        }
    }

    public static void combination(int result, int index){
        if(index >= 13) {
            finalValue = Math.min(finalValue,result);
            return;
        }

        combination(result + threeMonthUse, index + 3);
        combination(result + monthUsePay[index], index + 1);

    }

}
