package shift.mceconomy2;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import shift.mceconomy2.api.MCEconomyAPI;
import shift.mceconomy2.api.shop.IProductList;
import shift.mceconomy2.gui.ContainerShop;
import shift.mceconomy2.gui.GuiShop;
import cpw.mods.fml.common.network.IGuiHandler;

public class MCEGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {

		IProductList p = MCEconomyAPI.ShopManager.getProductList(ID);

		if(p!=null){
			return new ContainerShop(player.inventory, p,world);
		}
		return null;



	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,int x, int y, int z) {

		IProductList p = MCEconomyAPI.ShopManager.getProductList(ID);

		if(p!=null){
			return new GuiShop(player.inventory, p, ID, world);
		}
		return null;

	}

}
