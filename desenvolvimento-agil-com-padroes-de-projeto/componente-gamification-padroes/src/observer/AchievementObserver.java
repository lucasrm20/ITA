package observer;

import model.Achievement;

public interface AchievementObserver {

	void achievementUpdate(String user, Achievement a);
	
}
