package com.philips.services.fileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.philips.constants.EnvironmentConstants.StringConstants;
import com.philips.interfaces.IFileOperations;

public class FileOperations implements IFileOperations {
	
	private String filePath;
	
	public FileOperations(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public BufferedReader openReaderStream() 
	{
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
			return reader;
		} catch(FileNotFoundException exception) {
			exception.printStackTrace();
		}
		return null;
	}

	@Override
	public FileWriter openWriterStream() 
	{
		try {
			FileWriter writer = new FileWriter(this.filePath, true);
			return writer;	
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String readFile(BufferedReader reader) 
	{
		String content = StringConstants.EMPTY.get();
		try {			
			String line;
			line = reader.readLine();
			while (line != null) {
				content+=line;
				line = reader.readLine();
			}
		} catch(IOException exception) {
			exception.printStackTrace();
		}
		return content;
	}
	
	
	@Override
	public void appendFile(FileWriter writer, String record) 
	{
		try {
			writer.append(record);
			writer.flush();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void writeFile(FileWriter writer, String record) 
	{
		try {
			writer.write(record);
			writer.flush();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void closeReader(BufferedReader reader)
	{
		try {
			reader.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void closeWriter(FileWriter writer)
	{
		try {
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}