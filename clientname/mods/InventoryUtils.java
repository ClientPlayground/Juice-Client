package clientname.mods;

import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class InventoryUtils {

    public static int getPotionsFromInventory() {
        int count = 0;

        for (int i = 1; i < 45; ++i) {
            if (Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getHasStack()) {
                ItemStack is = Minecraft.getMinecraft().thePlayer.inventoryContainer.getSlot(i).getStack();
                Item item = is.getItem();

                if (item instanceof ItemPotion) {
                    ItemPotion potion = (ItemPotion) item;

                    if (potion.getEffects(is) != null) {
                        Iterator iterator = potion.getEffects(is).iterator();

                        while (iterator.hasNext()) {
                            Object o = iterator.next();
                            PotionEffect effect = (PotionEffect) o;

                            if (effect.getPotionID() == Potion.heal.id) {
                                ++count;
                            }
                        }
                    }
                }
            }
        }

        return count;
    }
}