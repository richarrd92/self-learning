package com.cooksys.ftd.assignments.control;

import com.cooksys.ftd.assignments.control.util.MissingImplementationException;

/**
 * The Fibonacci sequence is simply and recursively defined: the first two elements are `1`, and
 * every other element is equal to the sum of its two preceding elements. For example:
 * <p>
 * [1, 1] =>
 * [1, 1, 1 + 1]  => [1, 1, 2] =>
 * [1, 1, 2, 1 + 2] => [1, 1, 2, 3] =>
 * [1, 1, 2, 3, 2 + 3] => [1, 1, 2, 3, 5] =>
 * ...etc
 */
public class Fibonacci {

    /**
     * Calculates the value in the Fibonacci sequence at a given index. For example,
     * `atIndex(0)` and `atIndex(1)` should return `1`, because the first two elements of the
     * sequence are both `1`.
     *
     * @param i the index of the element to calculate
     * @return the calculated element
     * @throws IllegalArgumentException if the given index is less than zero
     */
    public static int atIndex(int i) throws IllegalArgumentException {
        // index cant be less than zero
        if (i < 0){
            throw new IllegalArgumentException();
        }

        // index is 0 or 1
        if (i == 0 || i == 1){
            return 1;
        }

        // assume fib is 1
        int prev_prev = 1; // value at index 0
        int prev = 1; // value at index 1
        int fib = 0;

        // start at index 2: fib index(0) and index(1) is always 1
        for(int start = 2; start <= i; start++){
            fib = prev_prev + prev;
            prev_prev = prev;
            prev = fib;
        }

        return fib; // fib at given index
    }

    /**
     * Calculates a slice of the fibonacci sequence, starting from a given start index (inclusive) and
     * ending at a given end index (exclusive).
     *
     * @param start the starting index of the slice (inclusive)
     * @param end   the ending index of the slice(exclusive)
     * @return the calculated slice as an array of int elements
     * @throws IllegalArgumentException if either the given start or end is negative, or if the
     *                                  given end is less than the given start
     */
    public static int[] slice(int start, int end) throws IllegalArgumentException {
        // validate arguments
        if (start < 0 || end < 0 || end < start){
            throw new IllegalArgumentException();
        }

        int[] fibSlice = new int[end - start];
        for (int i = start; i < end; i++) {
            fibSlice[i - start] = atIndex(i); // offset
        }

        // array of fib number in range(start, end)
        return fibSlice;
    }

    /**
     * Calculates the beginning of the fibonacci sequence, up to a given count.
     *
     * @param count the number of elements to calculate
     * @return the beginning of the fibonacci sequence, up to the given count, as an array of int elements
     * @throws IllegalArgumentException if the given count is negative
     */
    public static int[] fibonacci(int count) throws IllegalArgumentException {
        // count is negative
        if (count < 0){
            throw new IllegalArgumentException();
        }

        // slice returns an array of integers
        return slice(0, count);
    }
}
