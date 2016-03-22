package shift.mceconomy2.shop;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cpw.mods.fml.common.FMLLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.MinecraftForge;
import shift.mceconomy2.api.event.ShopEvent;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.IShopAPI;
import shift.mceconomy2.gui.ContainerShop;
import shift.mceconomy2.packet.PacketHandler;
import shift.mceconomy2.packet.PacketOpenShop;

public class ShopAPIHandler implements IShopAPI {

    private final ConcurrentMap<String, List<IShop>> SHOP_MAP = Maps.newConcurrentMap();

    @Override
    public int registerShop(String modid, IShop shop) {

        ShopEvent.Register event = new ShopEvent.Register(modid, shop);

        if (MinecraftForge.EVENT_BUS.post(event)) {

            return -1;
        }

        List<IShop> shops = SHOP_MAP.get(modid);

        if (shops == null) {

            shops = Lists.newArrayList();
        }

        if (shops.add(shop)) {

            SHOP_MAP.putIfAbsent(modid, shops);

            return shops.indexOf(shop);
        }

        return -1;
    }

    @Override
    public IShop getShop(String modid, int id) {

        List<IShop> shops = SHOP_MAP.get(modid);

        if (shops == null) {

            FMLLog.warning("MCEconomy: Shop list does not exist!");

            return null;
        }

        if (id < 0 || id >= shops.size()) {

            FMLLog.warning("MCEconomy: This shop id is invalid!");

            return null;
        }

        IShop shop = shops.get(id);

        if (shop == null) {

            FMLLog.warning("MCEconomy: Shop does not exist!");
        }

        return shop;
    }

    @Override
    public List<IShop> getShops(String modid) {

        List<IShop> shops = SHOP_MAP.get(modid);

        if (shops == null) {

            return Collections.emptyList();
        }

        return shops;
    }

    @Override
    public Map<String, List<IShop>> getShopEntries() {

        return SHOP_MAP;
    }

    @Override
    public void openShop(EntityPlayer player, String modid, int id) {

        ShopEvent.OpenShop event = new ShopEvent.OpenShop(player, modid, id, getShop(modid, id));

        if (MinecraftForge.EVENT_BUS.post(event)) {

            return;
        }

        if (player.worldObj.isRemote) {

            PacketHandler.INSTANCE.sendToServer(new PacketOpenShop(modid, id));
        }
        else if (player instanceof EntityPlayerMP) {

            EntityPlayerMP thePlayer = (EntityPlayerMP)player;
            ContainerShop container = new ContainerShop(thePlayer, getShop(modid, id), thePlayer.worldObj);

            thePlayer.getNextWindowId();
            thePlayer.closeContainer();
            int windowId = thePlayer.currentWindowId;
            PacketHandler.INSTANCE.sendTo(new PacketOpenShop(modid, id), thePlayer);
            thePlayer.openContainer = container;
            thePlayer.openContainer.windowId = windowId;
            thePlayer.openContainer.addCraftingToCrafters(thePlayer);
        }
    }
}