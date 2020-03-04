package org.luismedel.nqueensproblem;

/**
 * Utility math functions used in the problem.
 */
public class MathUtils
{
    /**
     * Returns the Great Common Divisor between two numbers {@code a} and {@code b} using the Euclidean algorithm.
     * It returns cached results for numbers below GCD_CACHE_SIZE.
     *
     * @param a
     * @param b
     */
    public static int GCD (int a, int b)
    {
        if (a < GCD_CACHE_SIZE && b < GCD_CACHE_SIZE)
            return gcdCache[a * GCD_CACHE_SIZE + b];

        return calcGCD (a, b);
    }

    /**
     * Returns the Great Common Divisor between two numbers {@code a} and {@code b} using the Euclidean algorithm.
     * This is the internal function used in {@code GCD}.
     *
     * @param a
     * @param b
     */
    private static int calcGCD (int a, int b)
    {
        return (b == 0) ? a : calcGCD (b, a % b);
    }

    /**
     * Size of the table used to cache GCD results
     */
    private static final int GCD_CACHE_SIZE = 255;

    /**
     * GCD cache. We can lookup the GCD between two positive numbers, a and b, up to GCD_CACHE_SIZE-1
     * using the index [a*GCD_CACHE_SIZE + b]
     */
    static final int[] gcdCache = new int[GCD_CACHE_SIZE * GCD_CACHE_SIZE];

    // Static initializer for gcdCache
    static
    {
        for (int i = 0; i < GCD_CACHE_SIZE; i++)
            for (int j = 0; j < GCD_CACHE_SIZE; j++)
                gcdCache[i * GCD_CACHE_SIZE + j] = calcGCD (i, j);
    }
}