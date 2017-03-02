package org.finra.evaluate.rest;

import java.io.FileNotFoundException;
import java.util.Date;

import org.finra.evaluate.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
public class FileServiceController
{
	@Autowired
	private FileService fileService;

	@RequestMapping(value="/upload", method=RequestMethod.POST)
    public @ResponseBody String upload(
            @RequestParam("file") MultipartFile file,@RequestParam("metaData") FileMetaData fileMetaData) throws FileNotFoundException{
        if (!file.isEmpty()) {
            try {

               /* byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(fileName + "-uploaded")));
                stream.write(bytes);
                stream.close();*/
                return fileMetaData.getId();
            } catch (Exception e) {
                return "You failed to upload " + fileMetaData.getName() + " => " + e.getMessage();
            }
        } else {
            throw new FileNotFoundException();
        }
    }


	@RequestMapping(value="/fileMetaData", method=RequestMethod.GET)
	public FileMetaData getFileMetaData(@RequestParam(value="fileId") String fileId) {
        return fileService.getFileMetaData(fileId);
    }



}
