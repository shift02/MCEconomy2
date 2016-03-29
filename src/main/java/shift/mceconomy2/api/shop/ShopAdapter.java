package shift.mceconomy2.api.shop;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

/**
 *
 * 後方互換用のクラス
 * @author Shift02
 *
 */
public class ShopAdapter implements IShop {

    private IProductList productList;
    private ArrayList<IProduct> list;

    public ShopAdapter(IProductList productList) {
        this.productList = productList;

        this.list = new ArrayList<IProduct>();

        for (int i = 0; i < this.productList.getProductList().size(); i++) {
            this.list.add(new ProductAdapter(this.productList.getProductList().get(i)));
        }

    }

    @Override
    public String getShopName(World world, EntityPlayer player) {
        return this.productList.getProductListName();
    }

    @Override
    public void addProduct(IProduct product) {

    }

    @Override
    public ArrayList<IProduct> getProductList(World world, EntityPlayer player) {

        if (this.list.size() == 0) {
            for (int i = 0; i < this.productList.getProductList().size(); i++) {
                this.list.add(new ProductAdapter(this.productList.getProductList().get(i)));
            }
        }

        return this.list;
    }

}
