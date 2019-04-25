package service;

public interface ForumService {

	void addTopic(String user, String topic);
	void addComment(String user, String topic, String comment);
	void likeTopic(String user, String topic, String topicUser);
	void likeComment(String user, String topic, String comment, String commentUser);
	
}
