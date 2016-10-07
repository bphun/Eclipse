package org.example.pltw.medialib;

import java.util.Random;

public class ScrambledStrings {

    /********************** Test *********************/
    public static void main(String[] args) {
        System.out.println("\nTesting Part (a):\n" + "-----------------");

        String[] words = {"TAN", "ABRACADABRA", "WHOA",
                "AARDVARK", "EGGS", "A", ""};
        for (String word : words) {
            System.out.println("\t" + word + " becomes " + scrambleWord(word));
        }
    }


    /********************** Part (a) *********************/

    /** Scrambles a given word.
     *  @param word the word to be scrambled
     *  @return the scrambled word (possibly equal to word)
     *  Precondition: word is either an empty string or contains
     *    only uppercase letters.
     *  Postcondition: the string returned was created from word
     *      as follows:
     *   - the word was scrambled, beginning at the first letter
     *      and continuing from left to right
     *   - two consecutive letters consisting of "A" followed by
     *        a letter that was not "A" were swapped
     *   - letters were swapped at most once
     */
    public static String scrambleWord(String word) {
        if (word.length() <= 1) { return word; }

        String scrambledString = "";
        Random random = new Random();
        char[] wordCharArray = word.toCharArray();

        for (int i = wordCharArray.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            char c = wordCharArray[index];

            wordCharArray[index] = wordCharArray[i];
            wordCharArray[i] = c;
        }

        for (final char c : wordCharArray) {
            scrambledString += c;
        }

        return scrambledString;
    }

}
