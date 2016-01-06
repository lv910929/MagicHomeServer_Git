package com.lencity.magichome.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lencity.magichome.dao.FeedbackDao;
import com.lencity.magichome.entity.Page;
import com.lencity.magichome.model.Feedback;

public class FeedbackService {
	
	private FeedbackDao feedbackDao;

	public FeedbackDao getFeedbackDao() {
		return feedbackDao;
	}

	public void setFeedbackDao(FeedbackDao feedbackDao) {
		this.feedbackDao = feedbackDao;
	}
	//新增反馈
	public void insertFeedback(Feedback feedback){
		feedbackDao.insertFeedback(feedback);
	}
	//修改反馈
	public void editFeedback(Feedback feedback){
		//当反馈未读时，修改反馈状态为已读
		if (feedback.getStatus().equals(Feedback.FEEDBACK_UNREAD)) {
			feedback.setStatus(Feedback.FEEDBACK_READ);
			feedbackDao.editFeedback(feedback);
		}
	}
	//删除反馈
	public void removeFeedback(int id){
		feedbackDao.removeFeedback(id);
	}
	//根据id获取反馈
	public Feedback getFeedbackByid(int id){
		Feedback feedback=feedbackDao.getFeedbackByid(id);
		return feedback;
	}
	
	/**
	 * 分页获取反馈信息
	 */
	public List<Feedback> getFeedbacksByPage(String account_name,int status,Page page){
		
		Map<String,Object> parameter = new HashMap<String, Object>();
		Feedback feedback=new Feedback();
		feedback.setAccount_name(account_name);
		feedback.setStatus(status);
		parameter.put("feedback", feedback);
		parameter.put("page", page);
		return feedbackDao.getFeedbacksByPage(parameter);
	}
}
