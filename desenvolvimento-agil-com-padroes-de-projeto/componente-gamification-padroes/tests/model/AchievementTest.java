package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AchievementTest {

	@Test
	public void adicionaDiferentesAchievementALista() {
		List<Achievement> achievements = new ArrayList<>();
		
		Achievement a1 = new Points("point A", 100);
		a1.addAchievement(achievements);
		
		Achievement a2 = new Badge("badge B");
		a2.addAchievement(achievements);
		
		Achievement a3 = new Badge("badge C");
		a3.addAchievement(achievements);
		
		Achievement a4 = new Points("point D", 300);
		a4.addAchievement(achievements);
		
		assertEquals(4, achievements.size());
		
		assertTrue(achievements.contains(a1));
		assertEquals(a1, achievements.get(0));
		
		assertTrue(achievements.contains(a2));
		assertEquals(a2, achievements.get(1));
		
		assertTrue(achievements.contains(a3));
		assertEquals(a3, achievements.get(2));
		
		assertTrue(achievements.contains(a4));
		assertEquals(a4, achievements.get(3));
	}
	
	@Test
	public void naoPodeHaverMaisDeUmAchievementComMesmoNomeMesmoQueDeTiposDiferentes() {
		List<Achievement> achievements = new ArrayList<>();
		
		Achievement a1 = new Points("Achievement A", 100);
		a1.addAchievement(achievements);
		
		Achievement a2 = new Badge("Achievement A");
		a2.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
	}
	
	@Test
	public void naoSubstituiUmTipoDeAchievementPorOutroTipoNaLista() {
		List<Achievement> achievements = new ArrayList<>();
		
		Achievement a1 = new Points("Achievement A", 100);
		a1.addAchievement(achievements);
		
		Achievement a2 = new Badge("Achievement A");
		a2.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
		
		assertTrue(achievements.contains(a1));
		assertEquals(a1, achievements.get(0));
		
		assertFalse(achievements.contains(a2));
	}

}
