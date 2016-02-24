package shift.mceconomy2.shop;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.shop.IProductItem;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.api.shop.ProductEvent;
import shift.mceconomy2.gui.ContainerShop;

/**
 * 非推奨 (*´ω｀*) <br>
 * 今までのIProductListをキープしつつEventを挟むためのラッパークラス
 *
 * @see ContainerShop
 * @author Shift02
 */
@Deprecated
public class VariableProductList implements IProductList {

    IProductList productList;
    ArrayList<IProductItem> products;

    public VariableProductList(EntityPlayer player, IProductList productList) {

        ProductEvent event = new ProductEvent(player, productList);
        MinecraftForge.EVENT_BUS.post(event);

        this.productList = productList;
        this.products = event.productList;
    }

    @Override
    public String getProductListName() {
        return this.productList.getProductListName();
    }

    @Override
    public void addItemProduct(IProductItem item) {
        this.productList.addItemProduct(item);
    }

    @Override
    public int getItemProductSize() {
        return this.productList.getItemProductSize();
    }

    @Override
    public ArrayList<IProductItem> getProductList() {
        return this.products;
    }

}
