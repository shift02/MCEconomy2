package shift.mceconomy2.api.shop;

import java.util.ArrayList;

/**
 * ProductList 製品リスト作成用のClass <br>
 * 特別な事がない限りこのClassを継承してください
 * @since 1.1.0
 * @version 1.2.0
 * @author Shift02
 */
public abstract class ProductList implements IProductList {

	private ArrayList<IProductItem> ProductItemList = new ArrayList<IProductItem>();

	@Override
	public void addItemProduct(IProductItem item) {
		ProductItemList.add(item);
	}

	@Override
	public int getItemProductSize() {
		return ProductItemList.size();
	}

	@Override
	public ArrayList<IProductItem> getProductList() {
		return ProductItemList;
	}

}
