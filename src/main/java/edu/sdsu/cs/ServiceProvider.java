package edu.sdsu.cs;

import java.io.*;

class ServiceProvider
{
    protected static final String DefaultPath = ".";
    protected static final String JavaExtension = ".java";
    protected static final String TextExtension = ".txt";
    protected static final String StatisticsExtension = ".stats";

    protected static boolean CheckIfPathIsProvided(String[] data)
    {
        return  data.length > 0;
    }

    protected static boolean HasDesiredExtension(File file)
    {
        return file.getPath().endsWith(TextExtension) || file.getPath().endsWith(JavaExtension);
    }

    protected static void WriteToFile(Statistics statistics) throws IOException {
        String path = statistics.getFile().getPath().endsWith(JavaExtension)
                ? statistics.getFile().getPath().replaceAll(JavaExtension, StatisticsExtension)
                : statistics.getFile().getPath().replaceAll(TextExtension, StatisticsExtension);

        FileWriter writer = new FileWriter(path, true);

    }
}
