package shift.mceconomy2.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import shift.mceconomy2.Config;
import shift.mceconomy2.EntityPropertieMP;
import shift.mceconomy2.api.MCEconomyAPI;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CommonEventManager {

	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event) {

		if (event.entity instanceof EntityPlayer) {
			event.entity.registerExtendedProperties(MPManager.STATUS,
					new EntityPropertieMP());
		}

	}

	@SubscribeEvent
	/* ワールドに入った時に呼ばれるイベント。 */
	public void onEntityJoinWorld(EntityJoinWorldEvent event) {

		if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

			MPManager.loadProxyData((EntityPlayer) event.entity);

		}
	}

	@SubscribeEvent
	public void onLivingDeath(LivingDeathEvent event) {

		// 別のMOD等でキャンセル済みの場合はなにもしない
		if (event.isCancelable() && event.isCanceled()) {
			return;
		}

		// 別のMOD等で不可とされた場合はなにもしない
		if (event.getResult() == Result.DENY) {
			return;
		}

		// なぜかダメージソースがnullだった場合はなにもしない
		if (event.source.getSourceOfDamage() == null || event.source.getEntity() == null) {
			return;
		}

		// ダメージソースがプレイヤーの場合はMP加算する
		if (event.source.getSourceOfDamage() instanceof EntityPlayerMP) {

			EntityPlayerMP entityPlayer = (EntityPlayerMP) event.source.getSourceOfDamage();

			if (MCEconomyAPI.ShopManager.hasEntityPurchase(event.entityLiving)) {
				MCEconomyAPI.addPlayerMP(entityPlayer, MCEconomyAPI.ShopManager.getEntityPurchase(event.entityLiving), false);
				if (Config.sound) entityPlayer.worldObj.playSoundAtEntity(entityPlayer, "mceconomy2:coin", 0.6f, 0.8f);
			}

		} else if (event.source.getEntity() instanceof EntityPlayerMP) {
			EntityPlayerMP entityPlayer = (EntityPlayerMP) event.source.getEntity();

			if (MCEconomyAPI.ShopManager.hasEntityPurchase(event.entityLiving)) {
				MCEconomyAPI.addPlayerMP(entityPlayer, MCEconomyAPI.ShopManager.getEntityPurchase(event.entityLiving), false);
				if (Config.sound) entityPlayer.worldObj.playSoundAtEntity(entityPlayer, "mceconomy2:coin", 0.6f, 0.8f);
			}
		}
	}

}
