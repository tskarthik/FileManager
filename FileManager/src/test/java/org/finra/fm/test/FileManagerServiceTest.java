package org.finra.fm.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.finra.fm.dao.FileDao;
import org.finra.fm.exception.FileManagerException;
import org.finra.fm.rest.FileMetaData;
import org.finra.fm.service.FileManagerService;
import org.finra.fm.service.FileManagerServiceImpl;
import org.finra.fm.storage.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerServiceTest {

	@Mock
	private FileDao fileDao;

	@Mock
	private StorageService storageService;

	@Mock
	private MultipartFile file;

	@InjectMocks
	private FileManagerService fileService = new FileManagerServiceImpl();

	@Before
    public void setupMock() {
       MockitoAnnotations.initMocks(this);

    }

	@Test
	public void testGetFileMetaData() throws FileManagerException {
		Date date = new Date();
		FileMetaData fileMetaData = new FileMetaData("123","File_Name","TEXT","test_owner",date);

		//DAO calls are mocked so no direct DB calls are made
		when(fileDao.getFileMetaData("123")).thenReturn(fileMetaData);

		//call the method under test
		FileMetaData actualMetaData = fileService.getFileMetaData("123");

		//verify whether DAO calls are made is correct data.
		verify(fileDao).getFileMetaData("123");

		//assert if expected and actual match
		assertThat(fileMetaData,new ReflectionEquals(actualMetaData));
	}

	@Test
	public void testUploadFile() throws FileManagerException {
		Date date = new Date();
		FileMetaData fileMetaData = new FileMetaData(null,"File_Name","TEXT","test_owner",date);
		String fileLocation = "c:/test/file/location";

		//mimic storageservice method call
		when(storageService.storeFile(file)).thenReturn(fileLocation);
		//mimic fileDao method call
		Mockito.doNothing().when(fileDao).createFileMetaData(Matchers.any(FileMetaData.class),Matchers.any(String.class));

		//call the actual method under test
		FileMetaData actualMetaData = fileService.uploadFile(file, fileMetaData);

		//verify if filedao service invoked with correct parameters
		verify(fileDao).createFileMetaData(fileMetaData,fileLocation);

		//verify if the storage service was invoked with correct parameters
		verify(storageService).storeFile(file);


		assertNotNull(actualMetaData.getId());
	}


}
