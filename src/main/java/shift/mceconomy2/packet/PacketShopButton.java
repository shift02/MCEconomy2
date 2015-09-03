package shift.mceconomy2.packet;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class PacketShopButton implements IMessage {

	private int currentRecipeIndex;

	public PacketShopButton()
	{
	}

	/*
	 * パケットの生成には通常こちらを用いる.
	 */
	public PacketShopButton(int currentRecipeIndex)
	{
		this.currentRecipeIndex = currentRecipeIndex;
	}

	@Override
	public void fromBytes(ByteBuf buf) {

		/*DataInputStream datainputstream = new DataInputStream(new ByteArrayInputStream(buf.array()));

        try {
        	currentRecipeIndex = datainputstream.readInt();
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		currentRecipeIndex = buf.readInt();


	}

	@Override
	public void toBytes(ByteBuf buf) {

		buf.writeInt(currentRecipeIndex);

	}

	public int getCurrentRecipeIndex() {
		return currentRecipeIndex;
	}

	public void setCurrentRecipeIndex(int currentRecipeIndex) {
		this.currentRecipeIndex = currentRecipeIndex;
	}

}
