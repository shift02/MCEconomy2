package shift.mceconomy2.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;

public class ItemMP extends Item {

    @Override
    public boolean onItemUse(ItemStack p_77648_1_, EntityPlayer p_77648_2_, World p_77648_3_, int p_77648_4_, int p_77648_5_, int p_77648_6_, int side, float p_77648_8_, float p_77648_9_, float p_77648_10_) {

        MCEconomyAPI.addPlayerMP(p_77648_2_, 100000, false);

        return true;

    }

}
