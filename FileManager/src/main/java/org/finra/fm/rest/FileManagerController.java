package org.finra.fm.rest;

import java.util.Date;

import org.finra.fm.exception.FileManagerException;
import org.finra.fm.service.FileManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileManagerController
{
	@Autowired
	private FileManagerService fileService;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String upload(
            @RequestParam("file") MultipartFile file,@RequestParam(value="fileName") String fileName,
            @RequestParam(value="ContentType") String ContentType,
            @RequestParam(value="owner") String owner,
            @RequestParam(value="createdDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date createdDate) throws FileManagerException{
        if (!file.isEmpty()) {
        		return fileService.uploadFile(file,new FileMetaData(null,fileName,ContentType,owner,createdDate)).getId();
        } else {
            throw new FileManagerException("Given input file is empty");
        }
    }


	@RequestMapping(value="/fileMetaData", method=RequestMethod.GET)
	public FileMetaData getFileMetaData(@RequestParam(value="fileId") String fileId) throws FileManagerException {
        return fileService.getFileMetaData(fileId);
    }


	@RequestMapping(value="/test", method=RequestMethod.POST)
	public @ResponseBody String test(@RequestParam("file") MultipartFile file,@RequestParam(value="fileName") String fileName) {
		System.out.println(fileName);
		return fileName+"___0000";
	}
}
