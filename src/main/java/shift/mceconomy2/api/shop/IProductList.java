package shift.mceconomy2.api.shop;

import java.util.ArrayList;

/**
 *
 * 非推奨 <br>
 * {@link IShop} を使ってください
 *
 * @author Shift02
 * @see IShop
 *
 */
@Deprecated
public interface IProductList {

    public String getProductListName();

    public void addItemProduct(IProductItem item);

    public int getItemProductSize();

    public ArrayList<IProductItem> getProductList();

}
