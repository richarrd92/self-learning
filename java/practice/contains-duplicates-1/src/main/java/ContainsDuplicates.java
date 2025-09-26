import java.util.*;

public class ContainsDuplicates {
    /**
     * Determine if an array of items contains any duplicate values. You should use a Set for this: remember that
     * Sets can not have duplicate values, but you may check if a value is already contained using the .contains method.
     * @param nums an array of ints.
     * @return true if nums contains any duplicate values, false if it does not.
     */
    public boolean containsDuplicate(int[] nums){
        Set<Integer> set = new HashSet<>();
        for(int num : nums){
            // if cant be added then its duplicate
            if (!(set.add(num))) return true; // return on first encounter of duplicate
            set.add(num); // add to set
        }
        return false;
    }
}
