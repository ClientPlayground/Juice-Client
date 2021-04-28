package clientname.http.gsonobjs;

import java.util.UUID;

public class ObjectUserCosmetics {

	private String uuid;
	
	public String cape_style;
	private int cape;
	private int googly_eyes;
	private int cape_number;
	private Hat hat;
	private int cape_enabled;
	private String Monkies;
	
	public class Hat {
		
		private int enabled;
		private int r;
		private int g;
		private int b;
		
		public boolean isEnabled() {
			return enabled == 1;
		}
		
		public float[] getColor() {
			return new float[] {convert(r), convert(g), convert(b)};
		}
		
		private float convert(int color) {
			return ( color / 255F );
		}
		
	}
	
	public class Cape {
		
		private int monkiesCape;// haha monkies
		private String getCape;
		
		public boolean isEnabled() {
			return ObjectUserCosmetics.this.cape_style != null;
		}
		
		public String getCape() {
			return cape_style;
		}
		
	}
	
	public Hat getHat() {
		return hat;
	}
	
	//public static boolean isCapeEnabled() {
		//return cape_enabled == 1;
	//}
	
	public UUID getUuid() {
		return UUID.fromString(uuid);
	}
	
	public boolean isGooglyEyesEnabled() {
		return googly_eyes == 1;
	}
	
	public boolean hasCape() {
		return cape_style != null;
	}
	
	public String monkiesCape() {
		return cape_style = "monkies";
	}
	
	public String getCape_style() {
		return cape_style;
	}

	public int getCape() {
		return cape_number;
	}
	
}
