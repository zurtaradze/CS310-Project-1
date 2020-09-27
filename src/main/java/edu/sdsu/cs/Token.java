package edu.sdsu.cs;

public class Token implements Comparable
{
    public String Token;
    public Integer Count;

    public Token(String token, int count)
    {
        Token = token;
        Count = count;
    }

    @Override
    public int compareTo(Object o)
    {
        String str = (String)o;
        return Token.compareTo(str);
    }

    @Override
    public boolean equals(Object o)
    {
        String str = (String)o;
        return Token.equals(str);
    }

    @Override
    public String toString()
    {
        return Token;
    }
}
