package shift.mceconomy2;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.gui.ContainerShop;
import shift.mceconomy2.gui.GuiShop;

public class MCEGuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        IShop shop = MCEconomyAPI.ShopManager.getShop(ID);

        if (shop != null) {

            //Event用ラッパークラスに差し替え
            //VariableProductList list = new VariableProductList(player, p);

            return new ContainerShop(player, shop, world);
        }
        return null;

    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {

        IShop shop = MCEconomyAPI.ShopManager.getShop(ID);

        if (shop != null) {

            //Event用ラッパークラスに差し替え
            //VariableProductList list = new VariableProductList(player, p);

            return new GuiShop(player, shop, ID, world);
        }
        return null;

    }

}
