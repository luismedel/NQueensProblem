package org.luismedel.nqueensproblem;

import picocli.CommandLine;

public class Program
{
    public static void main (String[] args)
    {
        CommandLine cmd = new CommandLine (new CliCommand ());
        cmd.execute (args);
    }
}
