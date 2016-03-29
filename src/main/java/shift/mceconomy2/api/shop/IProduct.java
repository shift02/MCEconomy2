package shift.mceconomy2.api.shop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 商品データクラス <br>
 * {@link IShop}で使用する
 *
 * @version 1.0.0
 * @author Shift02
 *
 */
public interface IProduct {

    /**
     * 商品自体
     * @return ItemStack
     */
    public ItemStack getItem(IShop shop, World world, EntityPlayer player);

    /**
     * 商品の値段 <br>
     * プレイヤーによって値段を変更したり出来る
     * @param player 購入しようとしているプレイヤー
     * @return 値段
     */
    public int getCost(IShop shop, World world, EntityPlayer player);

    /**
     * 商品を買えるかどうか
     * @param player 購入しようとしているプレイヤー
     * @return trueの時は購入できる
     */
    public boolean canBuy(IShop shop, World world, EntityPlayer player);

}
