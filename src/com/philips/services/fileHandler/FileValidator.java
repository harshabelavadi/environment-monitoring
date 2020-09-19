package com.philips.services.fileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.philips.constants.EnvironmentConstants.StringConstants;

public class FileValidator {
	private String contentType;
	private File file;
	
	public FileValidator(String filePath, String contentType)
	{
		this.file = new File(filePath);
		this.contentType = contentType;
	}

	public boolean checkFormat() {
		Path filePath;
		String verifyContentType = StringConstants.EMPTY.get();
		try {
			filePath = file.toPath();
			verifyContentType = Files.probeContentType(filePath);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		if(this.contentType.equals(verifyContentType)) 
			return true;
		return false;
	}

	public boolean fileExists() {
		return file.exists();
	}
	
}