package shift.mceconomy2.api.purchase;

import net.minecraft.item.ItemStack;

/**
 * Created by plusplus_F on 2016/03/29.
 * アイテムの値段設定を処理するクラス <br>
 * このクラスによって基本の価格が決定された後にPriceEventが発生する
 */
public interface IPurchaseItem {
    /**
     * 引数のItemStackがこのインスタンスで扱っているかを返す
     * @param itemStack
     * @return trueの場合、このクラスで処理できる
     */
    public boolean isMatch(ItemStack itemStack);

    /**
     * 引数のItemStackの売却価格を返す
     * @param itemStack
     * @return 売却価格
     */
    public int getPrice(ItemStack itemStack);
}
