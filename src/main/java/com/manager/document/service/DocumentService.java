package com.manager.document.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.manager.document.controller.DocumentController;
import com.manager.document.dao.DocumentDAO;
import com.manager.document.model.Document;

@Service
public class DocumentService {
	
Logger log = Logger.getLogger(DocumentController.class);
	
	private DocumentDAO docDao;
	
	@Autowired
	public DocumentService( DocumentDAO docDao){
		this.docDao = docDao;
	}
	
	public List<Document> getDocumentsList(){
		log.info("Buscando el listado de Documentos...");
		List<Document> docList = docDao.list();
        return docList; 
	}
	
	public void saveDocument(CommonsMultipartFile aFile, String type, String owner, String description){
		Document uploadFile = new Document();
        uploadFile.setDocument_name(aFile.getOriginalFilename());
        uploadFile.setDocument_file(aFile.getBytes());
	    uploadFile.setDocument_type(type);   
	    uploadFile.setDocument_owner(owner);   
	    uploadFile.setDocument_desc(description);   
        uploadFile.setDocument_upload(getDate());
		docDao.save(uploadFile);
	}
	
	private java.sql.Date getDate(){
		java.util.Date utilDate = new java.util.Date();
        return new java.sql.Date(utilDate.getTime());
	}

	public Document getDocument(Integer documentId){
			return docDao.get(documentId);
	}
	
	public void deleteDocument(Integer documentId){
		docDao.remove(documentId);
	}

	public List<Document> searchDocuments(String name, String owner, String date) {
		List<Document> docList = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yy");
			Date parsed;
			parsed = format.parse(date);
			java.sql.Date sql = new java.sql.Date(parsed.getTime());
			docList = docDao.selectCriteria(name, owner, sql);
			log.info("Se encontraron "+docList.size()+" documentos. ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return docList; 
		
	}
}
