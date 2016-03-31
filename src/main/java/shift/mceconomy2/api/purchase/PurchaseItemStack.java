package shift.mceconomy2.api.purchase;

import net.minecraft.item.ItemStack;

/**
 * Created by plusplus_F on 2016/03/29.
 * 前のバージョンとの互換性を保つためのクラス
 */
public class PurchaseItemStack implements IPurchaseItem {

    /**
     * このクラスが保持するアイテム
     */
    protected ItemStack itemStack;

    /**
     * 売却価格
     */
    protected int price;

    public PurchaseItemStack(ItemStack itemStack, int price) {
        this.itemStack = itemStack;
        this.price = price;
    }

    @Override
    public boolean isMatch(ItemStack itemStack) {
        return this.itemStack.getItem() == itemStack.getItem() && (this.itemStack.getItemDamage() == 32767 || this.itemStack.getItemDamage() == itemStack.getItemDamage());
    }

    @Override
    public int getPrice(ItemStack itemStack) {
        return price;
    }

    @Override
    public int getPriority() {
        return 5;
    }
}
