package storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Achievement;
import observer.AchievementObserver;

public class MemoryAchievementStorage implements AchievementStorage {
	
	private Map<String, List<Achievement>> achievements = new HashMap<>();
	private List<AchievementObserver> observers = new ArrayList<>();

	@Override
	public void addAchievement(String user, Achievement a) {
		List<Achievement> userAchievements = new ArrayList<>();
		
		if (achievements.containsKey(user)) {
			userAchievements = achievements.get(user);
		}
		
		a.addAchievement(userAchievements);	
		achievements.put(user, userAchievements);
		
		notify(user, a);
	}

	@Override
	public List<Achievement> getAchievements(String user) {
		return achievements.get(user);
	}

	@Override
	public Achievement getAchievement(String user, String achievementName) {
		List<Achievement> userAchievements = achievements.get(user);
		
		if (userAchievements == null)
			return null;
		
		return userAchievements
				.stream()
				.filter(a -> a.getName().equals(achievementName))
				.findFirst()
				.orElse(null);
	}

	@Override
	public void add(AchievementObserver o) {
		this.observers.add(o);
	}

	@Override
	public void remove(AchievementObserver o) {
		this.observers.remove(0);
	}

	@Override
	public void notify(String user, Achievement a) {
		for (AchievementObserver observer : observers) {
			observer.achievementUpdate(user, a);
		}
	}

}
