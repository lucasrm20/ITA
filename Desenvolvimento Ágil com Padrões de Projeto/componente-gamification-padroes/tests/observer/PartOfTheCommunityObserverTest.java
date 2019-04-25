package observer;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import model.Achievement;
import model.Badge;
import model.Points;
import storage.MemoryAchievementStorage;

public class PartOfTheCommunityObserverTest {
	
	private AchievementObserver observer;
	private MemoryAchievementStorage storage;

	@Before
	public void before() {
		storage = new MemoryAchievementStorage();
		observer = new PartOfTheCommunityObserver(storage);
	}
	
	@Test
	public void adicionaOAchievementQuandoParticipationPointsChegarA100() {
		String user = "User";
		observer.achievementUpdate(user, new Points(Achievement.PARTICIPATION, 100));
		
		assertNotNull(storage.getAchievement(user, Achievement.PART_OF_THE_COMMUNITY));
	}
	
	@Test
	public void naoFazNadaSeParticipationPointsForMenorDoQue100() {
		String user = "User";
		observer.achievementUpdate(user, new Points(Achievement.PARTICIPATION, 50));
		
		assertNull(storage.getAchievement(user, Achievement.PART_OF_THE_COMMUNITY));
	}
	
	@Test
	public void naoFazNadaSeReceberUmAchievementDiferenteDeParticipation() {
		String user = "User";
		observer.achievementUpdate(user, new Badge(Achievement.I_CAN_TALK));
		
		assertNull(storage.getAchievement(user, Achievement.PART_OF_THE_COMMUNITY));
	}

}
