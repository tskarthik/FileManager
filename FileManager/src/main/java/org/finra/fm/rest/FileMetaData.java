package org.finra.fm.rest;

import java.util.Date;

public class FileMetaData {

	private String id;
	private String name;
	private String contentType;
	private String owner;
	private Date createdDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "FileMetaData [id=" + id + ", name=" + name + ", contentType=" + contentType + ", owner=" + owner
				+ ", createdDate=" + createdDate + "]";
	}

	public FileMetaData(String id, String name, String contentType, String owner, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.contentType = contentType;
		this.owner = owner;
		this.createdDate = createdDate;
	}

	public FileMetaData(){

	}



}
