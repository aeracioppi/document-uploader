package com.manager.document.model;

import java.sql.Date;

public class Document {
	
	private int documentID;
	private String document_name;
	private Date document_upload;
	private String document_type;
	private String document_owner;
	private byte[] document_file;
	private String document_desc;

	public int getDocumentID() {
		return documentID;
	}
	
	public void setDocumentID(int documentID) {
		this.documentID = documentID;
	}
	
	public String getDocument_name() {
		return document_name;
	}
	
	public void setDocument_name(String document_name) {
		this.document_name = document_name;
	}
	
	public Date getDocument_upload() {
		return document_upload;
	}
	
	public void setDocument_upload(Date document_upload) {
		this.document_upload = document_upload;
	}
	
	public String getDocument_type() {
		return document_type;
	}
	
	public void setDocument_type(String document_type) {
		this.document_type = document_type;
	}
	
	public String getDocument_owner() {
		return document_owner;
	}
	
	public void setDocument_owner(String document_owner) {
		this.document_owner = document_owner;
	}
	
	public byte[] getDocument_file() {
		return document_file;
	}
	
	public void setDocument_file(byte[] document_file) {
		this.document_file = document_file;
	}
	
	public String getDocument_desc() {
		return document_desc;
	}
	
	public void setDocument_desc(String document_desc) {
		this.document_desc = document_desc;
	}

	@Override
	public String toString() {
		return "Document [document_name=" + document_name
				+ ", document_upload=" + document_upload.toString() + ", document_type="
				+ document_type + ", document_owner=" + document_owner
				+ ", document_desc=" + document_desc + "]";
	}
	
	
}
