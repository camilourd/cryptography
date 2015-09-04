import java.math.BigInteger;

public class AffineCipher
{
    public AffineCipher()
    {
    }
    
    public String encode(int a, int b, String message)
    {
        
        StringBuilder build = new StringBuilder();
        char[] result = message.toCharArray();
        for(int i=0; i< result.length; ++i)
        {
            char ch = message.charAt(i);
            ch=(char)((a * (ch - 'a') + b) % 26 + 'a');
            build.append(ch);
            //result[i]= alpha.getCharacter(((a * alpha.getIndex(result[i])) + b) % 26);
        }
        return build.toString();
    }
    
    public String decode(int a, int b, String message)
    {
        StringBuilder build = new StringBuilder();
        BigInteger inv = BigInteger.valueOf(a).modInverse(BigInteger.valueOf(26));
        for(int i=0;i<message.length();i++)
        {
            char ch = message.charAt(i);
            int deco = inv.intValue() *(ch -'a' - b + 26);
            ch = (char)(deco % 26 + 'a');
            build.append(ch);
        }
        return build.toString();
    }
}