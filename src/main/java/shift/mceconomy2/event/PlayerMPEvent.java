package shift.mceconomy2.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

//TODO
public class PlayerMPEvent extends PlayerEvent {

	public PlayerMPEvent(EntityPlayer player, int changeMP, MoneySource source) {
		super(player);
	}

	public class MoneySource {

	}

	public class MoneySourceMobKill extends MoneySource {

	}

}
