package service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.verification.Times;

import model.Achievement;
import model.Points;
import observer.InventorObserver;
import observer.PartOfTheCommunityObserver;
import storage.AchievementStorage;
import storage.AchievementStorageFactory;
import storage.MemoryAchievementStorage;

public class ForumServiceGamificationProxyTest {
	
	private ForumServiceGamificationProxy forumProxy;
	private AchievementStorage storage;
	private ForumService mockForumService;
	
	@Before
	public void before() {
		AchievementStorageFactory.setAchievementStorage(new MemoryAchievementStorage());
		
		storage = AchievementStorageFactory.getAchievementStorage();
		storage.add(new InventorObserver(storage));
		storage.add(new PartOfTheCommunityObserver(storage));
		
		mockForumService = mock(ForumService.class);
		
		forumProxy = new ForumServiceGamificationProxy(mockForumService, storage);
	}
	
	@Test
	public void adicionaCorretamenteOsAchievementsAoCriarUmTopico() {
		String user = "User";
		
		forumProxy.addTopic(user, "New Topic");
		
		verify(mockForumService, new Times(1)).addTopic(user, "New Topic");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.CREATION));
		assertEquals(5, ((Points) storage.getAchievement(user, Achievement.CREATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.I_CAN_TALK));
	}
	
	@Test
	public void adicionaCorretamenteOsAchievementsAoComentar() {
		String user = "User";
		forumProxy.addComment(user, "Foo Topic", "Comentário Sobre Alguma Coisa");
		
		verify(mockForumService, new Times(1)).addComment(user, "Foo Topic", "Comentário Sobre Alguma Coisa");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.PARTICIPATION));
		assertEquals(3, ((Points) storage.getAchievement(user, Achievement.PARTICIPATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.LET_ME_ADD));
	}
	
	@Test
	public void adicionaCorretamenteOsAchievementsAoDarLikeEmUmTopico() {
		String user = "User";
		forumProxy.likeTopic(user, "Foo Topic", "Bar User");
		
		verify(mockForumService, new Times(1)).likeTopic(user, "Foo Topic", "Bar User");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(1, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.CREATION));
		assertEquals(1, ((Points) storage.getAchievement(user, Achievement.CREATION)).getValue());
	}
	
	@Test
	public void adicionaCorretamenteOsAchievementsAoDarLikeEmUmComentario() {
		String user = "User";
		forumProxy.likeComment(user, "Foo Topic", "Bar Comment", "Baz User");
		
		verify(mockForumService, new Times(1)).likeComment(user, "Foo Topic", "Bar Comment", "Baz User");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(1, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.PARTICIPATION));
		assertEquals(1, ((Points) storage.getAchievement(user, Achievement.PARTICIPATION)).getValue());
	}
	
	@Test
	public void somaOsPontosEhAdicionaSomenteUmAchievementDeCadaAoCriarVariosTopicos() {
		String user = "User";
		
		forumProxy.addTopic(user, "New Topic Foo");
		forumProxy.addTopic(user, "New Topic Bar");
		
		verify(mockForumService, new Times(1)).addTopic(user, "New Topic Foo");
		verify(mockForumService, new Times(1)).addTopic(user, "New Topic Bar");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(2, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.CREATION));
		assertEquals(10, ((Points) storage.getAchievement(user, Achievement.CREATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.I_CAN_TALK));
	}
	
	@Test
	public void adicionaCorretamenteOsAchievementsAoRealizarVariasAcoes() {
		String user = "User";
		
		forumProxy.addTopic(user, "New Topic");
		forumProxy.addComment(user, "Topic Foo", "Comment Bar");
		forumProxy.likeTopic(user, "Topic Foo", "User Bar");
		forumProxy.likeComment(user, "Topic Foo", "Comment Bar", "User Baz");
		
		verify(mockForumService, new Times(1)).addTopic(user, "New Topic");
		verify(mockForumService, new Times(1)).addComment(user, "Topic Foo", "Comment Bar");
		verify(mockForumService, new Times(1)).likeTopic(user, "Topic Foo", "User Bar");
		verify(mockForumService, new Times(1)).likeComment(user, "Topic Foo", "Comment Bar", "User Baz");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(4, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.CREATION));
		assertEquals(6, ((Points) storage.getAchievement(user, Achievement.CREATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.PARTICIPATION));
		assertEquals(4, ((Points) storage.getAchievement(user, Achievement.PARTICIPATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.I_CAN_TALK));
		
		assertNotNull(storage.getAchievement(user, Achievement.LET_ME_ADD));
	}
	
	@Test
	public void naoAdicionaAchievementsAoOcorrerUmaExecaoAoAdicionarUmTopico() {
		String user = "User";
		doThrow(RuntimeException.class).when(mockForumService).addTopic(user, "New Topic");		
		
		forumProxy.addTopic(user, "New Topic");
		
		verify(mockForumService, new Times(1)).addTopic(user, "New Topic");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNull(achievements);
	}
	
	@Test
	public void naoAdicionaAchievementsAoOcorrerUmaExecaoAoAdicionarUmComentario() {
		String user = "User";
		doThrow(RuntimeException.class).when(mockForumService).addComment(user, "Topic Foo", "Comment Bar");		
		
		forumProxy.addComment(user, "Topic Foo", "Comment Bar");
		
		verify(mockForumService, new Times(1)).addComment(user, "Topic Foo", "Comment Bar");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNull(achievements);
	}
	
	@Test
	public void naoAdicionaAchievementsAoOcorrerUmaExecaoAoDarLikeEmUmTopico() {
		String user = "User";
		doThrow(RuntimeException.class).when(mockForumService).likeTopic(user, "Foo Topic", "Bar User");		
		
		forumProxy.likeTopic(user, "Foo Topic", "Bar User");
		
		verify(mockForumService, new Times(1)).likeTopic(user, "Foo Topic", "Bar User");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNull(achievements);
	}
	
	@Test
	public void naoAdicionaAchievementsAoOcorrerUmaExecaoAoDarLikeEmUmComentario() {
		String user = "User";
		doThrow(RuntimeException.class)
			.when(mockForumService)
			.likeComment(user, "Foo Topic", "Bar Comment", "Baz User");		
		
		forumProxy.likeComment(user, "Foo Topic", "Bar Comment", "Baz User");
		
		verify(mockForumService, new Times(1)).likeComment(user, "Foo Topic", "Bar Comment", "Baz User");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNull(achievements);
	}
	
	@Test
	public void aoAtingir100PontosDeCreationRecebeBadgeInventor() {
		String user = "User";
		
		for (int i = 0; i < 20; i++)
			forumProxy.addTopic(user, "New Topic");
		
		verify(mockForumService, new Times(20)).addTopic(user, "New Topic");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(3, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.CREATION));
		assertEquals(100, ((Points) storage.getAchievement(user, Achievement.CREATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.I_CAN_TALK));
		
		assertNotNull(storage.getAchievement(user, Achievement.INVENTOR));
	}
	
	@Test
	public void aoAtingir100PontosDeParticipationRecebeBadgePartOfTheCommunity() {
		String user = "User";
		
		for (int i = 0; i < 34; i++)
			forumProxy.addComment(user, "Topic Foo", "Comment Bar");
		
		verify(mockForumService, new Times(34)).addComment(user, "Topic Foo", "Comment Bar");
		
		List<Achievement> achievements = storage.getAchievements(user);
		
		assertNotNull(achievements);
		assertEquals(3, achievements.size());
		
		assertNotNull(storage.getAchievement(user, Achievement.PARTICIPATION));
		assertEquals(102, ((Points) storage.getAchievement(user, Achievement.PARTICIPATION)).getValue());
		
		assertNotNull(storage.getAchievement(user, Achievement.LET_ME_ADD));
		
		assertNotNull(storage.getAchievement(user, Achievement.PART_OF_THE_COMMUNITY));
	}
	
}
