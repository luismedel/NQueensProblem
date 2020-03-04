package org.luismedel.nqueensproblem.boards;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a board with the base constraints for the problem.
 */
public class Board
{
    public int getSize () { return size; }
    public List<Queen> getQueens () { return queens; }

    public Board (int size)
    {
        this.size = size;
        queens = new ArrayList<> (size);
    }

    protected Board (Board copy)
    {
        this.size = copy.size;
        queens = new ArrayList<> (copy.queens);
    }

    /**
     * Places a queen at ({@code row},{@code col})
     *
     * @param row
     * @param col
     */
    public void add (int row, int col) { queens.add (new Queen (row, col)); }

    /**
     * Clones the current board.
     *
     * @return A copy of the current board.
     */
    public Board clone () { return new Board (this);}

    public boolean isSafe (int row, int col)
    {
        for (Queen q : queens)
        {
            if (q.col == col || Math.abs (q.row - row) == Math.abs (q.col - col))
                return false;
        }

        return true;
    }

    protected final int size;
    protected final List<Queen> queens;

    /**
     * Represents the position of a queen in the board.
     */
    public static class Queen
    {
        public int getRow () { return row; }
        public int getCol () { return col; }

        public Queen (int row, int col)
        {
            this.row = row;
            this.col = col;
        }

        private final int row;
        private final int col;
    }
}
