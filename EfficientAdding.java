package PS8;
import java.util.*;

public class EfficientAdding {
    
    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        //Read in the total number of elements
        int n = in.nextInt();

        //PriorityQueue that will give us the smallest number in the list every time we .poll()
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        //Add each number to the priority queue
        for (int i = 0; i < n; i++){
            pq.add(in.nextInt());
        }

        in.close(); //Close the scanner

        int numA;   //Variable to store the first num we look at in a case
        int numB;   //Variable to store the second num we look at in a case
        long overallTotal = 0;   //The overall cost starts at 0
        int tempTotal;  //Will hold the current case's total of numA + numB

        while (pq.size() > 1){

            numA = pq.poll();   //Poll the smallest number in the priority queue
            numB = pq.poll();   //Poll the next smallest number in the priority queue

            tempTotal = numA + numB;  

            //Add the temporary total to the priority queue, because we will have to add
            // to this number again later
            // But it might not be the smallest number in the priority queue right now
            pq.add(tempTotal);

            //Increase the total cost by how much this case cost(ed), which is tempTotal
            overallTotal += tempTotal;

        }

        //Print out the overall cost (minimized) of adding all the numbers
        System.out.println(overallTotal);

    }

}
