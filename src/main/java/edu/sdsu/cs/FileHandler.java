package edu.sdsu.cs;

import java.io.File;
import java.util.*;

public class FileHandler
{
    Queue<File> files = new LinkedList<>();

    public boolean Enqueue(File file)
    {
        return file == null ? false : files.add(file);
    }

    public File Dequeue()
    {
        return files.poll();
    }

    public void Populate(String[] paths)
    {
        for (String path : paths)
            Populate(path);
    }

    public void Populate(String path)
    {
        File file = new File(path);
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (File f : files)
                Enqueue(f);
        }
        else
            Enqueue(file);
    }
}
