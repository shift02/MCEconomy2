package shift.mceconomy2.api.shop;

import net.minecraft.item.ItemStack;

public class ProductItem implements IProductItem {

	ItemStack item;
	int cost;

	public ProductItem(ItemStack item, int cost) {
		this.item = item;
		this.cost = cost;
	}

	@Override
	public ItemStack getProductItem() {
		return this.item.copy();
	}

	@Override
	public int getcost() {
		return this.cost;
	}

}
