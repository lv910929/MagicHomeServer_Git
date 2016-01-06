package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Document;

public interface DocumentDao {
	
	public void insertDocument(Document document);
	
	public void removeDocument(int id);
	
	public List<Document> getDocumentsByPage(Map<String, Object> parameter);
	
	public List<Document> getDocuments();

}
