package edu.sdsu.cs;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import static edu.sdsu.cs.ServiceProvider.*;

/**
 * Main Driving Program
 *
 * Students: Tatia Sebiskveradze, Zurab Kavtaradze
 */
public class App 
{
    public static void main( String[] args )
    {
        FileHandler handler = new FileHandler();

        if(CheckIfPathIsProvided(args))
            handler.Populate(args);
        else
            handler.Populate(DefaultPath);

        Queue<Statistics> stats = new LinkedList<>();
        while (handler.hasFiles())
        {
            Statistics statistics = new Statistics();
            try
            {
                statistics.ProcessFile(handler.Dequeue());
                statistics.Standardize();
                stats.add(statistics);
            }
            catch (IOException ex)
            {
                // TODO: Log Exception
            }
        }

        // TODO all these data should be saved in corresponding files
        Statistics s = stats.poll();
        System.out.println("Length of the longest line: " + s.getLengthOfLongestLine());
        System.out.println("Average line length: " + s.getAverageLineLength());
        System.out.println("Number of unique space-delineated tokens (case-sensitive): " + s.getSensitiveNumberOfSpaceDelimitedTokens());
        System.out.println("Number of unique space-delineated tokens (case-insensitive): " + s.getInsensitiveNumberOfSpaceDelimitedTokens());
        System.out.println("Number of all space-delineated tokens in file: " + s.getNumberOfAllSpaceDelimitedTokens());
        System.out.println("Most frequently occurring token(s): " + s.getMostFrequentlyOccuringToken());
        System.out.println("Count of most frequently occurring token (case-insensitive): " + s.getInsensitiveCountOfMostFrequentlyOccuringToken());
        System.out.println("10 most frequent tokens with their counts (case-insensitive): " + s.getInsensitiveTenMostFrequentlyUsedTokens());
        System.out.println("10 least frequent tokens with their counts (case-insensitive): " + s.getInsensitiveTenLeastFrequentlyUsedTokens());
    }
}
