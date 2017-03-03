package org.finra.fm.dao;

import org.finra.fm.rest.FileMetaData;


public interface FileDao {
	public FileMetaData getFileMetaData(String fileId);

	public void createFileMetaData(FileMetaData fileMetaData, String fileLocation);
}
