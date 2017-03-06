package org.finra.fm.exception;

public class FileManagerException extends Exception{

	public FileManagerException(Exception e) {
		super(e);
	}

	public FileManagerException(String msg) {
		super(msg);
	}

}
