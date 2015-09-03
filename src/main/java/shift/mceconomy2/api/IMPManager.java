package shift.mceconomy2.api;

import net.minecraft.entity.player.EntityPlayer;

/**
 * MPを管理するClass
 * @since 1.0.0
 * @version 2.0.0
 * @author Shift02
 */
public interface IMPManager {

	//public static final Minecraft mc = FMLClientHandler.instance().getClient();

	public int addPlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation);

	public int reducePlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation);

	public void setPlayerMP(EntityPlayer entityPlayer, int amount);

	public int getPlayerMP(EntityPlayer entityPlayer);

	//public void printChatMPMessage(EntityPlayer entityPlayer);

	//public void spawnWorldMP(World world, int x, int y, int z, int amount);

}
