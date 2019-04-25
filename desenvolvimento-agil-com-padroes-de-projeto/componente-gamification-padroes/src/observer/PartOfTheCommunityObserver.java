package observer;

import model.Achievement;
import model.Badge;
import model.Points;
import storage.AchievementStorage;

public class PartOfTheCommunityObserver implements AchievementObserver {
	
	private AchievementStorage storage;

	public PartOfTheCommunityObserver(AchievementStorage storage) {
		this.storage = storage;
	}

	@Override
	public void achievementUpdate(String user, Achievement a) {
		if (userHasPartOfTheCommunity(user))
			return;
		
		if (a instanceof Points) {
			Points p = (Points) a;
			
			if (p.getName().equals(Achievement.PARTICIPATION) && p.getValue() >= 100)
				storage.addAchievement(user, new Badge(Achievement.PART_OF_THE_COMMUNITY));
		}
		
	}

	private boolean userHasPartOfTheCommunity(String user) {
		Achievement a = storage.getAchievement(user, Achievement.PART_OF_THE_COMMUNITY);
		
		return a != null;
	}
	
}
