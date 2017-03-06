package org.finra.fm.storage;

import java.io.File;
import java.io.IOException;

import org.finra.fm.exception.FileManagerException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemService implements StorageService {

	/**
	 * Takes a file object and returns the stored file location
	 * @throws FileManagerException
	 * @throws IOException
	 */
	@Override
	public String storeFile(MultipartFile file) throws FileManagerException {

		String baseDir = "C:/fm_fileUploads/"+System.currentTimeMillis();
		String fileLocation = baseDir+"/"+file.getOriginalFilename();

		try {
			if(! new File(baseDir).exists())
            {
                new File(baseDir).mkdirs();
            }
			file.transferTo(new File(fileLocation));
			return fileLocation;
		} catch (IOException e) {
			throw new FileManagerException(e);
		}
	}

	/**
	 * Input is a file location and output is actual file
	 */
	@Override
	public File retriveFile(String fileLocation) {
		return new File(fileLocation);
	}

}
