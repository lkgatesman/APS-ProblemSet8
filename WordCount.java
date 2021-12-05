package PS8;
import java.util.*;

public class WordCount{

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        in.close();

        int words = 0;

        //For each character in the input string...
        for(int i = 0; i < input.length(); i++){
            //If its an alphebtical character, then we move  onto the next character

            while(Character.isLetter(input.charAt(i))){
                //System.out.println(input.charAt(i) + "   words: " + words);
                //Keep going through the input string until we find a non alphabetical char
                if( i< input.length() - 1){
                    i++;
                }
                else{
                    break;
                }
            }

            //If it's NOT an alphbetical character, then we increment the word count by 1
            // (because we have just finished reading a word)
            if (i != 0){
                words++;
            } 
            // and then we keep reading characters until we find another alphabetical character
            while(!Character.isLetter(input.charAt(i))){
                //System.out.println(input.charAt(i) + "   words: " + words);

                //Keep going through the input string until we find another alphabetical char
                if(i < input.length() - 1){
                    i++;
                }
                else{
                    break;
                }
            }

        }

        //Print out the total number of words
        System.out.println(words);

    }

}


