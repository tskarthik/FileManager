package org.finra.fm.storage;

import java.io.File;

import org.finra.fm.exception.FileManagerException;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
	public String storeFile(MultipartFile file)  throws FileManagerException;
	public File retriveFile(String fileLocation);
}
