package shift.mceconomy2.api.shop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class ShopBuyEvent extends PlayerEvent{

	public ItemStack buy;

	public ShopBuyEvent(EntityPlayer player, ItemStack buy) {
		super(player);
		this.buy = buy;
	}

}
