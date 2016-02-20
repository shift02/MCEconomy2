package shift.mceconomy2.api.shop;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

/**
 * 後方互換用のクラス
 * @author Shift02
 *
 */
public class ProductAdapter implements IProduct {

    private IProductItem productItem;

    public ProductAdapter(IProductItem productItem) {
        this.productItem = productItem;
    }

    @Override
    public ItemStack getItem(IShop shop, World world, EntityPlayer player) {
        return this.productItem.getProductItem();
    }

    @Override
    public int getCost(IShop shop, World world, EntityPlayer player) {
        return this.productItem.getcost();
    }

    @Override
    public boolean canBuy(IShop shop, World world, EntityPlayer player) {
        return true;
    }

}
