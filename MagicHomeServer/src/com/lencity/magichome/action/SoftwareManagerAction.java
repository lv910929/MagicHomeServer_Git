package com.lencity.magichome.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.lencity.magichome.model.Software;
import com.lencity.magichome.service.SoftwareService;
import com.lencity.magichome.socket.thread.udp.UDPBusinessService;

@SuppressWarnings("serial")
public class SoftwareManagerAction extends BaseAction {

	private SoftwareService softwareService;

	private List<Software> softwares;

	public SoftwareService getSoftwareService() {
		return softwareService;
	}

	public void setSoftwareService(SoftwareService softwareService) {
		this.softwareService = softwareService;
	}

	private File install;

	public File getInstall() {
		return install;
	}

	public void setInstall(File install) {
		this.install = install;
	}

	private String installFileName;

	public String getInstallFileName() {
		return installFileName;
	}

	public void setInstallFileName(String installFileName) {
		try {
			this.installFileName = new String(
					installFileName.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private Software software;

	public Software getSoftware() {
		return software;
	}

	public void setSoftware(Software software) {
		this.software = software;
	}

	private int id;

	private int software_type;
	// 应用版本
	private int software_version;
	// 应用安装包大小
	private String software_size;
	// 上传时间
	private Date release_time;
	// 安装包下载地址
	private String download_url;
	// 升级提示信息
	private String software_info;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSoftware_type() {
		return software_type;
	}

	public void setSoftware_type(int software_type) {
		this.software_type = software_type;
	}

	public int getSoftware_version() {
		return software_version;
	}

	public void setSoftware_version(int software_version) {
		this.software_version = software_version;
	}

	public String getSoftware_size() {
		return software_size;
	}

	public void setSoftware_size(String software_size) {
		this.software_size = software_size;
	}

	public Date getRelease_time() {
		return release_time;
	}

	public void setRelease_time(Date release_time) {
		this.release_time = release_time;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getSoftware_info() {
		return software_info;
	}

	public void setSoftware_info(String software_info) {
		this.software_info = software_info;
	}

	public List<Software> getSoftwares() {
		return softwares;
	}

	public void setSoftwares(List<Software> softwares) {
		this.softwares = softwares;
	}

	private InputStream downloadFile;
	
	public InputStream getDownloadFile() {
		return downloadFile;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}

	/**
	 * 多条件分页查询版本信息
	 */
	public String list() {
		init();
		softwares = softwareService.getSoftwaresByPage(software_type, page);
		return "list";
	}

	/**
	 * 上传新版本软件
	 */

	public String publish() {

		Software software = new Software();
		if (null != install) {

			String path = request.getSession().getServletContext()
					.getRealPath("/upload/app_install");
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			File saveFile = new File(filePath, installFileName);
			try {
				FileUtils.copyFile(install, saveFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			software.setSoftware_type(software_type);
			software.setSoftware_version(software_version);
			software.setSoftware_size(software_size);
			software.setRelease_time(new Date());
			software.setDownload_url("/upload/app_install/"
					+ saveFile.getName());
			software.setSoftware_info(software_info);
			softwareService.insertSoftware(software);
			addLog("上传了app：" + saveFile.getName());
		}
		return "listAction";
	}

	/**
	 * 删除软件版本
	 */
	public String remove() {

		Software software = softwareService.getSoftwareByid(id);
		addLog("删除了app：" + software.getSoftware_info());
		softwareService.removeSoftware(id);
		init();
		softwares = softwareService.getSoftwaresByPage(software_type, page);
		return "list";
	}

	public String download() {

		System.out.println("正在下载文件...");
		software = softwareService.getLastestSoftware(software_type);
		downloadFile = ServletActionContext.getServletContext().getResourceAsStream(
				software.getDownload_url());
		return SUCCESS;
	}
}
