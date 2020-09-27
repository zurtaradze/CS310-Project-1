package edu.sdsu.cs;

import java.io.*;

class ServiceProvider
{
    protected static final String DefaultPath = ".";

    protected static boolean CheckIfPathIsProvided(String[] data)
    {
        return  data.length > 0;
    }

    protected static boolean HasDesiredExtension(File file)
    {
        String path = file.getPath();
        int index = path.lastIndexOf('.');
        String extension = path.substring(index);

        return extension.equals("txt") || extension.equals("java");
    }
}
