package PS8;
import java.util.*;

public class GroceryDelivery {

    public static void main(String args[]){

        Scanner in = new Scanner(System.in);

        int numItems = in.nextInt();
        int capacity = in.nextInt();

        int box1 = capacity;
        int box2 = capacity;

        int[] itemVolumes = new int[numItems];

        for (int i = 0; i < numItems; i++){
            itemVolumes[i] = in.nextInt();
        }

        in.close();

        String[] itemAssignments1 = new String[numItems];
        String[] itemAssignments2 = new String[numItems];
        String[] itemAssignments3 = new String[numItems];

        int one = possibilityOne(itemAssignments1, numItems, box1, box2, itemVolumes);
        int two = possibilityTwo(itemAssignments2, numItems, box1, box2, itemVolumes);
        int three = possibilityThree(itemAssignments3, numItems, box1, box2, itemVolumes);

        int max = one;
        if (two > max){
            max = two;
        }
        if (three > max){
            max = three;
        }

        System.out.println(max);

        if (max == one){
            for (int j = 0; j < one; j++){
                System.out.println(itemAssignments1[j]);
            }
            return;
        }
        else if (max == two){
            for (int j = 0; j < two; j++){
                System.out.println(itemAssignments2[j]);
            }
            return;
        }
        if (max == three){
            for (int j = 0; j < three; j++){
                System.out.println(itemAssignments3[j]);
            }
            return;
        }


    }

    public static int possibilityOne(String[] itemAssignments, int numItems, int box1, int box2, int[] itemVolumes){

        String first = "1st";
        String second = "2nd";

        //For each item...
        int i = 0;
        int itemVolume;
        for (; i < numItems; i++){
            //look at its volume
            itemVolume = itemVolumes[i];

            if (box1 >= box2){
                if (box1 - itemVolume < 0){
                    if (box2 - itemVolume < 0){
                        break;
                    }
                    else{
                        box2 = box2 - itemVolume;
                        itemAssignments[i] = second;
                        continue;
                    }
                }
                else if (box1 - itemVolume >= 0){
                    box1 = box1 - itemVolume;
                    itemAssignments[i] = first;
                    continue;
                }
            }

            else{

                if (box2 - itemVolume < 0){
                    if (box1 - itemVolume < 0){
                        break;
                    }
                    else{
                        box1 = box1 - itemVolume;
                        itemAssignments[i] = first;
                        continue;
                    }
                }
                else if (box2 - itemVolume >= 0){
                    box2 = box2 - itemVolume;
                    itemAssignments[i] = second;
                    continue;
                }
            }
        }

        return i;

    }


    public static int possibilityTwo(String[] itemAssignments, int numItems, int box1, int box2, int[] itemVolumes){


        String first = "1st";
        String second = "2nd";

        int currBox = 1;

        //For each item...
        int i = 0;
        int itemVolume;

        for (; i < numItems; i++){

            itemVolume = itemVolumes[i];

            if (currBox == 1){

                if (box1 - itemVolume >= 0){
                    box1 = box1 - itemVolume;
                    itemAssignments[i] = first;
                }
                else if (box2 - itemVolume >= 0){
                    box2 = box2 - itemVolume;
                    itemAssignments[i] = second;
                    currBox = 2;
                }
                else{
                    break;
                }

            }

            else{

                if (box2 - itemVolume >= 0){
                    box2 = box2 - itemVolume;
                    itemAssignments[i] = second;
                }
                else if (box1 - itemVolume >= 0){
                    box1 = box1 - itemVolume;
                    itemAssignments[i] = first;
                    currBox = 1;
                }
                else{
                    break;
                }

            }

        }

        return i;


    }

    public static int possibilityThree(String[] itemAssignments, int numItems, int box1, int box2, int[] itemVolumes){
        String first = "1st";
        String second = "2nd";

        //For each item...
        int i = 0;
        int itemVolume;

        for (; i < numItems; i++){

            itemVolume = itemVolumes[i];

            if (box1 - itemVolume >= 0){
                box1 = box1 - itemVolume;
                itemAssignments[i] = first;
            }
            else if (box2 - itemVolume >= 0){
                box2 = box2 - itemVolume;
                itemAssignments[i] = second;
            }
            else{
                break;
            }

        }

        return i;
    }
}
