package clientname.cosmetics;

import clientname.http.HTTPFunctions;
import clientname.http.gsonobjs.ObjectUserCosmetics;
import net.minecraft.client.entity.AbstractClientPlayer;

public class CosmeticController {
	
	private static ObjectUserCosmetics[] userCosmetics;
	
	public static boolean shouldRenderTopHat(AbstractClientPlayer player) {
		ObjectUserCosmetics uc = getCosmetics(player);
		if(uc == null) {
			return false;
		}
		return uc.getHat().isEnabled();
	} 
	
	public static float[] getTopHatColor(AbstractClientPlayer player) {
		
		ObjectUserCosmetics uc = getCosmetics(player);
		if(uc == null) {
			return new float[] {0, 0, 0};
		}
		return uc.getHat().getColor();
	}
	
	public static boolean shouldRenderEyes(AbstractClientPlayer player) {
		ObjectUserCosmetics uc = getCosmetics(player);
		if(uc == null) {
			return false;
		}
		return uc.isGooglyEyesEnabled();
	}
	
	public static boolean shouldRenderCape(AbstractClientPlayer player) {
		ObjectUserCosmetics uc = getCosmetics(player);
		if(uc == null) {
			return false;
		}
		return uc.hasCape();
	}
	
	public static int monkiesCape(AbstractClientPlayer player) {
		ObjectUserCosmetics uc = getCosmetics(player);
		if(uc == null) {
			return 0;
		}
		return uc.getCape();
	}

	private static ObjectUserCosmetics getCosmetics(AbstractClientPlayer player) {
		for(ObjectUserCosmetics uc : userCosmetics) {
			if(player.getGameProfile().getId().equals(uc.getUuid())) {
				return uc;
			}
		}
		return null;
	}
	
	public static void downloadUserCosmetics() {
		userCosmetics = HTTPFunctions.downloadUserCosmetics();
	}
	
}
