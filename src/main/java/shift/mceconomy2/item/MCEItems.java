package shift.mceconomy2.item;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class MCEItems {

	public static Item MPWallet;

	public static void initItem(){

		//MPWallet = new ItemMPWallet(100).setUnlocalizedName("mp_wallet").setCreativeTab(CreativeTabs.tabFood);
		GameRegistry.registerItem(MPWallet, "MPWallet");

	}

}
