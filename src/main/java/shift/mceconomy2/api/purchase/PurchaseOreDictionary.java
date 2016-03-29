package shift.mceconomy2.api.purchase;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

/**
 * Created by plusplus_F on 2016/03/29.
 */
public class PurchaseOreDictionary implements IPurchaseItem {
    /**
     * このクラスが扱う鉱石辞書ID
     */
    protected int oreId;

    /**
     * 売却価格
     */
    protected int price;

    public PurchaseOreDictionary(String oreName, int price){
        oreId= OreDictionary.getOreID(oreName);
        this.price=price;
    }

    @Override
    public boolean isMatch(ItemStack itemStack) {
        int[] ids=OreDictionary.getOreIDs(itemStack);
        for(int i : ids){
            if(oreId==i) return true;
        }
        return false;
    }

    @Override
    public int getPrice(ItemStack itemStack) {
        return price;
    }
}
