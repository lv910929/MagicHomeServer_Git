package com.lencity.magichome.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.lencity.magichome.model.Document;
import com.lencity.magichome.service.DocumentService;

@SuppressWarnings("serial")
public class DocumentManagerAction extends BaseAction{

	private DocumentService documentService;
	
	private List<Document> documents;
	
	private File data;
	
	private String dataFileName;
	
	private Document document;
	
	private int id;
	
	private Date upload_time;
	// 安装包下载地址
	private String download_url;
	// 升级提示信息
	private String file_info;
	
	public DocumentService getDocumentService() {
		return documentService;
	}

	public void setDocumentService(DocumentService documentService) {
		this.documentService = documentService;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	public File getData() {
		return data;
	}

	public void setData(File data) {
		this.data = data;
	}

	public String getDataFileName() {
		return dataFileName;
	}

	public void setDataFileName(String dataFileName) {
		this.dataFileName = dataFileName;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getFile_info() {
		return file_info;
	}

	public void setFile_info(String file_info) {
		this.file_info = file_info;
	}

	private InputStream downloadFile;
	
	public InputStream getDownloadFile(){
		
		document = documentService.getdocument();
		InputStream is=ServletActionContext.getServletContext().getResourceAsStream(document.getDownload_url());
		return is;
	}
	
	public void setDownloadFile(InputStream downloadFile){
		this.downloadFile = downloadFile;
	}
	
	/**
	 * 多条件分页查询版本信息
	 */
	public String list() {
		init();
		documents=documentService.getDocumentsByPage(page);
		return "list";
	}
	
	/**
	 * 上传新版本软件
	 */
	public String publish() {
		
		Document document = new Document();
		if (null != data) {
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/document");
			File filePath = new File(path);
			if (!filePath.exists())
			{
				filePath.mkdirs();
			}
			File saveFile = new File(filePath, dataFileName);
			try
			{
				FileUtils.copyFile(data, saveFile);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			document.setUpload_time(new Date());
			document.setDownload_url("/upload/document/"+ saveFile.getName());
			document.setFile_info(file_info);
			documentService.insertDocument(document);
			addLog("上传了文件："+saveFile.getName());
		}
		return SUCCESS;
	}
	
	public String remove() {
		
		Document document=documentService.getdocument();
		addLog("删除了文件："+document.getDownload_url());
		documentService.removeDocument(id);
		init();
		documents=documentService.getDocumentsByPage(page);
		return "list";
	}
	
	public String downloadDocument(){
		return "downloadDocument";
	}
	
}
