package garbage.collector;

public class Garbage {
	
	// Prevent the hardcoding.
	public static final String TRASH_TYPE_PLASTIC = "plastic";
	public static final String TRASH_TYPE_ORGANIC = "organic";
	public static final String TRASH_TYPE_GLASS = "glass";
	public static final String TRASH_TYPE_FABRIC = "fabric";
	public static final String TRASH_TYPE_METAL = "metal";
	public static final String TRASH_TYPE_PAPER = "paper";
	
	private String name;
	public String type;
	
	public Garbage(String name, String type) {
		this.type = type;
		this.name = name;
	}
	
	public String toString() {	
		return name;
	}
	
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj.getClass() == this.getClass())
			result = toString() == ((Garbage)obj).toString(); 
		return result;
	}
}