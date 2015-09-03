package shift.mceconomy2.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import shift.mceconomy2.Config;
import shift.mceconomy2.EntityPropertieMP;
import shift.mceconomy2.api.IMPManager;
import shift.mceconomy2.packet.MessagePlayerProperties;
import shift.mceconomy2.packet.PacketHandler;

public class MPManager implements IMPManager{

	private static final MPManager instance = new MPManager();

	public static final String STATUS = "mp";

	private MPManager(){

	}

	public static MPManager getInstance(){
		 return instance;
	}

	public static EntityPropertieMP getEntityPropertieMP(EntityPlayer p){

		return (EntityPropertieMP) p.getExtendedProperties(STATUS);

	}

	@Override
	public int addPlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation) {

		if(amount<0||getEntityPropertieMP(entityPlayer)==null){
			return 0;
		}

		if(entityPlayer instanceof EntityPlayerMP && !entityPlayer.worldObj.isRemote){


			EntityPropertieMP p = getEntityPropertieMP(entityPlayer);

			int add = 0;
			if(amount+p.getMp()>Config.maxMP){

				add = Config.maxMP-p.getMp();
				if(!simulation)p.setMp(Config.maxMP);

			}else{

				add = amount;
				if(!simulation)p.setMp(p.getMp()+amount);

			}

			sendPacket(entityPlayer);

			return add;

		}

		return 0;


	}


	@Override
	public int reducePlayerMP(EntityPlayer entityPlayer, int amount, boolean simulation) {

		if(amount<0||getEntityPropertieMP(entityPlayer)==null){
			return 0;
		}

		if(entityPlayer instanceof EntityPlayerMP && !entityPlayer.worldObj.isRemote){


			EntityPropertieMP p = getEntityPropertieMP(entityPlayer);

			int reduce = 0;

			if(getPlayerMP(entityPlayer)>=amount){

				reduce = amount;

				if(!simulation)setPlayerMP(entityPlayer, getPlayerMP(entityPlayer)-reduce);

			}else{

				reduce = getPlayerMP(entityPlayer);

				if(!simulation)setPlayerMP(entityPlayer,0);

			}

			sendPacket(entityPlayer);

			return reduce;



		}

		return 0;
	}

	@Override
	public void setPlayerMP(EntityPlayer entityPlayer, int amount) {
		getEntityPropertieMP(entityPlayer).setMp(Math.max(0,Math.min(amount, Config.maxMP)));
		sendPacket(entityPlayer);
	}

	@Override
	public int getPlayerMP(EntityPlayer entityPlayer) {
		return getEntityPropertieMP(entityPlayer).getMp();
	}


	public static void loadProxyData(EntityPlayer player) {
        PacketHandler.INSTANCE.sendTo(new MessagePlayerProperties(player,true), (EntityPlayerMP)player);
    }

	public static void sendPacket(EntityPlayer player) {
        PacketHandler.INSTANCE.sendTo(new MessagePlayerProperties(player), (EntityPlayerMP)player);
    }



}
