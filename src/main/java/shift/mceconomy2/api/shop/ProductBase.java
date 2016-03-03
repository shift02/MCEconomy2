/*
* 作成者: Shift02
* 作成日: 2016/03/03 - 23:30:43
*/
package shift.mceconomy2.api.shop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ProductBase implements IProduct {

    protected ItemStack item;
    protected int cost;

    public ProductBase(ItemStack item, int cost) {
        this.item = item;
        this.cost = cost;
    }

    @Override
    public ItemStack getItem(IShop shop, World world, EntityPlayer player) {
        return this.item;
    }

    @Override
    public int getCost(IShop shop, World world, EntityPlayer player) {
        return this.cost;
    }

    @Override
    public boolean canBuy(IShop shop, World world, EntityPlayer player) {
        return true;
    }

}
