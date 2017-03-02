package org.finra.evaluate.dao;

import org.finra.evaluate.rest.FileMetaData;


public interface FileDao {
	public FileMetaData getFileMetaData(String fileId);

	public void uploadFile();
}
