package com.lencity.magichome.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.lencity.magichome.model.Hardware;
import com.lencity.magichome.service.HardwareService;

@SuppressWarnings("serial")
public class HardwareManagerAction extends BaseAction{
	
	private HardwareService hardwareService;
	
	private List<Hardware> hardwares;
	
	private Hardware hardware;
	
	private File data;
	
	private String dataFileName;
	
	private int id;
	
	private int hardware_type;
	
	private int hardware_version;
	
	private String hardware_size;
	
	private Date upload_time;
	
	private String update_url;
	
	private String hardware_info;

	public HardwareService getHardwareService() {
		return hardwareService;
	}

	public void setHardwareService(HardwareService hardwareService) {
		this.hardwareService = hardwareService;
	}

	public List<Hardware> getHardwares() {
		return hardwares;
	}

	public void setHardwares(List<Hardware> hardwares) {
		this.hardwares = hardwares;
	}

	public Hardware getHardware() {
		return hardware;
	}

	public void setHardware(Hardware hardware) {
		this.hardware = hardware;
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getHardware_type() {
		return hardware_type;
	}

	public void setHardware_type(int hardware_type) {
		this.hardware_type = hardware_type;
	}

	public int getHardware_version() {
		return hardware_version;
	}

	public void setHardware_version(int hardware_version) {
		this.hardware_version = hardware_version;
	}

	public String getHardware_size() {
		return hardware_size;
	}

	public void setHardware_size(String hardware_size) {
		this.hardware_size = hardware_size;
	}

	public Date getUpload_time() {
		return upload_time;
	}

	public void setUpload_time(Date upload_time) {
		this.upload_time = upload_time;
	}

	public String getUpdate_url() {
		return update_url;
	}

	public void setUpdate_url(String update_url) {
		this.update_url = update_url;
	}

	public String getHardware_info() {
		return hardware_info;
	}

	public void setHardware_info(String hardware_info) {
		this.hardware_info = hardware_info;
	}
	
	private InputStream downloadFile;
	
	public InputStream getDownloadFile() {
		hardware=hardwareService.getHardwareByType(hardware_type);
		String url = hardware.getUpdate_url();
		InputStream is=ServletActionContext.getServletContext().getResourceAsStream(url);
		return is;
	}

	public void setDownloadFile(InputStream downloadFile) {
		this.downloadFile = downloadFile;
	}
	/**
	 * 有条件分页查询
	 */
	public String list(){
		
		init();
		hardwares=hardwareService.getHardwaresByCondition(hardware_type, page);
		return "list";
	}
	/**
	 * 上传新版本硬件信息
	 */
	public String publish(){
		
		Hardware hardware=new Hardware();
		if (null!=data) {
			String path = request.getSession().getServletContext()
					.getRealPath("/upload/hardware_data");
			File filePath = new File(path);
			if (!filePath.exists()){
				filePath.mkdirs();
			}
			File saveFile = new File(filePath, dataFileName);
			try{
				FileUtils.copyFile(data, saveFile);
			} catch (IOException e){
				e.printStackTrace();
			}
			hardware.setHardware_type(hardware_type);
			hardware.setHardware_version(hardware_version);
			hardware.setHardware_size(hardware_size);
			hardware.setUpload_time(new Date());
			hardware.setUpdate_url("/upload/hardware_data/"+ saveFile.getName());
			hardware.setHardware_info(hardware_info);
			hardwareService.insertHardware(hardware);
			addLog("上传了数据"+saveFile.getName());
		}
		return SUCCESS;
			
	}
	/**
	 * 删除新版本硬件信息
	 */
	public String remove(){
		
		Hardware hardware=hardwareService.getHardwareByid(id);
		addLog("删除了数据,数据版本是："+hardware.getHardware_version());
		hardwareService.removeHardware(id);
		init();
		hardwares=hardwareService.getHardwaresByCondition(hardware_type, page);
		return "list";
	}
	
	public String downloadData()
	{
		return "downloadData";
	}

}
