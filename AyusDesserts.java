package PS8;

import java.util.*;

public class AyusDesserts {

	static int M;   //Ayu's budget limit
    static int[][] memo;    //Memoization table to store answers for subproblems that have already been calculated
	static int[][] price;   //Stores the price for dessert of type i and flavor number j in price[i][j]
	
	public static void main(String[] args){

        Scanner in = new Scanner(System.in);

        M = in.nextInt();
        int N = in.nextInt();   //N = the number of types of desserts

        //Holds the answers for how much we can spend on dessert type N when we have M dollars
        memo = new int[N+1][M+1];   //Originally declared as static so we can access in shop() function

        //Holds the prices of each dessert for each flavor of the dessert offered
        price = new int[N][];   //originally declared as static so we can access in shop() function

        //For each type of dessert...
        for(int i = 0; i < N; i++){

            int k = in.nextInt();   //Read in the number of flavors the dessert has
            //System.out.println("\nk: " + k);
            //System.out.println("prices of k: ");
            price[i] = new int[k];  //Initialize the array of flavors for dessert type i

            //Read in each flavor's price
            for(int j = 0; j < k; j++){
                price[i][j] = in.nextInt();
                //System.out.println(price[i][j]);
            }
        }

        in.close(); //close the scanner

        //Initialize all values in the memoization table to -1
        for(int i = 0; i < memo.length; i++){
            Arrays.fill(memo[i], -1);
        }        
        
        //Call the recursive function that uses memoization
        int answer = shop(0,0);

        //If the answer is negative one, there is no solution for Ayu
        if (answer == -1){
            System.out.println("no solution");
        }
        
        //Else, print out the maximum amount Ayu can spend on desserts
        else{
            System.out.println(answer);
        }
	}

    //Bottom-up approach to solving Ayu's Desserts
    public static int shop(int m, int i)
	{
        //If we have exceeded the original amount of money, no solution is possible
		if (m > M){
            return -1;
        }

        //If we have reached the last flavor of dessert, return m
		if (i == price.length){
            return m;
        }

        //If we have already calculated this solution previously, return the value we already calculated
		if (memo[i][m] != -1){
            return memo[i][m];
        }

        //ELSE: 
        int best = -1;    //Set result variable and initialize to -1

        //Calculate the maximum Ayu can spend if she chooses dessert flavor j --> m+j
        //And then we move onto picking the next dessert --> i+1
		for(int j : price[i]){
            best = Math.max(best, shop(m+j, i+1));
        }
		
        //Return the max in the memo table
		return memo[i][m] = best;
	}

}