package clientname.event.impl;

import clientname.event.Event;
import net.minecraft.item.ItemStack;

public class ItemPickupEvent extends Event {

	public final ItemStack item;
	public final int stackSize;
	
	public ItemPickupEvent(ItemStack item, int stackSize) {
		this.item = item;
		this.stackSize = stackSize;
	}
}