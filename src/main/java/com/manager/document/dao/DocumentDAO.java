package com.manager.document.dao;

import java.sql.Date;
import java.util.List;

import com.manager.document.model.Document;

public interface DocumentDAO {
	
	public void save(Document doc);
	public List<Document> list(); 
    public Document get(Integer id);
    public void remove(Integer id);
    public List<Document> selectCriteria(String name, String owner, Date sql);

}
