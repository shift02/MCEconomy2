package shift.mceconomy2.api.event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import cpw.mods.fml.common.eventhandler.Event;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.shop.IShop;

public class ShopEvent extends Event {

    public final IShop shop;

    public ShopEvent(IShop shop) {
        this.shop = shop;
    }

    /**
     * ショップが登録される前に呼ばれるイベントです。<br>
     * このイベントをキャンセルすると、そのショップは登録されません。<br>
     * このイベントは、{@link MinecraftForge#EVENT_BUS}で呼ばれます。
     */
    @Cancelable
    public static class Register extends ShopEvent {

        public final String modId;

        public Register(String modId, IShop shop) {
            super(shop);
            this.modId = modId;
        }
    }

    /**
     * ショップが開かれる前に呼ばれるイベントです。<br>
     * このイベントをキャンセルすると、そのショップは開かれません。<br>
     * このイベントは、{@link MinecraftForge#EVENT_BUS}で呼ばれます。
     */
    @Cancelable
    public static class OpenShop extends ShopEvent {

        public final EntityPlayer player;
        public final String modId;
        public final int shopId;

        public OpenShop(EntityPlayer player, String modId, int shopId, IShop shop) {
            super(shop);
            this.player = player;
            this.modId = modId;
            this.shopId = shopId;
        }
    }
}