package clientname.http.gsonobjs;

public class ObjectIsBanned {

	private String hwid;
	
	private int isBanned;
	
	public String getHwid() {
		return hwid;
	}
	
	public boolean isBanned() {
		return isBanned == 1;
	}

}
