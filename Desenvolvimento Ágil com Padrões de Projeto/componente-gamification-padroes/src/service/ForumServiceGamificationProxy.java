package service;

import model.Achievement;
import model.Badge;
import model.Points;
import storage.AchievementStorage;

public class ForumServiceGamificationProxy implements ForumService {

	private ForumService forumService;
	private AchievementStorage storage;

	public ForumServiceGamificationProxy(ForumService forumService, AchievementStorage storage) {
		this.forumService = forumService;
		this.storage = storage;
	}

	public void addTopic(String user, String topic) {
		try {
			forumService.addTopic(user, topic);
		
			storage.addAchievement(user, new Points(Achievement.CREATION, 5));
			storage.addAchievement(user, new Badge(Achievement.I_CAN_TALK));
			
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void addComment(String user, String topic, String comment) {
		try {
			forumService.addComment(user, topic, comment);
			
			storage.addAchievement(user, new Points(Achievement.PARTICIPATION, 3));
			storage.addAchievement(user, new Badge(Achievement.LET_ME_ADD));
		
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
		
	}

	public void likeTopic(String user, String topic, String topicUser) {
		try {
			forumService.likeTopic(user, topic, topicUser);
		
			storage.addAchievement(user, new Points(Achievement.CREATION, 1));
			
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}

	public void likeComment(String user, String topic, String comment, String commentUser) {
		try {
			forumService.likeComment(user, topic, comment, commentUser);
		
			storage.addAchievement(user, new Points(Achievement.PARTICIPATION, 1));
		
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
	}
	
}
