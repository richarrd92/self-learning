public class GetMax {
    /**
     * This method should return the max value of arr (the largest int.)
     * This problem will require you to use a for loop. No other guidance will be provided.
     *
     * @param arr an array of ints to be manipulated.
     * @return the largest value in arr.
     */
    public int max(int[] arr){
        int largest = arr[0];
        for(int i = 0; i < arr.length; i++){
            if (largest < arr[i]){
                // update to new largest
                largest = arr[i];
            }
        }

        return largest;
    }
}
