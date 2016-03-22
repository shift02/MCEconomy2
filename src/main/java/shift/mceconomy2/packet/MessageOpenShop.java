package shift.mceconomy2.packet;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import shift.mceconomy2.MCEconomy2;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.gui.ContainerShop;
import shift.mceconomy2.gui.GuiNewShop;

public class MessageOpenShop implements IMessageHandler<PacketOpenShop, IMessage> {

    @Override
    public IMessage onMessage(PacketOpenShop message, MessageContext ctx) {

        IShop shop = message.getShop();

        if (shop == null) {

            return null;
        }

        if (ctx.side.isServer()) {

            EntityPlayerMP player = ctx.getServerHandler().playerEntity;
            ContainerShop container = new ContainerShop(player, shop, player.worldObj);

            player.getNextWindowId();
            player.closeContainer();
            int windowId = player.currentWindowId;
            PacketHandler.INSTANCE.sendTo(new PacketOpenShop(message.getModID(), message.getShopID()), player);
            player.openContainer = container;
            player.openContainer.windowId = windowId;
            player.openContainer.addCraftingToCrafters(player);
        }
        else {

            EntityPlayer player = MCEconomy2.proxy.getClientPlayer();

            FMLCommonHandler.instance().showGuiScreen(new GuiNewShop(player, shop, message.getShopID(), player.worldObj));
        }

        return null;
    }
}