package observer;

import model.Achievement;
import model.Badge;
import model.Points;
import storage.AchievementStorage;

public class InventorObserver implements AchievementObserver {

	private AchievementStorage storage;

	public InventorObserver(AchievementStorage storage) {
		this.storage = storage;
	}
	
	@Override
	public void achievementUpdate(String user, Achievement a) {
		if (userHasInventor(user))
			return;
		
		if (a instanceof Points) {
			Points p = (Points) a;
			
			if (p.getName().equals(Achievement.CREATION) && p.getValue() >= 100)
				storage.addAchievement(user, new Badge(Achievement.INVENTOR));
		}
	}
	
	private boolean userHasInventor(String user) {
		Achievement a = storage.getAchievement(user, Achievement.INVENTOR);
		
		return a != null;
	}

}
