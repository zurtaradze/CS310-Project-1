package edu.sdsu.cs;

//Author: Tatia Sebiskveradze
public class Token implements Comparable
{
    protected String Token;
    protected Integer Count;

    protected Token(String token, int count)
    {
        Token = token;
        Count = count;
    }

    protected Token(String token)
    {
        Token = token;
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
        String str = o.toString();
        return this.Token.equals(str);
    }

    @Override
    public String toString()
    {
        return this.Token;
    }
}
