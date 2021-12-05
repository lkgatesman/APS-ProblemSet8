package PS8;
import java.util.*;

public class LaughingMonsters{

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        Double d = in.nextDouble();
        int unionOps = 0;

        //System.out.println("\n number of seats: " + n + ", D = " + d);

        in.nextLine();  //Read out the line break

        //Create the disjoint set with n elements
        DisjointUnionSets dus = new DisjointUnionSets(n);

        Seat newSeat;
        Seat[] seatArr = new Seat[n];

        //For each seat...
        for (int i = 0; i < n; i++){

            newSeat = new Seat(i, in.nextDouble(), in.nextDouble());
            in.nextLine(); //read out the line break

            /*System.out.println("seat #" + newSeat.index);
            System.out.println("x = " + newSeat.x);
            System.out.println("y = " + newSeat.y + "\n");*/

            //Add it to the array of seats that have already been read in
            seatArr[i] = newSeat;

            //Call function to find adjacent seats
            unionOps += findAdjacentSeats(dus, seatArr, newSeat, d);

        }

        in.close();

        System.out.print(n - unionOps);


    }

    public static int findAdjacentSeats(DisjointUnionSets dus, Seat[] seatArr, Seat s1, Double d){

        Seat s2;
        Double distance;
        Double xDiff;
        Double yDiff;

        //For each seat before the current seat...
        for (int i = 0; i < s1.index; i++){

            s2 = seatArr[i];

            //System.out.println("calculating the distance between " + s2.index + " and " + s1.index);

            //Calculate the distance between the current seat and s1
            xDiff = Math.pow(s2.x - s1.x, 2);
            yDiff = Math.pow(s2.y - s1.y, 2);

            //System.out.println("xDiff = " + xDiff);
            //System.out.println("yDiff = " + yDiff);

            distance = Math.sqrt(xDiff + yDiff);
            //System.out.println("distance = " + distance + "\n\n");

            //if the distance is less than or equal to D, union them and break the function
            if (distance <= d){
                //System.out.println("here");
                dus.union(s1.index, s2.index);
                return 1;
            }

        }

        //if we reach here, then there were no adjacent seats, which means we do nothing
        return 0;

    }

}

class Seat{

    int index;
    Double x;
    Double y;

    Seat(int index, Double x, Double y){

        this.index = index;
        this.x = x;
        this.y = y;

    }

}


//Disjoint Set Implementation from: https://www.geeksforgeeks.org/disjoint-set-data-structures/ 
class DisjointUnionSets {
	int[] rank, parent;
	int n;

	// Constructor
	public DisjointUnionSets(int n)
	{
		rank = new int[n];
		parent = new int[n];
		this.n = n;
		makeSet();
	}

	// Creates n sets with single item in each
	void makeSet()
	{
		for (int i = 0; i < n; i++) {
			// Initially, all elements are in
			// their own set.
			parent[i] = i;
		}
	}

	// Returns representative of x's set
	int find(int x)
	{
		// Finds the representative of the set
		// that x is an element of
		if (parent[x] != x) {
			// if x is not the parent of itself
			// Then x is not the representative of
			// his set,
			parent[x] = find(parent[x]);

			// so we recursively call Find on its parent
			// and move i's node directly under the
			// representative of this set
		}

		return parent[x];
	}

	// Unites the set that includes x and the set
	// that includes x
	void union(int x, int y)
	{
		// Find representatives of two sets
		int xRoot = find(x), yRoot = find(y);

		// Elements are in the same set, no need
		// to unite anything.
		if (xRoot == yRoot)
			return;

		// If x's rank is less than y's rank
		if (rank[xRoot] < rank[yRoot])

			// Then move x under y so that depth
			// of tree remains less
			parent[xRoot] = yRoot;

		// Else if y's rank is less than x's rank
		else if (rank[yRoot] < rank[xRoot])

			// Then move y under x so that depth of
			// tree remains less
			parent[yRoot] = xRoot;

		else // if ranks are the same
		{
			// Then move y under x (doesn't matter
			// which one goes where)
			parent[yRoot] = xRoot;

			// And increment the result tree's
			// rank by 1
			rank[xRoot] = rank[xRoot] + 1;
		}
	}
}
