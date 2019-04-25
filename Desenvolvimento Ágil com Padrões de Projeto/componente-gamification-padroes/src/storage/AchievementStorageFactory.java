package storage;

public class AchievementStorageFactory {

	private static AchievementStorage storage = null;
	
	public static AchievementStorage getAchievementStorage() {
		if (storage == null)
			storage = new MemoryAchievementStorage();
			
		return storage;
	}
	
	public static void setAchievementStorage(AchievementStorage a) {
		AchievementStorageFactory.storage = a;
	}
	
}
