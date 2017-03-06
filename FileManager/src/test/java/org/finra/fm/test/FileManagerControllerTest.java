package org.finra.fm.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import org.finra.fm.main.AppMain;
import org.finra.fm.rest.FileManagerController;
import org.finra.fm.rest.FileMetaData;
import org.finra.fm.service.FileManagerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppMain.class)
public class FileManagerControllerTest {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

	@Mock
	private FileManagerService fileService;

	private MockMvc mockMvc;

	@InjectMocks
	private FileManagerController fileController;

	@Before
    public void setupMock() {
       mockMvc = MockMvcBuilders
               .standaloneSetup(fileController)
               .build();
    }

	@Test
	public void testGetFileMetaData() throws Exception{
		Date date = new Date();
		FileMetaData fileMetaData = new FileMetaData("123123","File_Name","TEXT","test_owner",date);

		//mock the file service to return a different object that the request to make sure the mock is returning correctly
		when(fileService.getFileMetaData("123")).thenReturn(fileMetaData);

		mockMvc.perform(get("/fileMetaData?fileId=123")).andExpect(status().isOk())
			.andExpect(content().contentType(contentType))
	        .andExpect(jsonPath("$.id", is(fileMetaData.getId())))
	        .andExpect(jsonPath("$.name", is(fileMetaData.getName())))
	        .andExpect(jsonPath("$.contentType", is(fileMetaData.getContentType())))
	        .andExpect(jsonPath("$.owner", is(fileMetaData.getOwner())));

		verify(fileService).getFileMetaData("123");
	}

	@Test
	public void testFileUpload() throws Exception{
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = format.parse("2011-11-11");

		FileMetaData mockFileMetaData = new FileMetaData(null,"File_Name","TEXT","test_owner",date);
		FileMetaData actualMetaData = new FileMetaData("123123","File_Name","TEXT","test_owner",new Date());
		MockMultipartFile mockFile = new MockMultipartFile("file", "test_file.txt", "text/plain", "some other type".getBytes());

		//mock the file service to return a different object that the request to make sure the mock is returning correctly
		when(fileService.uploadFile(Matchers.any(MultipartFile.class), Matchers.any(FileMetaData.class))).thenReturn(actualMetaData);

		//perform file upload to the controller and except the same data as mocked
		mockMvc.perform(MockMvcRequestBuilders
				.fileUpload("/upload")
				.file(mockFile)
				.param("fileName", mockFileMetaData.getName())
				.param("contentType", mockFileMetaData.getContentType())
				.param("owner", mockFileMetaData.getOwner())
				.param("createdDate", format.format(mockFileMetaData.getCreatedDate())))
		.andExpect(status().isOk())
		.andExpect(content().string(actualMetaData.getId()));

	}

}
