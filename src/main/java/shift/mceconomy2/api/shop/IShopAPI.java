package shift.mceconomy2.api.shop;

import java.util.List;
import java.util.Map;

import net.minecraft.entity.player.EntityPlayer;

public interface IShopAPI {

    public int registerShop(String modid, IShop shop);

    public IShop getShop(String modid, int id);

    public List<IShop> getShops(String modid);

    public Map<String, List<IShop>> getShopEntries();

    public void openShop(EntityPlayer player, String modid, int id);
}