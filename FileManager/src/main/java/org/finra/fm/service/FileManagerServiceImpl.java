package org.finra.fm.service;

import org.finra.fm.dao.FileDao;
import org.finra.fm.exception.FileManagerException;
import org.finra.fm.rest.FileMetaData;
import org.finra.fm.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileManagerServiceImpl implements FileManagerService {

	@Autowired
	private FileDao fileDao;

	@Autowired
	private StorageService storageService;

	@Override
	public FileMetaData getFileMetaData(String fileId) {
		return fileDao.getFileMetaData(fileId);
	}


	@Override
	public FileMetaData uploadFile(MultipartFile file, FileMetaData fileMetaData) throws FileManagerException {
		String fileLocation = storageService.storeFile(file);
		fileMetaData.setId(String.valueOf(generateFileId()));
		fileDao.createFileMetaData(fileMetaData,fileLocation);
		return fileMetaData;
	}

	private long generateFileId(){
		return System.currentTimeMillis();
	}

}
