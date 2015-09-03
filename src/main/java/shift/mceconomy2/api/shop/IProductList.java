package shift.mceconomy2.api.shop;

import java.util.ArrayList;

public interface IProductList {

	public String getProductListName();

	public void addItemProduct(IProductItem item);

	public int getItemProductSize();

	public ArrayList<IProductItem> getProductList();

}
