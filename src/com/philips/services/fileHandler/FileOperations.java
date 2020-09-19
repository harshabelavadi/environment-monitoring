package com.philips.services.fileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
	
	private String filePath;
	
	public FileOperations(String filePath) {
		this.filePath = filePath;
	}
	
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
	
	public void readFile(BufferedReader reader) 
	{
		try {
			String line;
			line = reader.readLine();
			while (line != null)				
				line = reader.readLine();				
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void appendFile(FileWriter writer, String record) 
	{
		try {
			writer.append(record);
			writer.flush();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void writeFile(FileWriter writer, String record) 
	{
		try {
			writer.write(record);
			writer.flush();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void closeReader(BufferedReader reader)
	{
		try {
			reader.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
	
	public void closeWriter(FileWriter writer)
	{
		try {
			writer.close();
		} catch(IOException exception) {
			exception.printStackTrace();
		}
	}
}