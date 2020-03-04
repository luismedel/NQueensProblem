package org.luismedel.nqueensproblem;

import org.luismedel.nqueensproblem.boards.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Solver for the N Queens problem.
 */
class Solver
{
    /**
     * Solves the problem given a starting board state.
     *
     * @param startingBoard     The starting board state.
     *
     * @return                  A list with all the existing solutions for the given board.
     */
    public static List<Board> solve (Board startingBoard)
    {
        List<Board> solutions = new ArrayList<> ();
        solve (startingBoard, 0, solutions);
        return solutions;
    }

    /**
     * Solves the problem starting at {@code row} using backtracking.
     * This is the actual algorithm.
     *
     * @param board         Current board state.
     * @param row           Row to start solving.
     * @param solutions     Solution list to append the current solution, if any.
     *
     * @return              true if the current state is a valid solution, false otherwise.
     */
    private static boolean solve (Board board, int row, List<Board> solutions)
    {
        if (row == board.getSize ())
            return true;

        // Check every column in this row for a safe place
        for (int i = 0 ; i < board.getSize (); i++)
        {
            if (board.isSafe (row, i))
            {
                Board child = board.clone ();
                child.add (row, i);

                if (solve (child, row + 1, solutions))
                    solutions.add (child);
            }
        }

        return false;
    }
}
