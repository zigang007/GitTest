package org.fkit.oa.util.entity;

import java.io.InputStream;

public class FileEntity   {

	private InputStream inputStream;
	
	private String fileType;
	
	private String fileName;
	
	private String UUIDFileName;
	
	private long fileSize;
	
	private String filePath;

	 

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUUIDFileName() {
		return UUIDFileName;
	}

	public void setUUIDFileName(String uUIDFileName) {
		UUIDFileName = uUIDFileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	
}
