package edu.sdsu.cs;

import java.io.File;
import java.util.*;

public class FileHandler
{
    public Queue<File> getFiles() {
        return files;
    }

    Queue<File> files = new LinkedList<>();

    protected boolean Enqueue(File file)
    {
        return file == null ? false : files.add(file);
    }

    protected File Dequeue()
    {
        return files.poll();
    }

    protected void Populate(String[] paths)
    {
        for (String path : paths)
            Populate(path);
    }

    protected void Populate(String path)
    {
        File file = new File(path);
        if (file.isDirectory())
        {
            File[] files = file.listFiles();
            for (File f : files)
                if (HasDesiredExtension(f))
                    Enqueue(f);
        }
        else
            Enqueue(file);
    }

    private boolean HasDesiredExtension(File f) {
        return  f.getPath().endsWith(".java") || f.getPath().endsWith(".txt");
    }

    protected boolean hasFiles()
    {
        return !files.isEmpty();
    }
}
