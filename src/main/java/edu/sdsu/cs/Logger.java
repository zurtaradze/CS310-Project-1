package edu.sdsu.cs;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger
{
    protected static void Log(Exception ex)
    {
        String path = ServiceProvider.DefaultPath + "//Logs//" + new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime()) + ServiceProvider.TextExtension;
        try
        {
            FileWriter writer = new FileWriter(path);
            writer.write(ex.getMessage());
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
