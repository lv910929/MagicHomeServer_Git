package com.lencity.magichome.dao;

import java.util.List;
import java.util.Map;

import com.lencity.magichome.model.Feedback;

public interface FeedbackDao {
	
	public void insertFeedback(Feedback feedback);
	
	public void editFeedback(Feedback feedback);
	
	public void removeFeedback(int id);
	
	public Feedback getFeedbackByid(int id);
	
	public List<Feedback> getAllFeedbacks();
	
	public List<Feedback> getFeedbacksByPage(Map<String, Object> parameter);

}
