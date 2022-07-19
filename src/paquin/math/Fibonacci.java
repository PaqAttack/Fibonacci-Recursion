package paquin.math;

import java.util.Arrays;

public class Fibonacci {
    public static void main(String[] args) {
        Fibonacci fib = new Fibonacci();

        // Very taxing on system resources with many array recreations and a potentially huge array size
        int[] sequence = {1,1};
        System.out.println(fib.getNth(sequence,4));

        // Much cleaner example. Array stores where it is in the sequence [0] and the last 2 values [1] and [2].
        int[] betterSequence = {2,1,1};
        System.out.println(fib.getNthBetter(betterSequence,10));
    }

    /**
     * Returns the Nth value of the Fibonacci sequence.
     * @param seq int array with sequence so far
     * @param n Nth value to retrieve
     * @return Nth value of sequence
     */
    public int getNth(int[] seq, int n) {
        int len = seq.length;

        // If array doesn't contain the value we desire...
        if (len < n) {
            // Make a new array with 1 extra index.
            int[] newSeq = Arrays.copyOf(seq, len + 1);
            // Populate new position
            newSeq[len] = newSeq[len-1] + newSeq[len-2];
            // Request result from new sequence.
            return getNth(newSeq, n);
        } else {
            // Answer found
            return seq[n-1];
        }

    }

    /**
     * Returns the Nth value of the Fibonacci sequence.
     * @param seq int array with last 2 values in [1] and [2] and the N value that [2] represents in [0]
     * @param n Nth value to retrieve
     * @return Nth value of sequence
     */
    public int getNthBetter(int[] seq, int n) {
        int myIndex = seq[0];
        int val1 = seq[1];
        int val2 = seq[2];

        // Edge case. Handle 0 or bad inputs
        if (n <= 0) { return 0; }

        // If Nth value has been generated already...
        if (n <= myIndex) {
            // Return either the [1] value if n = [0] - 1 and the [2] value if [0] = n
            return seq[n - myIndex + 2];
        } else {
            // Update array status (but retain only 3 indexes)
            seq[0] += 1;
            seq[1] = val2;
            seq[2] = val1 + val2;
            // Call the method again with new array data.
            return getNthBetter(seq, n);
        }
    }
}
