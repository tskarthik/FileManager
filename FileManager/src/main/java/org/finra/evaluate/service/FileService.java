package org.finra.evaluate.service;

import org.finra.evaluate.rest.FileMetaData;

public interface FileService {
	public FileMetaData getFileMetaData(String fileId);

	public void uploadFile();
}
