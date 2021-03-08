package com.akm.hackerrank;

import java.util.ArrayList;
import java.util.List;

public class LongestSubArrayProblem {

	public static void main(String[] args) {
		
		List<Integer>  arr = new ArrayList<Integer> ();
		arr.add(1);
		arr.add(1);
		arr.add(2);
		arr.add(2);
		arr.add(2);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(2);
		arr.add(2);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(1);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		arr.add(3);
		System.out.println(arr);
		int result=findlongestSubArray(arr);
		System.out.println("Result : "+ result);
	}
	
	public static int findlongestSubArray(List<Integer>  arr )
	{
		int size=arr.size();
		int maxSubArraySize=0;
		for(int index=0;index<size;index++)
		{
			int  currentMaxSize=getPossibleSubArraysize(arr,index);
			System.out.println("For Index  : "+index + " size is  : "+currentMaxSize);
			if(currentMaxSize>maxSubArraySize)
			{
				maxSubArraySize=currentMaxSize;
			}
		}
		return maxSubArraySize;
	}

	private static int getPossibleSubArraysize(List<Integer> arr, int index) {
		
		int arraySize=arr.size();
		int currentIndex=index;
		int size=1;
		if(index+1==arraySize)
		{
			return size;
		}
		int firstElement=arr.get(index);
		int secondElement=Integer.MAX_VALUE;
		currentIndex++;
		while (currentIndex<arraySize)
		{
			int currentElement=arr.get(currentIndex);
			if(currentElement==firstElement || currentElement==secondElement)
			{
				size++;
			}else if(currentElement+1==firstElement || currentElement-1== firstElement)
			{
				if(secondElement==Integer.MAX_VALUE)
				{
					secondElement=currentElement;
					size++;
				}else
				{
					break;
				}
			}else
			{
				break;
			}
			
			currentIndex++;
		}
		return size;
	}



}
