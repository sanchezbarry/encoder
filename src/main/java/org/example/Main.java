package org.example;

import java.util.*;

public class Main {
    //Store static string for output
    static String encodedString = "";

    //all possible characters
    static char[] ar = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '(', ')', '*', '+',
            ',', '-', '.', '/' };

    //duplicates the table of characters in another array
    static char[] ori = ar.clone();

    //define offset character
    static char offChar;

    //Store offset no.
    static int newOFF = 0;

    public static void main(String[] args) {
        //Input offset
        Scanner sc = new Scanner(System.in);

        //Input string
        String str = "HELLO WORLD";

        //Tells user to input offset char & read it
        System.out.println("Enter Offset: ");
        char in = sc.next().charAt(0);

        int offset = 0;
        for (int i = 0; i < 44; i++) {
            //Store offset char index in offset variable
            if (in == ar[i]) {
                offset = i;
            }

        }
        //Store offset character
        offChar = ar[offset];
        newOFF = offset;
        //Store offset character
        Main ob = new Main();

        //call encode and print, also prints out offset character
        System.out.println("Encoded String:= " + in  + ob.encode(str));
        //call decode and print
        System.out.println("Decoded String:= "+ob.decode(encodedString)); //call public String encode(plaintext) method

    }

    //method to decode character
    public String decode(String encodedString) {
        //convert to array
        char[] encodedArray = encodedString.toCharArray();

        //store positions of encoded
        int[] position = new int[encodedString.length()];

        int pos = 0;
        String c = "";
        int f = 0;

        //decode string
        for (int i = 0; i < encodedArray.length; i++) {
            for (int k = 0; k < ar.length; k++) {
                if (encodedArray[i] == ar[k]) { //If encoded characters matches with Offset table then add Offset to Decode String
                    f = 1;
                    c = c + ar[k + newOFF];
                    break;
                }
                //handle spaces
                if (encodedArray[i] == ' ') {
                    c = c + " ";
                    break;
                }

            }

            if (f == 0) { //If f=0 means No character present in table so just re-print it
                c = c + encodedArray[i];
            }

            f = 0; //Re-initialize f=0 for further calculations
        }

        return c; //return resultant String
    }

    public String encode(String plaintext) {//Used to decode plaintext
        //convert to array
        char[] res = plaintext.toCharArray();
        //store no. of spaces
        int c = 0;
        //calculate the space in the string
        for (int i = 0; i < res.length; i++) {
            if (res[i] == ' ') {
                c++;
            }
        }
//Get offset table based on Offset value
        for (int j = 0; j < newOFF; j++) {

            char tem = ar[ar.length - 1]; //store last element of given array in tem variable
            //get length of the array
            int l = ar.length;

            int last = ar.length - 1;

            for (int i = l - 2; i >= 0; i--) { //Shift every character from beginning to end by one time
                ar[last] = ar[i]; //store ar[i]th element in last location of array
                last--; //decrement last so all element up to 1st will shift to last
            }
            ar[0] = tem; //Store last element of array at 1st location

        }
        String t = "";
        int[] position = new int[res.length]; //integer array to store index of resultant array
        int pos = 0; //Index of position array
        int g = 0;

        int f = 0; //Flag to check whether element of String is present on Table or not
        for (int k = 0; k < res.length; k++) {
            for (int i = 0; i < ori.length; i++) {
                if (res[k] == ori[i]) { // IF element from String present in table then add its position in array
                    f = 1; //Indicate element of String is present in Table
                    position[pos] = i; //Store that element
                    pos++; //Increment pos index of array
                    break;
                }

            }
            if (f == 0 && res[k] != ' ') { //If element from input String not present in table then type cast that character to integer and store in array
                position[pos] = (int) res[k];
                pos++;
            }

            f = 0;
            if (res[k] == ' ') {//If space is present in input String then add 0 to position array
                position[pos] = 0;
                pos++;
            }
        }
        //store encoded String to be return
        String tr = "";

        for (int i = 0; i < position.length; i++) {//If 0 present in position array it Indicate presence of space so add space to encoded String
            if (position[i] == 0) {
                tr = tr + " ";
            }
            if (position[i] != 0 && position[i] >= 0 && position[i] <= 43) {//To encode element from table
                tr = tr + ar[position[i]];
            }

            if (position[i] >= 97 && position[i] <= 122) { //To encode element of lowerCases
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 58 && position[i] <= 64) { //To encode element of special character
                char single = (char) position[i]; ////Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 91 && position[i] <= 96) {//To encode element of special character
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }

            if (position[i] >= 123 && position[i] <= 126) {//To encode element of special character
                char single = (char) position[i]; //Typecast integer to character from position array
                tr = tr + single;
            }
        }
        //Update static encodedString String for decode
        encodedString = encodedString + tr;
        return tr; //return resultant encoded string
    }

}