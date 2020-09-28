package edu.sdsu.cs;

import java.io.*;
import java.util.List;

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

        FileWriter writer = new FileWriter(path, false);

        String data = String.format("%20s:%d" , "Length of longest line in file" , statistics.getLengthOfLongestLine())
                    .concat(String.format("%20s:%.2f" , "\nAverage line length" , statistics.getAverageLineLength()))
                    .concat(String.format("%20s:%d" , "\nNumber of unique space-delineated tokens (case-sensitive)" , statistics.getSensitiveNumberOfSpaceDelimitedTokens()))
                    .concat(String.format("%20s:%d" , "\nNumber of unique space-delineated tokens (case-insensitive)" , statistics.getInsensitiveNumberOfSpaceDelimitedTokens()))
                    .concat(String.format("%20s:%d" , "\nNumber of all space-delineated tokens in file" , statistics.getNumberOfAllSpaceDelimitedTokens()))
                    .concat(String.format("%20s:%s" , "\nMost frequently occurring token(s)" , statistics.getMostFrequentlyOccuringToken()))
                    .concat(String.format("%20s:%d" , "\nCount of most frequently occurring token (case-insensitive)" , statistics.getInsensitiveCountOfMostFrequentlyOccuringToken()))
                    .concat(String.format("%20s:%s" , "\n10 most frequent tokens with their counts (case-insensitive)" , GetFormatedString(statistics.getInsensitiveTenMostFrequentlyUsedTokens())))
                    .concat(String.format("%20s:%s" , "\n10 least frequent tokens with their counts (case-sensitive)" , GetFormatedString(statistics.getInsensitiveTenLeastFrequentlyUsedTokens())));

        System.out.println(data);
        writer.write(data);

        writer.close();
    }

    private static String GetFormatedString(List<Token> list)
    {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (Token tkn : list)
            sb.append("\n  "+ count++ + ")." + "Token:" + tkn.Token + ", Count " + tkn.Count);
        return sb.toString();
    }
}
