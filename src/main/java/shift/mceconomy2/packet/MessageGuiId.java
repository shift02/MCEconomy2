package shift.mceconomy2.packet;

import net.minecraft.entity.player.EntityPlayerMP;
import shift.mceconomy2.MCEconomy2;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiId implements IMessageHandler<PacketGuiId, IMessage>{

	@Override
	public IMessage onMessage(PacketGuiId message, MessageContext ctx) {

		EntityPlayerMP p = ctx.getServerHandler().playerEntity;

		int i = message.getData().getInteger("gui");

		p.openGui(MCEconomy2.instance, i, p.worldObj, (int)p.posX, (int)p.posY, (int)p.posZ);

		return null;

	}
}
