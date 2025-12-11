package backtracking_algorithms;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /**
         * Step 1: Define the input array of numbers
         * We will generate all possible permutations of this array
         */
        int[] nums = {1, 2, 3};

        /**
         * Step 2: Initialize an array to track which numbers are already used in the current permutation
         * This is a boolean array, where `true` means the number at that index is already included
         * in the current permutation, and `false` means it is available to use.
         */
        boolean[] seen = new boolean[nums.length];

        /**
         * Step 3: Create a list to store all permutations
         * Each element of this list will itself be a list representing one unique permutation
         */
        List<List<Integer>> result = new ArrayList<>();

        /**
         * Step 4: Create a temporary list to store the currently forming permutation
         * This list is modified during recursion and backtracking
         */
        List<Integer> temp = new ArrayList<>();

        /**
         * Step 5: Start the backtracking process
         * This will recursively build all permutations and store them in `result`
         */
        backtrack(result, temp, nums, seen);

        /**
         * Step 6: Print all permutations
         * Using Java 8 stream API to print each permutation in the result list
         */
        result.stream().forEach(System.out::println);
    }

    /**
     * Recursive backtracking function to generate all possible permutations
     * 
     * @param result The list to store all generated permutations
     * @param temp The temporary list that stores the current permutation being built
     * @param nums The input array of numbers to permute
     * @param seen Boolean array that tracks which numbers are already included in `temp`
     */
    static void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] seen) {

        // Base Case: If the temporary list has the same length as the input array
        // It means we have formed a complete permutation
        if (temp.size() == nums.length) {
            // Add a **copy** of temp to result
            // Important: We create a new ArrayList to avoid referencing the same list repeatedly
            result.add(new ArrayList<>(temp));
            return; // Backtrack
        }

        // Recursive Case: Try to add each number to the current permutation
        for (int i = 0; i < nums.length; i++) {

            // If the number is already used in the current permutation, skip it
            if (seen[i]) continue;

            // Choose the number
            seen[i] = true; // Mark it as used
            temp.add(nums[i]); // Add it to the current permutation

            // Recursively continue building the permutation with the next numbers
            backtrack(result, temp, nums, seen);

            // Backtracking Step:
            // After exploring all permutations starting with temp, undo the last choice
            seen[i] = false; // Mark the number as unused so it can be used in other permutations
            temp.remove(temp.size() - 1); // Remove the last number to try a different option
        }
    }
}
