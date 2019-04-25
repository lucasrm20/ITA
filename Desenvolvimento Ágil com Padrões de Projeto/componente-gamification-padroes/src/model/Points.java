package model;

import java.util.List;

public class Points extends Achievement {

	private int value;
	
	public Points(String name, int value) {
		super(name);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public void addAchievement(List<Achievement> achievements) {
		if (this.isPresentIn(achievements)) {
			Points old = (Points) achievements.get(achievements.indexOf(this));
			old.setValue(old.getValue() + this.value);
			
			this.value = old.getValue();
		} else {			
			achievements.add(this);
		}
	}
	
}
