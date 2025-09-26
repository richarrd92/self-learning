
public class ReturnWordCount {
    /**
     * @param in A String representing a sentence, with words delineated by spaces.
     * @return return the amount of words in a string.
     */
    public int count(String in){
        int i = 0;
        int count = 0;
        int len = in.length();
        while(i < len){
            // skip spaces
            while(i < len && in.charAt(i) == ' ') i++;

            if (i < len) count++;

            // skip characters
            while(i < len && in.charAt(i) != ' ') i++;
        }
        return count;
    }
}
