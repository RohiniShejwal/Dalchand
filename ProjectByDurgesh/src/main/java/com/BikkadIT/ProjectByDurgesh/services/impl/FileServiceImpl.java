package com.BikkadIT.ProjectByDurgesh.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.BikkadIT.ProjectByDurgesh.services.FileServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FileServiceImpl implements FileServiceI{

// upload image
	
	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		log.info("Initiating the dao call for upload image");
		// File name
		String name = file.getOriginalFilename();
		// abc.png
		
		// random name generate file
		String randomID = UUID.randomUUID().toString();
		String filename1 = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		// full path
		String filePath= path + File.separator + filename1;
		
		// create folder if not created
		File f = new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		// file copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		log.info("Completing the dao call for upload image");
		return name;
	}

// get image
	
	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		
		log.info("Initiating the dao call for download image");
		String fullPath=path+File.separator+fileName;
		FileInputStream stream = new FileInputStream(fullPath);
		log.info("Completing the dao call for download image");
		return stream;
	}

}
