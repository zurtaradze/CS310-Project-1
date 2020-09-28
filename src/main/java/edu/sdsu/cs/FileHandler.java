package edu.sdsu.cs;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

import static edu.sdsu.cs.ServiceProvider.*;

// Author: Zurab Kavtaradze
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

    protected void Populate(String[] paths) throws IOException {
        for (String path : paths)
            Populate(path);
    }

    protected void Populate(String path) throws IOException {
        List<Path> fil = Files.find(Paths.get(path),
                                    Integer.MAX_VALUE,
                                    (filePath, fileAttr) -> fileAttr.isRegularFile()).collect(Collectors.toList());

        for (Path s : fil)
            if (HasDesiredExtension(s.toFile()))
                Enqueue(s.toFile());
    }
    protected boolean hasFiles()
    {
        return !files.isEmpty();
    }
}
