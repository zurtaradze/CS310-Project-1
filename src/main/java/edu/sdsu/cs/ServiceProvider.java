package edu.sdsu.cs;

class ServiceProvider
{
    public static final String DefaultPath = ".";

    public static boolean CheckIfPathIsProvided(String[] data)
    {
        return  data.length > 0;
    }
}
