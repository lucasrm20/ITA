package model;

import java.util.List;

public abstract class Achievement {
	
	public static final String CREATION = "CREATION";
	public static final String I_CAN_TALK = "I CAN TALK";
	public static final String PARTICIPATION = "PARTICIPATION";
	public static final String LET_ME_ADD = "LET ME ADD";
	public static final String INVENTOR = "INVENTOR";
	public static final String PART_OF_THE_COMMUNITY = "PART OF THE COMMUNITY";

	protected String name;
	
	public Achievement(String name) {
		this.name = name;
	}
	
	public abstract void addAchievement(List<Achievement> achievements);
	
	protected boolean isPresentIn(List<Achievement> achievements) {
		if (achievements.contains(this))
			return true;
		
		return achievements
			.stream()
			.anyMatch(a -> a.getName().equals(this.name));
	}
	

	public String getName() {
		return name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Achievement other = (Achievement) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
