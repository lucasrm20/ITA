package storage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Achievement;
import model.Badge;
import model.Points;

public class MemoryAchievementStorageTest {
	
	private MemoryAchievementStorage storage;

	@Before
	public void before() {
		storage = new MemoryAchievementStorage();
	}
	
	@Test
	public void retornaNullAoTentarRecuperarOsAchievementsDeUmUsuarioQueNaoPossuiNenhum() {
		assertNull(storage.getAchievements("Sem Achievements"));
	}
	
	@Test
	public void retornaAListaDeAchievementsDeUmUsuario() {
		String user = "User01";
		storage.addAchievement(user, new Badge("Achievement 01"));
		
		assertNotNull(storage.getAchievements(user));
	}

	@Test
	public void adicionaAchievementsParaUmUsuario() {
		String user = "User01";
		
		storage.addAchievement(user, new Badge("Achievement 01"));
		storage.addAchievement(user, new Points("Achievement 02", 100));
		
		assertNotNull(storage.getAchievements(user));
		assertEquals(2, storage.getAchievements(user).size());
	}
	
	@Test
	public void retornaNullAoTentarRecuperarUmAchievementInexistenteDeUmUsuarioPeloName() {
		String user = "User01";
		storage.addAchievement(user, new Badge("Achievement 01"));
		
		assertNull(storage.getAchievement(user, "Achievement 55"));
	}
	
	@Test
	public void retornaUmAchievementDeUmUsuarioPeloName() {
		String user = "User01";
		Achievement achievement = new Badge("Achievement 01");
		storage.addAchievement(user, achievement);
		
		assertNotNull(storage.getAchievement(user, "Achievement 01"));
		assertEquals(achievement, storage.getAchievement(user, "Achievement 01"));
	}

}
