package observer;

import model.Achievement;

public interface AchievementObservable {

	void add(AchievementObserver o);
	void remove(AchievementObserver o);
	void notify(String user, Achievement a);
	
}
