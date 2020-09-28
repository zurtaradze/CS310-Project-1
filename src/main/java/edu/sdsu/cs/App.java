package edu.sdsu.cs;

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
        try
        {
            if(CheckIfPathIsProvided(args))
                handler.Populate(args);
            else
                handler.Populate(DefaultPath);
        }
        catch(Exception ex)
        {
            Logger.Log(ex);
        }

        Queue<Statistics> stats = new LinkedList<>();
        while (handler.hasFiles())
        {
            Statistics statistics = new Statistics();
            try
            {
                statistics.ProcessFile(handler.Dequeue());
                stats.add(statistics);
            }
            catch (IOException ex)
            {
                Logger.Log(ex);
            }
        }

        while (!stats.isEmpty())
        {
            try
            {
                Statistics s = stats.poll();
                WriteToFile(s);
            }
            catch (Exception ex)
            {
                Logger.Log(ex);
            }
        }
    }
}
