package org.luismedel.nqueensproblem;

import org.luismedel.nqueensproblem.boards.Board;
import org.luismedel.nqueensproblem.boards.NotThreeAligned;

import java.util.List;
import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command (name = "nqueens", version = "1.0", description = "Solves the N queens problem.")
public class CliCommand
    implements Callable<Integer>
{
    @Parameters (index = "0", description = "Size of the board to solve.")
    private int size;

    @Option (names = {"-p", "--print"}, description = "Prints solutions to screen.")
    private boolean print = false;

    @Option (names = {"-t", "--time"}, description = "Prints the time taken to finish.")
    private boolean time = false;

    @Override
    public Integer call ()
    {
        long start = System.nanoTime ();
        List<Board> boards = Solver.solve (new NotThreeAligned (size));
        long end = System.nanoTime ();

        System.out.printf ("Found %d solutions for a %dx%d board.%n", boards.size (), size, size);

        if (time)
            System.out.printf ("Time elapsed: %.3f seconds.%n", (end - start) / 1000000000.0);

        if (print)
        {
            System.out.println ();

            int count = boards.size ();
            for (int i = 0; i < count; i++)
            {
                printBoard (boards.get (i));
                System.out.println ();
            }
        }

        return 0;
    }

    private static void printBoard (Board board)
    {
        int size = board.getSize ();

        String border = "+" + createString ("-", board.getSize () * 2 + 1) + "+";

        StringBuffer buffer = new StringBuffer ();
        buffer.append (border);
        buffer.append ('\n');

        for (Board.Queen q : board.getQueens ())
        {
            int col = q.getCol ();

            buffer.append ('|');
            buffer.append (createString (" .", q.getCol ()));
            buffer.append (" Q");
            buffer.append (createString (" .", size - q.getCol () - 1));
            buffer.append (" |\n");
        }

        buffer.append (border);

        System.out.println (buffer.toString ());
    }

    /**
     * Creates a string consisting of a {@code pattern} repeated {@code count} times.
     *
     * @param pattern   Pattern to use.
     * @param count     Times to repeat the {@code pattern}.
     *
     * @return          The new string.
     */
    private static String createString (String pattern, int count)
    {
        if (count == 0)
            return "";

        if (count == 1)
            return pattern;

        char[] chars = pattern.toCharArray ();
        int len = chars.length;
        char[] result = new char[len * count];

        for (int i = 0; i < count; i++)
            System.arraycopy (chars, 0, result, i*len, len);

        return new String (result);
    }
}