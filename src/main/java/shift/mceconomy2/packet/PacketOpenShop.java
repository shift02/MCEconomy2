package shift.mceconomy2.packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import shift.mceconomy2.api.shop.IShop;
import shift.mceconomy2.api.shop.ShopAPI;

public class PacketOpenShop implements IMessage {

    private String modid;
    private int id;

    public PacketOpenShop() {}

    public PacketOpenShop(String modid, int id) {

        this.modid = modid;
        this.id = id;
    }

    @Override
    public void fromBytes(ByteBuf buf) {

        modid = ByteBufUtils.readUTF8String(buf);
        id = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {

        ByteBufUtils.writeUTF8String(buf, modid);
        buf.writeInt(id);
    }

    public String getModID() {

        return modid;
    }

    public int getShopID() {

        return id;
    }

    public IShop getShop() {

        return ShopAPI.getShop(modid, id);
    }
}