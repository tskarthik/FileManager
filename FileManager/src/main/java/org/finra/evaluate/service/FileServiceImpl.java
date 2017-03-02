package org.finra.evaluate.service;

import org.finra.evaluate.dao.FileDao;
import org.finra.evaluate.rest.FileMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;

	@Override
	public FileMetaData getFileMetaData(String fileId) {
		return fileDao.getFileMetaData(fileId);
	}

	@Override
	public void uploadFile() {
		// TODO Auto-generated method stub

	}

}
