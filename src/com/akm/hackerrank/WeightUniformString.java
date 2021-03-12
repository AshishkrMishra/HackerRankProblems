package com.akm.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class WeightUniformString {

    public static final String YES="Yes";
    public static final String NO="No";
	public static void main(String[] args) {
		
		
		int queries[] = {1,
				3,
				12,
				5,
				9,
				10} ;
		weightedUniformStrings("abccddde",queries);


	}
    
 // Complete the weightedUniformStrings function below.
    static String[] weightedUniformStrings(String s, int[] queries) {
        int currentIndex=0;
        int size=s.length();
        int outSize=queries.length;
        char EMPTY='\0';
        char prevChar='\0';
        int prevValue=0;
        List<Integer> value= new ArrayList<Integer>();
        String[] result= new String[outSize]; 
        while(currentIndex<size)
        {
            char currentChar=s.charAt(currentIndex);
            int currentValue=valueOf(currentChar);
            if(EMPTY!=currentChar )
            {
                if(currentChar==prevChar)
                {
                    currentValue=currentValue+prevValue;
                    prevValue=currentValue;
                }else
                {
                   prevChar=currentChar;
                   prevValue=currentValue; 
                }
            }else
            {
                prevChar=currentChar;
                prevValue=currentValue;
            }
            value.add(currentValue);
            currentIndex++;
        }
        
        for(int index=0; index<outSize;index++)
        {
            if(value.contains(queries[index]))
            {
                result[index]= YES;  
            }else
            {
                result[index]= NO;
            }
        }
        System.out.println("Input String: "+ s);
        System.out.print("Query String: ");
        for(int val:queries)
		{
			System.out.print(" "+ val+" ");
		}
		System.out.println();
        System.out.println("Value Calcaulated : "+ value);
		System.out.print("Result Generated : ");
		for(String val:result)
		{
			System.out.print(" "+ val+" ");
		}
		System.out.println();
        return result;

    }

	
	
	  public static int  valueOf(char inChar)
	    {
	        int base='a';
	        int inVal=inChar;
	        int value=inVal-base+1;
	        return value;
	    }


}
