package model;

public class Palavra {
	
	private String ingles;
	private String portugues;

	public Palavra(String ingles, String portugues) {
		this.ingles = ingles;
		this.portugues = portugues;
	}
	
	public String getIngles() {
		return ingles;
	}
	public String getPortugues() {
		return portugues;
	}

}
