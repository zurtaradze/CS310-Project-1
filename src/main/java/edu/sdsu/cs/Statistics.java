package edu.sdsu.cs;

import java.util.ArrayList;
import java.util.List;

public class Statistics
{
    public void ProcessToken(String token)
    {
        NumberOfAllSpaceDelimitedTokens++;
        if (!InsensitiveTokens.contains(token.toLowerCase()))
        {
            InsensitiveNumberOfSpaceDelimitedTokens++;
            Token tkn = new Token(token.toLowerCase(), 1);
            InsensitiveTokens.add(tkn);
        }
        else
        {
            int index = InsensitiveTokens.indexOf(token.toLowerCase());
            Token model = InsensitiveTokens.get(index);
            model.Count++;
        }
        if (!SensitiveTokens.contains(token))
        {
            SensitiveNumberOfSpaceDelimitedTokens++;
            Token tkn = new Token(token, 1);
            SensitiveTokens.add(tkn);
        }
        else
        {
            int index = SensitiveTokens.indexOf(token);
            Token model = SensitiveTokens.get(index);
            model.Count++;
        }
    }

    public void ProcessLine(String line)
    {
        totalLineLength += line.length();
        lineCount++;

        if (line.length() > LengthOfLongestLine)
            LengthOfLongestLine = line.length();

        String[] tokens = line.split(" ");

        for (String token : tokens)
            ProcessToken(token);
    }

    private int LengthOfLongestLine;
    private double AverageLineLength;
    private int SensitiveNumberOfSpaceDelimitedTokens;
    private int InsensitiveNumberOfSpaceDelimitedTokens;
    private int NumberOfAllSpaceDelimitedTokens;
    private List<String> MostFrequentlyOccuringToken;
    private int InsensitiveCountOfMostFrequentlyOccuringToken;
    private List<Token> InsensitiveTenMostFrequentlyUsedTokens;
    private List<Token> SensitiveTenLeastFrequentlyUsedTokens;

    private int totalLineLength;
    private int lineCount;
    List<Token> InsensitiveTokens = new ArrayList<>();
    List<Token>  SensitiveTokens = new ArrayList<>();

    public void Standardize()
    {
    }
}
