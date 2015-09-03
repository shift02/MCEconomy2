package shift.mceconomy2.packet;

import net.minecraft.inventory.Container;
import shift.mceconomy2.gui.ContainerShop;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ShopButtonHandler implements IMessageHandler<PacketShopButton, IMessage>{

	@Override
	public IMessage onMessage(PacketShopButton message, MessageContext ctx) {

		Container container = ctx.getServerHandler().playerEntity.openContainer;

    	if (container instanceof ContainerShop)
        {
            ((ContainerShop)container).setCurrentRecipeIndex(message.getCurrentRecipeIndex());
        }

		return null;
	}

}
