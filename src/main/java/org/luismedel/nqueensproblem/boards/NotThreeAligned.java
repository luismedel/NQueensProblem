package org.luismedel.nqueensproblem.boards;

import org.luismedel.nqueensproblem.MathUtils;

/**
 * Represents a board that applies the additional constraint that no three queens can be in a straight line.
 */
public class NotThreeAligned
    extends Board
{
    public NotThreeAligned (int size)
    {
        super (size);
        cells = new boolean[size * size];
    }

    protected NotThreeAligned (Board copy)
    {
        super (copy);
        cells = new boolean[getSize () * getSize ()];
    }

    @Override
    public void add (int row, int col)
    {
        super.add (row, col);
        cells[row*size + col] = true;
    }

    @Override
    public Board clone ()
    {
        NotThreeAligned result = new NotThreeAligned (this);
        System.arraycopy (cells, 0, result.cells, 0, result.cells.length);

        return result;
    }

    @Override
    public boolean isSafe (int row, int col)
    {
        if (!super.isSafe (row, col))
            return false;

        for (Queen q : queens)
        {
            int queenRow = q.getRow ();
            int queenCol = q.getCol ();

            // Get the slope components between the current queen and (row,col)
            int dx = queenCol - col;
            int dy = queenRow - row;

            // Find the smallest slope components.
            // We don't want to miss intermediate queens.
            int gcd = MathUtils.GCD (Math.abs (dx), Math.abs (dy));
            dx /= gcd;
            dy /= gcd;

            /*
             * For now, we have for sure the current queen and the candidate at (row,col). So, starting from the
             * current queen and advancing along the line defined by (dy,dx), we check if there's one more queen
             * within the same line.
             *
             * We check two cells a time (one forward and other backwards the line) until we exit the board.
             *
             * *** Rationale ***
             * Let's be practical. Using backtracking (as we're using) this problem is manageable only for small sizes.
             * So, checking so-small boards along a line step-by-step, while being O(n), is a cheap operation.
             *
             * Alternatively, we could have chosen to search for another queen which forms a line with (row,col) with
             * the same slope dy/dx. It can be done easily using a set and iterating again against the queens.
             * But it would imply creating (or resetting) a new object on every iteration, mangling the heap and
             * adding pressure to the GC in the short term. Also, inserts and searches of float values on a set, while
             * pretty fast nowadays, involves several calculations and boxing/unboxing of values.
             *
             * So I think this simple algorithm is better in this case.
             */

            int fwdRow = queenRow;
            int fwdCol = queenCol;
            boolean fwdTestActive;

            int backRow = queenRow;
            int backCol = queenCol;
            boolean backTestActive;

            do
            {
                fwdRow  += dy;
                fwdCol  += dx;
                fwdTestActive = fwdRow > -1 && fwdCol > -1
                             && fwdRow < size && fwdCol < size;

                backRow -= dy;
                backCol -= dx;
                backTestActive = backRow > -1 && backCol > -1
                              && backRow < size && backCol < size;

                if ((fwdTestActive  && cells[fwdRow*size  + fwdCol])
                 || (backTestActive && cells[backRow*size + backCol]))
                    return false;
            }
            while (fwdTestActive || backTestActive);
        }

        return true;
    }

    /**
     * One-dimensional board state representation (true=queen, false=free)
     * Every cell is at index [row*size + col]
     */
    private final boolean[] cells;
}