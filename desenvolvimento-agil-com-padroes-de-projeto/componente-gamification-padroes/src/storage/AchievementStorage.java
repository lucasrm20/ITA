package storage;

import java.util.List;

import model.Achievement;
import observer.AchievementObservable;

public interface AchievementStorage extends AchievementObservable {

	void addAchievement(String user, Achievement a);
	List<Achievement> getAchievements(String user);
	Achievement getAchievement(String user, String achievementName);
	
}
