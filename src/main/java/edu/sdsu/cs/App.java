package edu.sdsu.cs;

import java.io.File;
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

        while (handler.hasFiles())
            System.out.println(handler.Dequeue().getPath());
    }
}
