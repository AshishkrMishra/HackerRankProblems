package com.akm.soraco;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class FileDuplicationProblem 
{

	private int [] indexs=null;
	
	public static int byteBufferSize=1024;
	/**
	 * dedup - It will minimize the Size of File by removing Duplicate bytes
	 * @param input_file_path
	 * @param deduped_file_path
	 * @throws IOException
	 */
	public void dedup(String input_file_path , String deduped_file_path) throws IOException
	{
		File input_file =new File(input_file_path);
		BufferedInputStream bufferedInputStream=null;
		File deduped_file= new File(deduped_file_path);
		FileOutputStream fileOutputStream=null;
		try
		{
			fileOutputStream= new FileOutputStream(deduped_file,true);
			if(!input_file.exists())
			{
				return ;
			}
			int seq=0;
			try {
					bufferedInputStream= new BufferedInputStream(new FileInputStream(input_file));
					int  fileSize=bufferedInputStream.available();
					int  noOfChunks=(int) (fileSize/byteBufferSize);
					indexs= new int[noOfChunks];
					byte [] currentByte=null;
					int index=0;
					while(index<noOfChunks)
					{
						
						currentByte= new byte[byteBufferSize];
						bufferedInputStream.read(currentByte);
						bufferedInputStream.skip(byteBufferSize);
						int result=isDuplicateContent(deduped_file, currentByte);
						if(-1 !=result)
						{
							indexs[index]=result;
						}
						else
						{
							indexs[index]=seq;
						}
						index++;
						seq++;
						
					}
				} catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
		}finally
		{
			fileOutputStream.close();
		}
		
		
		
	}
	/**
	 * isDuplicateContent - is Utility method to check given chunk of Byte is 
	 * duplicate or not if yes 
	 * @param deduped_file
	 * @param context
	 * @return
	 * @throws IOException
	 */
	private int  isDuplicateContent(File deduped_file,byte [] context) throws IOException
	{
		int  isDup=-1;
		FileInputStream fileInputStream=null;
		try
		{
			fileInputStream= new FileInputStream(deduped_file);
			int  fileSize=fileInputStream.available();
			int  noOfChunks=(int) (fileSize/byteBufferSize);
			int index=0;
			byte [] currentByte=null;
			while(index<noOfChunks)
			{
				
				currentByte= new byte[byteBufferSize];
				fileInputStream.read(currentByte);
				fileInputStream.skip(byteBufferSize);
				if(Arrays.equals(context,currentByte))
				{
					isDup=index;
					break;
				}
				index++;
				
			}
		}finally 
		{
			fileInputStream.close();
		}
		
		return isDup;
	}
	/**
	 * redup - It Will create back the Original File From deduped file 
	 * refering index of Uniq chunk of Byte 
	 * @param deduped_file_path
	 * @param outPut_file_path
	 * @throws IOException
	 * @throws NoSuchAlgorithmException 
	 */
	public boolean redup(String deduped_file_path , String outPut_file_path) throws IOException, NoSuchAlgorithmException
	{
		boolean isDataCorrect=false;
		File file = new File(deduped_file_path);
		MessageDigest preMd5Digest = MessageDigest.getInstance("MD5");
		MessageDigest postMd5Digest = MessageDigest.getInstance("MD5");
		FileOutputStream fileOutputStream=null;
		try
		{
			fileOutputStream= new FileOutputStream(outPut_file_path, true);
			for(int index:indexs)
			{
				byte [] currentByte=getByteOfCertainPosition(file, index);
				fileOutputStream.write(currentByte);
				preMd5Digest.update(currentByte);
			}
		}finally
		{
			fileOutputStream.close();
		}
		String preCheckSum=getDigestByteToHexString(preMd5Digest);
		String posCheckSum=getCheckSum(postMd5Digest, file);
		isDataCorrect=preCheckSum.equals(posCheckSum);
		return isDataCorrect;
		
	}
	/**
	 * getDigestByteToHexString - Convert Digest Bytes to HexStrings 
	 * @param digest
	 * @return
	 */
	public String getDigestByteToHexString(MessageDigest digest)
	{
	    byte[] bytes = digest.digest();
	    StringBuilder sb = new StringBuilder();
	    for(int i=0; i< bytes.length ;i++)
	    {
	        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
	/**
	 * getCheckSum - Generate CheckSum for File 
	 * @param digest
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public String getCheckSum(MessageDigest digest, File file) throws IOException
	{
		FileInputStream fis = new FileInputStream(file);
		byte[] byteArray = new byte[1024];
	    int bytesCount = 0;
	    while ((bytesCount = fis.read(byteArray)) != -1) 
	    {
	        digest.update(byteArray, 0, bytesCount);
	    };
	    fis.close();
		return getDigestByteToHexString(digest);
	}
	
	/**
	 * getByteOfCertainPosition - Get Byte At Certain Index 
	 * @param file
	 * @param index
	 * @return
	 * @throws IOException
	 */
	public byte [] getByteOfCertainPosition(File file,int index) throws IOException
	{
		byte [] result=new byte[byteBufferSize];
		FileInputStream fileInputStream=null;
		try	
		{
			 fileInputStream= new FileInputStream(file);
				int position=index*byteBufferSize;
				fileInputStream.skip(position);
				fileInputStream.read(result);
				return result;
		}finally 
		{
			fileInputStream.close();
		}
		
	}
	
	public static void main(String[] args) 
	{
		
	}
}
