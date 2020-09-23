package com.philips.interfaces;

import java.io.BufferedReader;
import java.io.FileWriter;

/**
 * This IFileOperations interface
 * is used to perform various file handler operations
 	 
 */
public interface IFileOperations {
	public BufferedReader openReaderStream();
	public FileWriter openWriterStream();
	public String readFile(BufferedReader reader);
	public void appendFile(FileWriter writer, String record);
	public void writeFile(FileWriter writer, String record);
	public void closeReader(BufferedReader reader);
	public void closeWriter(FileWriter writer);
}