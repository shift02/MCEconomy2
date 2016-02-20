package shift.mceconomy2.item;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MCEItems {

    public static Item MPWallet;

    public static Item mp;

    public static void initItem() {

        //MPWallet = new ItemMPWallet(100).setUnlocalizedName("mp_wallet").setCreativeTab(CreativeTabs.tabFood);
        //GameRegistry.registerItem(MPWallet, "MPWallet");

        mp = new ItemMP().setTextureName("mceconomy2:mp_coin").setUnlocalizedName("mp").setCreativeTab(CreativeTabs.tabFood);
        GameRegistry.registerItem(mp, "MP");

    }

}
