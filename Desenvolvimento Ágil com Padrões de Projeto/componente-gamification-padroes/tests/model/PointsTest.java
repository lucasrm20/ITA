package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PointsTest {

	@Test
	public void adicionaUmNovoPointALista() {
		List<Achievement> achievements = new ArrayList<>();
		
		Points point = new Points("X", 100);
		point.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
		assertTrue(achievements.contains(point));
		assertEquals(point, achievements.get(0));
	}
	
	@Test
	public void quandoUmPointJaExisteNaListaAtualizaOSeuValue() {
		List<Achievement> achievements = new ArrayList<>();
		
		Points p1 = new Points("X", 100);
		p1.addAchievement(achievements);
		
		Points p2 = new Points("X", 150);
		p2.addAchievement(achievements);
		
		assertEquals(1, achievements.size());
		assertEquals(250, ((Points) achievements.get(0)).getValue());
	}

}
