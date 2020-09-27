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
        System.out.println(stats.size());
    }
}
