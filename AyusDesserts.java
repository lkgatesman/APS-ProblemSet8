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

/* PROBLEM STATEMENT
Ayu’s Shopping
Ayu is keen on desserts. She found that she will run out of them soon so she decided to buy some more today. Her favorite
dessert shop offers several flavors (banana, grape, vanilla, etc.) for every kind of dessert (candy, cake, ice-cream, etc.). Ayu
has M dollars and she wants to spend as much of that money as possible. She also doesn’t want to get too fat so she
decided to buy only one flavor of each kind of dessert. The question is how much money will she spend.

Input
The first line of the input contains two integers M (1 <= M <= 200) and N (1 <= N <= 20), indicating the amount of money
Ayu has and the number of different kinds of desserts. Each of the following N lines contains K + 1 integers where K is the
first integer of that line (1 <= K <= 20), indicating the number of flavors for this kind of dessert. The following K integers
indicate the price for each flavor of the dessert (these are integers).

Output
Print one line consisting of one integer indicating the maximum amount of money spent to buy one flavor of each kind of
dessert, or no solution if there is no solution.

EXAMPLES
Example 1

Input:
100 4
3 8 6 4
2 5 10
4 1 3 3 7
4 50 14 23 8

Output: (by picking 8, 10, 7 and 50)
75


Example 2

Input:
20 3
3 4 6 8
2 5 10
4 1 3 5 5

Output:
19

Example 3
Input:
5 3
3 6 4 8
2 10 6
4 7 3 1 7

Output:
no solution

*/
