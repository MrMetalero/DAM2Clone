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
        Collections.shuffle(availableNumbers); 
        iterator = availableNumbers.iterator(); 
    }

   
    public Integer getNextID() {
        if (iterator.hasNext()) {
            return iterator.next(); 
        }
        return null; 
    }

}

