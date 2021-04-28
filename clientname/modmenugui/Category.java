package clientname.modmenugui;

public enum Category {
	
	HUD("HUD", 130),
	WORLD("World", 130 * 2),
	PLAYER("Player", 130 * 3),
	/*COSMETIC("Cosmetic", 200 * 4)*/;
	
	
	public String name;
	public int pos;
	Category(String name, int pos) {
		this.name = name;
		this.pos = pos;
	}

}
