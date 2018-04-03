package com.akm.coinchange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Problem Statment: Sort the String based on length and Rearrange it also .
 * @author ashishkumarmishra
 *
 */
public class SentenceOrderingProblem 
{
	
	public static void main(String[] args) 
	{
		
		List<String>inptString=new ArrayList<String>();
		inptString.add("here     am     I      ");
		inptString.add("Bad are you");
		inptString.add("Hero am I");
		printInSortedOrder(inptString);
		
		
	}
	
	public static void printInSortedOrder(List<String> lines)
	{
		if(lines==null || lines.isEmpty())
		{
			return ;
		}
		List<String> reverseInput= lines.stream()
										.map(p->getOrderedSentence(p))
										.collect(Collectors.toList());
		Collections.sort(reverseInput,Comparator.comparingInt(String::length));
		reverseInput.stream().forEach(p->{System.out.println(p);});
	}
	
	private static String getOrderedSentence(String inputLine)
	{
		int length=inputLine.length();
		StringBuilder newLine=new StringBuilder();
		StringBuilder word=null;
		StringBuilder space=null;
		for(int index=0;index<length;index++)
		{
			char ch=inputLine.charAt(index) ;
			if(' '== ch)
			{
				if(space==null)
				{
					space= new StringBuilder();
				}
				space.append(ch);
				if(word!=null)
				{
					newLine=new StringBuilder().append(word).append(newLine);
					word=null;
				}
				
			}else
			{
				if(word==null)
				{
					word= new StringBuilder();
				}
				word.append(ch);
				if(space!=null)
				{
					newLine=new StringBuilder().append(space).append(newLine);
					space=null;
				}
			}	
		}
		if(space!=null)
		{
			newLine=new StringBuilder().append(space).append(newLine);
		}
		if(word!=null)
		{
			newLine=new StringBuilder().append(word).append(newLine);
		}
		return newLine.toString();
	}

}
