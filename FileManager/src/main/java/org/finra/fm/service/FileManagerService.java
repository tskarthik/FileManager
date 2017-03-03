package org.finra.fm.service;

import org.finra.fm.exception.FileManagerException;
import org.finra.fm.rest.FileMetaData;
import org.springframework.web.multipart.MultipartFile;

public interface FileManagerService {
	public FileMetaData getFileMetaData(String fileId) throws FileManagerException;

	public FileMetaData uploadFile(MultipartFile file, FileMetaData fileMetaData) throws FileManagerException;
}
