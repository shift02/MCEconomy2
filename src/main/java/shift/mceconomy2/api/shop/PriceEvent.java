package shift.mceconomy2.api.shop;

import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.item.ItemStack;

/**
 * 非推奨 eventパッケージに移動しました
 * アイテム価値のEvent
 * -1に設定すれば非売品にも出来ます
 * @author Shift02
 */
@Deprecated
public class PriceEvent extends Event {

    private final ItemStack item;
    private final int oldPrice;
    private int newPrice;

    public PriceEvent(ItemStack item, int oldValue) {
        this.item = item;
        this.oldPrice = oldValue;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public int getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(int newPrice) {
        this.newPrice = newPrice;
    }

    public ItemStack getPriceItem() {
        return this.item;
    }

}
