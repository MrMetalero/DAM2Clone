package com.example;

import java.util.*;

public class RandomNumberGenerator {

    private List<Integer> availableNumbers;
    private Iterator<Integer> iterator;

    // Initialize with a range of numbers and shuffle them
    public RandomNumberGenerator(int lowerBound, int upperBound) {
        availableNumbers = new ArrayList<>();
        for (int i = lowerBound; i <= upperBound; i++) {
            availableNumbers.add(i);
        }
        Collections.shuffle(availableNumbers); // Shuffle the list to randomize order
        iterator = availableNumbers.iterator(); // Create an iterator to go through the numbers
    }

    // Get the next unique number
    public Integer getNextID() {
        if (iterator.hasNext()) {
            return iterator.next(); // Return the next random number
        }
        return null; // If no more numbers are available
    }

}

