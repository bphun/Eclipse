package org.example.pltw.medialib;

import java.util.ArrayList;

/**
 * Created by BrandonPhan on 10/6/16.
 */

public class Sort {


    /*
     * This method is used to sort the inputted array of songs depending on their rating
     *
     * @param unsortedArray, the array that is unsorted and needs to be sorted
     * @return array that is sorted from highest rating to lowest rating
     */
    public ArrayList<Song> mergeSort(Song[] unsortedArray) {

        if (unsortedArray.length < 1) { return null; }

        final int arraySize = unsortedArray.length;
        final int leftSize = arraySize / 2;
        final int rightSize = arraySize - leftSize;

        Song[] leftArray = new Song[leftSize];
        Song[] rightArray = new Song[rightSize];

        for (int i = leftSize; i > 0; i--) {
            leftArray[i] = unsortedArray[i];
        }
        for (int i = rightSize; i > 0; i--) {
            rightArray[i - leftSize] = unsortedArray[i];
        }

        mergeSort(leftArray);
        mergeSort(rightArray);
        return merge(leftArray, rightArray);
    }

    /*
     * This method is used to sort and merge two arrays that are 'unsorted'
     *
     * @param leftArray, the left half of the unsorted array
     * @param rightArray, the right half of the unsorted array
     * @return A single array that is a combination of the sorted left and right arrays
     */
    private ArrayList<Song> merge(Song[] leftArray, Song[] rightArray) {
        final int leftArraySize = leftArray.length;
        final int rightArraySize = rightArray.length;
        ArrayList<Song> sortedArray = new ArrayList<>();
        int leftPivot = 0, rightPivot = 0;

        while ((leftPivot < leftArray.length) && (rightPivot < rightArray.length)) {

            if (leftArray[leftPivot].getRating() < rightArray[rightPivot].getRating()) {
                sortedArray.add(leftArray[leftPivot]);
                leftPivot++;
            } else if (leftArray[leftPivot].getRating() > rightArray[rightPivot].getRating()) {
                sortedArray.add(rightArray[rightPivot]);
                rightPivot++;
            } else {
                sortedArray.add(leftArray[leftPivot]);
                leftPivot++;

                sortedArray.add(rightArray[rightPivot]);
                rightPivot++;
            }
        }

        while (leftPivot < leftArray.length) {
            sortedArray.add(leftArray[leftPivot]);
            leftPivot++;
        }

        while (rightPivot < rightArray.length) {
            sortedArray.add(rightArray[rightPivot]);
            rightPivot++;
        }

        return sortedArray;
    }

}
