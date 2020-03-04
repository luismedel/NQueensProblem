package org.luismedel.nqueensproblem;

import org.luismedel.nqueensproblem.boards.Board;
import org.luismedel.nqueensproblem.boards.NotThreeAligned;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class NQueensTests
{
    /**
     * Tests the number of solutions of the "not three in a straight line" problem for size=8.
     *
     * It uses the known fact that for size=8, only one of the fundamental solutions has the property that no three
     * queens are in a straight line.
     *
     * But we calculate all variants, so for size=8 there will be 8 solutions in total, counting rotated and mirrored
     * variants.
     */
    @Test
    public void testNotThreeAlignedProblem ()
    {
        List<Board> solutions = Solver.solve (new NotThreeAligned (8));
        assertEquals (8, solutions.size ());
    }


    /**
     * Tests the number of solutions in the classic problem up to size 13.
     * It checks against the first 13 elements in the sequence A000170 (https://oeis.org/A000170)
     */
       @Test
    public void testClassicProblem ()
    {
        final int[] knownSolutions = { 1, 0, 0, 2, 10, 4, 40, 92, 352, 724, 2680, 14200, 73712 };

        for (int i = 0; i < knownSolutions.length; i++)
        {
            List<Board> solverSolutions = Solver.solve (new Board (i + 1));
            assertEquals (knownSolutions[i], solverSolutions.size ());
        }
    }
}
