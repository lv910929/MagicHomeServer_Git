package com.lencity.magichome.action;

import java.util.List;

import com.lencity.magichome.model.Feedback;
import com.lencity.magichome.service.FeedbackService;

@SuppressWarnings("serial")
public class FeedbackManagerAction extends BaseAction{

	private List<Feedback> feedbacks;
	
	public List<Feedback> getFeedbacks() {
		return feedbacks;
	}

	public void setFeedbacks(List<Feedback> feedbacks) {
		this.feedbacks = feedbacks;
	}

	private FeedbackService feedbackService;

	public FeedbackService getFeedbackService() {
		return feedbackService;
	}

	public void setFeedbackService(FeedbackService feedbackService) {
		this.feedbackService = feedbackService;
	}
	
	private int id;
	
	private String account_name;
		
	private int status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * 分页获取反馈信息
	 */
	public String list(){

		init();
		feedbacks=feedbackService.getFeedbacksByPage(account_name, status, page);
		return "list";
	}
	/**
	 * 删除反馈
	 */
	public String remove(){
		
		Feedback feedback = feedbackService.getFeedbackByid(id);
		addLog("删除了反馈:"+feedback.getContent());
		feedbackService.removeFeedback(id);
		init();
		feedbacks=feedbackService.getFeedbacksByPage(account_name, status, page);
		return "list";
	}
}
