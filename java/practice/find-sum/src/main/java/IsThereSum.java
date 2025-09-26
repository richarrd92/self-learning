import java.util.*;
public class IsThereSum {
    /**
     * Check if there exist two numbers in an array add up to some other number.
     * You will likely need a for loop nested inside of another for loop for this problem - each for loop checking
     * sums of two different indexes of arr.
     *
     * @param arr an array of ints.
     * @param target a hypothetical sum of two numbers.
     * @return true if two separate values in the array add up to a target, false otherwise.
     */
    public boolean check(int[] arr, int target){
        // use hashmap to map key to values
        Map<Integer, Integer> mp = new HashMap<>();
        for(int i = 0; i < arr.length; i++){
            int complement = target - arr[i];

            // if map contains complement then there exist two numbers that add up to target
            if (mp.containsValue(complement)){
                return true;
            }

            // add to map
            mp.put(arr[i], i);

        }

        return false;
    }
}
