package clientname.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.google.gson.Gson;

import clientname.http.gsonobjs.ObjectAPIDown;
import clientname.http.gsonobjs.ObjectGlobalSettings;
import clientname.http.gsonobjs.ObjectIsBanned;
import clientname.http.gsonobjs.ObjectIsJuiceClient;
import clientname.http.gsonobjs.ObjectIsWhitelisted;
import clientname.http.gsonobjs.ObjectUserCosmetics;
import net.minecraft.client.Minecraft;

public class HTTPFunctions {

	private static final Gson gson = new Gson();
	
	public static void sendHWIDmap() {
		Minecraft mc = Minecraft.getMinecraft();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", mc.getSession().getProfile().getId().toString()));
		params.add(new BasicNameValuePair("username", mc.getSession().getProfile().getName()));
		params.add(new BasicNameValuePair("hwid", HWID.get()));
		HTTPUtils.sendPostAsync(HttpEndpoints.MAP_UUID, params);
	}
	
	public static boolean isAPIUp() {		
		HTTPReply reply = HTTPUtils.sendGet(HttpEndpoints.BASE);
		
		if(reply.getStatusCode() == 200) {
			return true;
		}
		
		return false;
	}
	
	public static boolean isBanned() {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("hwid", HWID.get()));
		HTTPReply reply = HTTPUtils.sendGet(HttpEndpoints.IS_BANNED, params);
		
		if(reply.getStatusCode() == 200) {
			ObjectIsBanned obj = gson.fromJson(reply.getBody(), ObjectIsBanned.class);
			return obj.isBanned();
		}
		
		return false;
	}
	
	public static boolean isJuiceClient() {
		
		Minecraft mc = Minecraft.getMinecraft();
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("uuid", mc.getSession().getProfile().getId().toString()));
		HTTPReply reply = HTTPUtils.sendGet(HttpEndpoints.MAP_UUID, params);
		
		if(reply.getStatusCode() == 200) {
			ObjectIsJuiceClient obj = gson.fromJson(reply.getBody(), ObjectIsJuiceClient.class);
			return obj.isJuiceClient();
		}
		return false;
	}
	
	public static boolean isWhitelisted() {
		
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("hwid", HWID.get()));
		HTTPReply reply = HTTPUtils.sendGet(HttpEndpoints.IS_WHITELISTED, params);
		
		if(reply.getStatusCode() == 200) {
			ObjectIsWhitelisted obj = gson.fromJson(reply.getBody(), ObjectIsWhitelisted.class);
			return obj.isWhitelisted();
		}
		
		return false;
	}
	
	public static ObjectUserCosmetics[] downloadUserCosmetics() {
		return gson.fromJson(HTTPUtils.sendGet(HttpEndpoints.COSMETICS).getBody(), ObjectUserCosmetics[].class);
	}
	public static ObjectGlobalSettings downloadGlobalSettings() {
		 return gson.fromJson(HTTPUtils.sendGet(HttpEndpoints.GLOBAL_SETTINGS).getBody(), ObjectGlobalSettings.class);
	}
	
}
