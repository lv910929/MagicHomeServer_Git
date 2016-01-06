package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.DocumentDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Document;

public class DocumentService {
	
	private DocumentDao documentDao;
	
	public DocumentDao getDocumentDao() {
		return documentDao;
	}

	public void setDocumentDao(DocumentDao documentDao) {
		this.documentDao = documentDao;
	}

	public void insertDocument(Document file){
		documentDao.insertDocument(file);
	}
	
	public void removeDocument(int id){
		documentDao.removeDocument(id);
	}
	
	public List<Document> getDocumentsByPage(Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		parameter.put("page", page);
		return documentDao.getDocumentsByPage(parameter);
	}
	
	public Document getdocument(){
		
		Document document = new Document();
		document = documentDao.getDocuments().get(0);
		return document;
	}
	
}
