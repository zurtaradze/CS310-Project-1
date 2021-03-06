package edu.sdsu.cs;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Author: Zurab Kavtaradze
class Statistics
{
    protected void ProcessFile(File file) throws IOException {
        this.file = file;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        do {
            line = reader.readLine();
            if (line == null) break;
            ProcessLine(line);
        } while (line != null);
        reader.close();
    }
    private void ProcessToken(String token)
    {
        if (token.equals(""))
            return;
        NumberOfAllSpaceDelimitedTokens++;
        if (!InsensitiveTokens.contains(new Token(token.toLowerCase())))
        {
            InsensitiveNumberOfSpaceDelimitedTokens++;
            Token tkn = new Token(token.toLowerCase(), 1);
            InsensitiveTokens.add(tkn);
        }
        else
        {
            int index = InsensitiveTokens.indexOf(new Token(token.toLowerCase()));
            Token model = InsensitiveTokens.get(index);
            model.Count++;
        }
        if (!SensitiveTokens.contains(new Token(token)))
        {
            SensitiveNumberOfSpaceDelimitedTokens++;
            Token tkn = new Token(token, 1);
            SensitiveTokens.add(tkn);
        }
        else
        {
            int index = SensitiveTokens.indexOf(new Token(token));
            Token model = SensitiveTokens.get(index);
            model.Count++;
        }
    }

    private void ProcessLine(String line)
    {
        totalLineLength += line.length();
        lineCount++;

        if (line.length() > LengthOfLongestLine)
            LengthOfLongestLine = line.length();

        String[] tokens = line.split("\\s+");

        for (String token : tokens)
            ProcessToken(token);
    }


    // GETTERS
    protected int getLengthOfLongestLine() {
        if (!isStandardized) Standardize();
        return LengthOfLongestLine;
    }
    protected double getAverageLineLength() {
        if (!isStandardized) Standardize();
        return (double)totalLineLength / lineCount;
    }
    protected int getInsensitiveNumberOfSpaceDelimitedTokens() {
        if (!isStandardized) Standardize();
        return InsensitiveNumberOfSpaceDelimitedTokens;
    }
    protected int getSensitiveNumberOfSpaceDelimitedTokens() {
        if (!isStandardized) Standardize();
        return SensitiveNumberOfSpaceDelimitedTokens;
    }
    protected int getNumberOfAllSpaceDelimitedTokens() {
        if (!isStandardized) Standardize();
        return NumberOfAllSpaceDelimitedTokens;
    }
    protected List<Token> getMostFrequentlyOccuringToken() {
        if (!isStandardized) Standardize();
        List<Token> tokens = new ArrayList<>();
        for (int i = SensitiveTokens.size() - 1; i >= 0; i--)
        {
            if (tokens.isEmpty())
                tokens.add(SensitiveTokens.get(i));
            else if (SensitiveTokens.get(i).Count >= tokens.get(0).Count)
                tokens.add(SensitiveTokens.get(i));
            else
                break;
        }
        return tokens;
    }
    protected int getInsensitiveCountOfMostFrequentlyOccuringToken() {
        if (!isStandardized) Standardize();
        return InsensitiveTokens.get(InsensitiveTokens.size() - 1).Count;
    }
    protected List<Token> getInsensitiveTenMostFrequentlyUsedTokens() {
        if (!isStandardized) Standardize();
        if (InsensitiveTokens.size() < 10)
        {
            Collections.reverse(InsensitiveTokens);
            return InsensitiveTokens;
        }
        List<Token> list = new ArrayList<>(10);
        list = InsensitiveTokens.stream().skip(InsensitiveTokens.size() - 10).limit(10).collect(Collectors.toList());
        Collections.reverse(list);
        return list;
    }
    protected List<Token> getInsensitiveTenLeastFrequentlyUsedTokens() {
        if (!isStandardized) Standardize();
        if (InsensitiveTokens.size() < 10)
            return  InsensitiveTokens;
        List<Token> list = new ArrayList<>(10);
        list = InsensitiveTokens.stream().limit(10).collect(Collectors.toList());
        return list;
    }
    protected File getFile() {
        return file;
    }

    private int LengthOfLongestLine;
    private double AverageLineLength;
    private int SensitiveNumberOfSpaceDelimitedTokens;
    private int InsensitiveNumberOfSpaceDelimitedTokens;
    private int NumberOfAllSpaceDelimitedTokens;
    private List<Token> MostFrequentlyOccuringToken;
    private int InsensitiveCountOfMostFrequentlyOccuringToken;
    private List<Token> InsensitiveTenMostFrequentlyUsedTokens;
    private List<Token> InsensitiveTenLeastFrequentlyUsedTokens;
    private File file;

    private int totalLineLength;
    private int lineCount;
    private boolean isStandardized;
    List<Token> InsensitiveTokens = new ArrayList<>();
    List<Token>  SensitiveTokens = new ArrayList<>();

    protected void Standardize()
    {
        InsensitiveTokens.sort(new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o1.Count.compareTo(o2.Count);
            }
        });

        SensitiveTokens.sort(new Comparator<Token>() {
            @Override
            public int compare(Token o1, Token o2) {
                return o1.Count.compareTo(o2.Count);
            }
        });
        isStandardized = true;
    }
}
