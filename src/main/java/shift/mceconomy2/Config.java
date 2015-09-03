package shift.mceconomy2;

import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Config {

	public static boolean HUD;

	public static int maxMP;

	public static boolean sound;

	public static void ConfigRead(FMLPreInitializationEvent event) {

		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());

		try {

			cfg.load();

			ConfigForMCEconomy(cfg);

		} catch (Exception e) {
			//FMLLog.log(Level.SEVERE, "", e.getMessage());
		} finally {
			cfg.save();
		}

	}

	public static void ConfigForMCEconomy(Configuration cfg) {

		maxMP = cfg.getInt("Player_MP_MAX", "general", 1000000, 0, 9999999, "");

		HUD = cfg.getBoolean("MP", "general", true, "");

		sound = cfg.getBoolean("Sound", "general", true, "");


	}

}
