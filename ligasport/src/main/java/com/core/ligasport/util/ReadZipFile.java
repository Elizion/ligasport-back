package com.core.ligasport.util;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class ReadZipFile {

	private static final Logger logger = LoggerFactory.getLogger(ReadZipFile.class);
	
	public void depositFilesFromZip(@RequestParam("file") MultipartFile multipartFile) throws IOException {		   				
		ZipEntry entry = null;												
		String nameFile = null;				
		try {				
			ZipInputStream packaging = new ZipInputStream(multipartFile.getInputStream());								
			while((entry = packaging.getNextEntry()) != null) {				
				nameFile = entry.getName();
				logger.info(nameFile);																			
			}			
		} catch(Exception e) {
			System.out.println(e);
		}        
	}
	
}
