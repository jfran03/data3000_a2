package data3000_a2;

public class BinarySearch {

    // Main search method that gets called from the driver program
    public static <T extends Comparable<T>> int search(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        // Start the recursive search from the first index to the last index
        return runBinarySearch(array, 0, array.length - 1, key);
    }

    // The recursive method that splits the array in half
    private static <T extends Comparable<T>> int runBinarySearch(T[] array, int low, int high, T key) {
        // Base case: if low passes high, the item isn't in the array
        if (low > high) {
            return -1;
        }

        // Calculate the middle point
        int mid = low + (high - low) / 2;
        int result = array[mid].compareTo(key);

        if (result == 0) {
            // Found a match! But we need to make sure it's the FIRST occurrence.
            // Check the left side of the array to see if the same item exists earlier.
            int leftCheck = runBinarySearch(array, low, mid - 1, key);
            
            // If the left side found a match, return that one because it's earlier.
            // Otherwise, mid is our first occurrence.
            if (leftCheck != -1) {
                return leftCheck;
            } else {
                return mid;
            }
            
        } else if (result > 0) {
            // The item at mid is bigger than our key, so look in the left half
            return runBinarySearch(array, low, mid - 1, key);
        } else {
            // The item at mid is smaller than our key, so look in the right half
            return runBinarySearch(array, mid + 1, high, key);
        }
    }
}