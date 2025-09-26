

// recieves obj and returns a string 
public interface Prettifier {

    /**
     * This method takes an object and returns a string that is a pretified version 
     * of the object. The pretification depends on the implementation of this 
     * interface, but the goal is to make a human-readable, pretty string out of 
     * the object.
     *
     * @param object The object to prettify
     * @return A string that is a prettified version of the object
     */
    public abstract String prettifyString(Object object);
}