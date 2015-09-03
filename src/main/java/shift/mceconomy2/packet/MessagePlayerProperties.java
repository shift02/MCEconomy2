package shift.mceconomy2.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import shift.mceconomy2.MCEconomy2;
import shift.mceconomy2.event.MPManager;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MessagePlayerProperties implements IMessage, IMessageHandler<MessagePlayerProperties, IMessage>{

	private NBTTagCompound data;

    public MessagePlayerProperties(){}

    public MessagePlayerProperties(EntityPlayer entityPlayer) {
    	this(entityPlayer,false);
    }

    public MessagePlayerProperties(EntityPlayer entityPlayer,boolean init) {
        this.data = new NBTTagCompound();
        data.setBoolean("init", init);
        MPManager.getEntityPropertieMP(entityPlayer).saveNBTData(data);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        data = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, data);
    }

    @Override
    public IMessage onMessage(MessagePlayerProperties message, MessageContext ctx) {
        //Client側にIExtendedEntityPropertiesを渡す。
    	MPManager.getEntityPropertieMP(MCEconomy2.proxy.getClientPlayer()).loadNBTData(message.data);
        //REPLYは送らないので、nullを返す。
        return null;
    }

}
