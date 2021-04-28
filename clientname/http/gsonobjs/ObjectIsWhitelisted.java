package clientname.http.gsonobjs;

public class ObjectIsWhitelisted {

	private String hwid;
	
	private int isWhitelisted;
	
	public String getHwid() {
		return hwid;
	}
	
	public boolean isWhitelisted() {
		return isWhitelisted == 1;
	}
	
}
