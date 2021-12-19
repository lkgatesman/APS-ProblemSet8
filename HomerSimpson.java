import java.util.*;

public class HomerSimpson{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()){

            String inputString = in.nextLine();
            String[] input = inputString.split(" ");

            int m = Integer.parseInt(input[0]);
            int n = Integer.parseInt(input[1]);
            int t = Integer.parseInt(input[2]);

            int[][] bb = new int[t+1][2];
            for (int j = 0; j <= t; j++){
                bb[j][0] = -1;
                bb[j][1] = -1;
            }

            //Then call the function here
            int answer = homersDiet(m, n, t, bb);

            if (bb[answer][1] > 0){
                System.out.println(bb[answer][0] + " " + bb[answer][1]);
            }
            else{
                System.out.println(bb[answer][0]);
            }

        }

    }

    public static int homersDiet(int m, int n, int t, int[][] bb){

        int burgerOne = Math.min(m, n);
        int burgerTwo = Math.max(m, n);
        //System.out.println("burgerOne: " + burgerOne);
        //System.out.println("burgerTwo: " + burgerTwo);

        int modm;
        int divm;
        int burgers;
        int answer;

        for (int i = t; i >= 0; ){

            divm = i / burgerOne;
            modm = i % burgerOne;

            if (modm != 0){
                if (modm % burgerTwo == 0){
                    burgers = divm + (modm / burgerTwo);
                    bb[i][0] = burgers;
                    bb[i][1] = 0;
                }
                else{
                    burgers = divm;
                    int diff = t - (divm * burgerOne);
                    burgers += (diff / burgerTwo);
                    bb[i][0] = burgers;
                    bb[i][1] = modm;
                }
            }

            else{
                burgers = divm;
                int diff = t - (divm * burgerOne);
                burgers += (diff / burgerTwo);
                bb[i][0] = burgers;
                bb[i][1] = modm;   
                //answer = i;
                //return answer;           
            }

            /*System.out.println("time " + i);
            System.out.println("burgers: " + bb[i][0]);
            System.out.println("beers  : " + bb[i][1]);*/

            i = i - burgerTwo;
        }

        answer = 0;
        int currentMin = Integer.MAX_VALUE;
        for (int i = 0; i <= t; i++){

            if (bb[i][1] == -1){
                continue;
            }

            if (bb[i][1] <= currentMin){
                answer = i;
                currentMin = bb[i][1];
            }

        }

        return answer;


    }

}
