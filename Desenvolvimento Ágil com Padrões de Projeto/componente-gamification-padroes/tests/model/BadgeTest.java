package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BadgeTest {

	@Test
	public void adicionaUmaNovaBagdeALista() {
		List<Achievement> achievements = new ArrayList<>();
		
		Badge badge = new Badge("X");
		badge.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
		assertTrue(achievements.contains(badge));
		assertEquals(badge, achievements.get(0));
	}
	
	@Test
	public void quandoABagdeJaExisteNaListaNaoAdiciona() {
		List<Achievement> achievements = new ArrayList<>();
		
		Badge b1 = new Badge("X");
		b1.addAchievement(achievements);
		
		Badge b2 = new Badge("X");
		b2.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
		assertTrue(achievements.contains(b1));
		assertTrue(achievements.contains(b2));
		assertEquals(b1, achievements.get(0));
		assertEquals(b2, achievements.get(0));
	}

}
